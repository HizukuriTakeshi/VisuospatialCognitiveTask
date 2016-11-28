package imgProc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import createCogitiveTask.Data;

public class BackgroundSub {

	private Mat diffImg;
	private double affine_x;
	private double affine_y;
	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Mat getDiffImg() {
		return diffImg;
	}

	public void setDiffImg(Mat diffImg) {
		this.diffImg = diffImg;
	}

	public double getAffine_x() {
		return affine_x;
	}

	public void setAffine_x(double affine_x) {
		this.affine_x = affine_x;
	}

	public double getAffine_y() {
		return affine_y;
	}

	public void setAffine_y(double affine_y) {
		this.affine_y = affine_y;
	}

	public BackgroundSub(String pathObject, String pathScene){
		Mat img_object = Highgui.imread(pathObject, 0);
		Mat img_scene = Highgui.imread(pathScene, 0);

		//特徴検出
		FeatureDetector detector = FeatureDetector.create(4); //4 = SURF 
		MatOfKeyPoint keypoints_object = new MatOfKeyPoint();
		MatOfKeyPoint keypoints_scene  = new MatOfKeyPoint();
		detector.detect(img_object, keypoints_object);
		detector.detect(img_scene, keypoints_scene);

		//特徴記述
		DescriptorExtractor extractor = DescriptorExtractor.create(2); //2 = SURF;
		Mat descriptor_object = new Mat();
		Mat descriptor_scene = new Mat();
		extractor.compute(img_object, keypoints_object, descriptor_object);
		extractor.compute(img_scene, keypoints_scene, descriptor_scene);

		//マッチング
		DescriptorMatcher matcher = DescriptorMatcher.create(1); // 1 = FLANNBASED
		List<MatOfDMatch> knnmatch_points = new ArrayList<MatOfDMatch>();
		matcher.knnMatch(descriptor_object, descriptor_scene, knnmatch_points, 2);

		final double match_par = 0.6;
		LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
		MatOfDMatch gm = new MatOfDMatch();
		LinkedList<Point> match_point1 = new LinkedList<Point>();
		LinkedList<Point> match_point2 = new LinkedList<Point>();

		//knnマッチング結果の絞り込み
		for(int i = 0; i< knnmatch_points.size(); ++i){
			double distance1 = knnmatch_points.get(i).toArray()[0].distance;
			double distance2 = knnmatch_points.get(i).toArray()[1].distance;
			//第2候補から距離値が離れている点のみ抽出
			if(distance1< distance2*match_par){
				good_matches.addLast(knnmatch_points.get(i).toArray()[0]);
				match_point1.add(keypoints_object.toList().get(knnmatch_points.get(i).toArray()[0].queryIdx).pt);
				match_point2.add(keypoints_scene.toList().get(knnmatch_points.get(i).toArray()[0].trainIdx).pt);
			}
		}

		gm.fromList(good_matches);
		MatOfPoint2f m_p1 = new MatOfPoint2f();
		m_p1.fromList(match_point1);
		MatOfPoint2f m_p2 = new MatOfPoint2f();
		m_p2.fromList(match_point2);

		//ホモグラフィ行列計算
		Mat masks = new Mat();
		Mat H = Calib3d.findHomography(m_p1, m_p2, 3, Calib3d.RANSAC, masks);
		Mat HP = H.clone();

		LinkedList<DMatch> inlinerMatch = new LinkedList<DMatch>();
		MatOfDMatch inM = new MatOfDMatch();

		//RANSACに使った点のみ抽出
		for(int i = 0; i< masks.rows(); ++i){
			if(masks.get(i, 0)[0] == 1){
				inlinerMatch.addLast(good_matches.get(i));
			}
		}
		inM.fromList(inlinerMatch);

		Mat img_matches = new Mat();
		Features2d.drawMatches(img_object, keypoints_object, img_scene, keypoints_scene, inM, img_matches, new Scalar(255,0,0), new Scalar(0,0,255), new MatOfByte(), 2);
		Highgui.imwrite("./imgs/match.jpg", img_matches);


		//ホモグラフィ変換後の画像の4隅を計算
		Mat P = new Mat(new Size(4,3), CvType.CV_64F);
		P.put(0, 0, new double[] {0,img_object.width(), img_object.width(), 0 ,0, 0, img_object.height(), img_object.height(), 1, 1, 1, 1});
		Mat PP = new Mat(new Size(4,3), CvType.CV_64F);
		//画像の4隅をホモグラフィ変換する
		Core.gemm(H, P, 1, new Mat(), 0, PP);

		double x_min,y_min, x_max,y_max;
		Mat dst = new Mat();
		//変換後のx,yの最小値計算
		Core.reduce(PP, dst, 1, Core.REDUCE_MIN);
		x_min = dst.get(0, 0)[0];
		y_min = dst.get(1, 0)[0];
		//変換後のx,yの最大値計算
		Core.reduce(PP, dst, 1, Core.REDUCE_MAX);
		x_max = dst.get(0, 0)[0];
		y_max = dst.get(1, 0)[0];

		double affinex=0, affiney=0;
		//ホモグラフィ変換後の座標が負の領域にある場合、平行移動する
		if(x_min < 0 ){
			HP.put(0, 2, new double[] {HP.get(0, 2)[0]-x_min});
			affinex = -x_min;
		}

		if(y_min < 0 ){
			HP.put(1, 2, new double[] {HP.get(1, 2)[0]-y_min});
			affiney = -y_min;
		}

		//平行移動前の座標
		List<Point> src_pt = new ArrayList<Point>();
		src_pt.add(new Point(0,0));
		src_pt.add(new Point(10,0));
		src_pt.add(new Point(0,10));
		MatOfPoint2f srcpt = new MatOfPoint2f();
		srcpt.fromList(src_pt);
		//平行移動後の座標
		List<Point> dst_pt = new ArrayList<Point>();
		dst_pt.add(new Point(affinex, affiney));
		dst_pt.add(new Point(10+affinex,affiney));
		dst_pt.add(new Point(affinex,10+affiney));
		MatOfPoint2f dstpt = new MatOfPoint2f();
		dstpt.fromList(dst_pt);

		//アフィン行列生成
		Mat A = Imgproc.getAffineTransform(srcpt, dstpt);

		Mat homo_img = new Mat();
		Mat affine_img = new Mat();
		Mat diff_img = new Mat();

		//ホモグラフィ変換
		Imgproc.warpPerspective(img_object, homo_img, HP, new Size(x_max*1.5,y_max*1.5));
		//アフィン変換による平行移動
		Imgproc.warpAffine(img_scene, affine_img, A, new Size(x_max*1.5,y_max*1.5));

		//差分
		Core.absdiff(homo_img, affine_img, diff_img);
		//しきい値
		Imgproc.threshold(diff_img, diff_img, 100.0, 255.0,Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
		//ノイズ除去
		Imgproc.erode(diff_img, diff_img, new Mat(), new Point(-1,-1), 1);
		//欠損部補完
		Imgproc.dilate(diff_img, diff_img, new Mat());

		setDiffImg(diff_img);
		setAffine_x(affinex);
		setAffine_y(affiney);
		setX(img_object.cols());
		setY(img_object.rows());

		Highgui.imwrite("imgs/homo.jpg",homo_img);
		Highgui.imwrite("imgs/affine.jpg",affine_img);
		Highgui.imwrite("imgs/diff.jpg",diff_img);
	}

	/**
	 * @param d
	 * @param name
	 * @return
	 */
	public double checkBoundingBox(Data d, int name){
		//DenseCapのBBをリサイズ
		int x,y,w,h;
		if(d.getBox()[0] > 0){
			x = (int)((d.getBox()[0]*getX()/720)+getAffine_x());
		}else{
			x= 0;
		}
		if(d.getBox()[1] > 0){
			y = (int)((d.getBox()[1]*getY()/540)+getAffine_y());
		}else{
			y= 0;
		}
		w = (int)d.getBox()[2]*getX()/720;
		h = (int)d.getBox()[3]*getY()/540;

		Mat cut_img = new Mat(getDiffImg(), new Rect(x,y,w,h));

		//BB内の差の割合計算
		double sum = 0;
		for(int i = 0; i< cut_img.rows(); i++){
			for(int j = 0; j < cut_img.cols(); j++){
				sum+= cut_img.get(i, j)[0]/255;
			}
		}

		
		double result = sum/(cut_img.rows()*cut_img.cols());
		if(result > 0.2){
		System.out.println(result);
		Highgui.imwrite("imgs/cut"+name+".jpg",cut_img);
		}
		return result;
	}
}
