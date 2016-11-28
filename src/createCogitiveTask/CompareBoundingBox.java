package createCogitiveTask;

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
			
			for(Data d : datalistlist.getDatalistList().get(opt).getDatas()){	
				if(bgs.checkBoundingBox(d,name) > 0.2){
					datalist.addData(d);
					d.setType(QuestionType.APPEARANCE);
					System.out.println(d.getCaption());
					name++;
				}
				
			}

			setDatalist0(datalist);
		}





}