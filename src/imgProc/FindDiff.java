package imgProc;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import imgProc.BackgroundSub;

public class FindDiff {


	/**尤も変化してそうな輪郭の選択
	 * @param before_imgName
	 * @param after_imgName
	 */
	public void selectChangeContour(String before_imgName, String after_imgName){

		String before_imgPath = before_imgName;
		String after_imgPath =  after_imgName;

		//差分計算
		BackgroundSub bgs = new BackgroundSub(before_imgPath, after_imgPath);
			
		//輪郭抽出した画像の切り出し
		Rect[] rects = bgs.findDifference(bgs.getDiffImg());
		
		
		Mat diff_img = bgs.getDiffImg();
		Mat after_img = Highgui.imread(after_imgPath);
		
		
		Rect max_r = new Rect();
		double max = 0;
		
		for(int i= 0; i<rects.length ; i++ ){
			Rect r = rects[i];
			
			double a = r.width*r.height;
			double b = diff_img.rows()*diff_img.cols();
			double result = bgs.checkBoundingBox(r, i);
			
			if(result > 0.1 && a/b > 0.005){
				if(max<result){
					max_r = r;
				}
			}	
			
		}
		max_r.set(new double[] {max_r.x, max_r.y ,max_r.width,max_r.height});
		Mat img = new Mat(after_img, max_r);
		Highgui.imwrite("imgs/processed/rinkaku/1.jpg",img);
	}
}
