package createCogitiveTask;

import org.opencv.core.Rect;

import imgProc.BackgroundSub;

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

			
			//nameは出力ファイル名
			int name = 0;
			int opt=0;
			if(type == QuestionType.APPEARANCE){
				opt=1;
			}
			
			//差分とキャプションのBBを比較してピクセル割合が大きいときdatalistにadd
			for(Data d : datalistlist.getDatalistList().get(opt).getDatas()){	
				if(bgs.checkBoundingBox(d,name) > 0.1){
					datalist.addData(d);
					d.setType(QuestionType.APPEARANCE);
					System.out.println(d.getCaption());
					name++;
				}
			}
			
			if(datalist.getDatas().isEmpty()){
				System.out.println("empty");
				//輪郭のところを再びDneseCap
			}
			
			//テスト
			Rect[] rects = bgs.findDifference(bgs.getDiffImg());
			System.out.println(rects.length);
			for(int i= 0; i<rects.length ; i++ ){
				Rect r = rects[i];
				bgs.checkBoundingBox(r, i);
			}
			setDatalist0(datalist);
		}





}
