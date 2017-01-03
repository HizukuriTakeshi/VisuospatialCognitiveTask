package createCogitiveTask;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import densecapProcess.DenseCapProcess;
import imgProc.BackgroundSub;
import imgProc.FindDiff;

public class CompareBoundingBox {

	public DataList datalist0 = new DataList();
	public DataList datalist1 = new DataList();
	public DataList getDatalist0() {
		return datalist0;
	}

	public void setDatalist0(DataList datalist0) {
		this.datalist0 = datalist0;
	}

	public DataList getDatalist1() {
		return datalist1;
	}

	public void setDatalist1(DataList datalist1) {
		this.datalist1 = datalist1;
	}


	/**キャプションのバウンディングボックスと差分画像と比較
	 * 増えたキャプションのみを返す
	 * @param datalistlist　データリストリスト
	 * @throws Exception
	 */
	public void compareBoundingBox(DataListList datalistlist, QuestionType type){

		String before_imgPath = "./imgs/"+ datalistlist.getDatalistList().get(0).getDatas().get(0).getImg_name();
		String after_imgPath = "./imgs/"+ datalistlist.getDatalistList().get(1).getDatas().get(0).getImg_name();

		
		
		//差分計算
		BackgroundSub bgs = new BackgroundSub(before_imgPath, after_imgPath);
		DataList datalist = new DataList();
		//bgs.setDiffImg(bgs.createPartSub(bgs.getHomoImg(), bgs.getAfterImg(), (int)bgs.getAfterImg().cols()/5, (int)bgs.getAfterImg().rows()/5, (int)bgs.getAfterImg().cols()/5, (int)bgs.getAfterImg().rows()/5));

		//nameは出力ファイル名
		int name = 0;
		int opt=0;
		if(type == QuestionType.APPEARANCE){
			opt=1;
		}

		
		
		//差分とキャプションのBBを比較してピクセル割合が大きいときdatalistにadd
		for(Data d : datalistlist.getDatalistList().get(opt).getDatas()){
			//System.out.println(d.getCaption()+":"+d.getWariai());
			d.setWariai(bgs.checkBoundingBox(d,name));
			if(d.getWariai() > 0.1){
				datalist.addData(d);
				d.setType(QuestionType.APPEARANCE);
				System.out.println("割合高い:"+d.getWariai()+d.getCaption());
				name++;
			}
			name++;
		}
		
		//割合ごとに並べ替え
		Collections.sort(datalistlist.getDatalistList().get(opt).getDatas(), new Comparator<Data>(){
			@Override
			public int compare(Data first, Data second){
				
				//idの文字列長でソート。文字列数がが小さい順に並べる。
				if(first.getWariai() > second.getWariai()){
					return -1;
				}else if(first.getWariai() < second.getWariai()){
					return 1;
				}else if(first.getWariai() == second.getWariai()){
					return 0;
				}
				return 0;
			}
		});
		
		
		
		
		//差分領域とキャプションの重なりがないときは輪郭抽出を行う
//		if(datalist.getDatas().isEmpty()){
//			System.out.println("empty");
//			//輪郭のところを再びDneseCap
//			FindDiff fd = new FindDiff(bgs.getDiffImg(),bgs.getBeforeImg(),bgs.getAfterImg());
//			fd.selectChangeContour();
//			//densecapに通す
//			try {
//				DenseCapProcess.DenseCapScript3();
//			} catch (IOException | InterruptedException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//			System.out.println("rinkaku end");
//		}

		setDatalist0(datalist);
	}





}
