package createCogitiveTask;

import nlp.CosinSimilarity;

public class CompareCap {

	public DataList datalist0 = new DataList();
	public DataList datalist1 = new DataList();
	private int count=0;

	//cosin類似度チェック
	CosinSimilarity cs = new CosinSimilarity();


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


	/**データリストリスト中のデータリスト同士のキャプションを比較して
	 * 増えたキャプションのみを返す
	 * @param datalistlist　データリストリスト
	 * @throws Exception
	 */
	public void compareCaption0(DataListList datalistlist){

		DataList datalist = new DataList();


		for(Data d1: datalistlist.getDatalistList().get(1).getDatas()){

			count = 0;
			for(Data d0: datalistlist.getDatalistList().get(0).getDatas()){

				if(cs.caluculate(d1.getCaption(),d0.getCaption())>0.6){
					break;
				}else{
					count++;
				}
			}

			if(count == datalistlist.getDatalistList().get(0).getDatas().size()){
				datalist.addData(d1);
				d1.setType(QuestionType.APPEARANCE);
			}
		}
		setDatalist0(datalist);
	}


	/**データリストリスト中のデータリスト同士のキャプションを比較して
	 * 消えたキャプションのみを返す
	 * @param datalistlist
	 */
	public void compareCaption1(DataListList datalistlist){

		DataList datalist = new DataList();


		for(Data d0: datalistlist.getDatalistList().get(0).getDatas()){

			count = 0;
			for(Data d1: datalistlist.getDatalistList().get(1).getDatas()){

				try {
					if(cs.caluculate(d0.getCaption(),d1.getCaption())>0.6){
						break;
					}else{
						count++;
					}
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}

			//kokogaokasiiyo
			if(count == datalistlist.getDatalistList().get(1).getDatas().size()){
				datalist.addData(d0);
				d0.setType(QuestionType.DISAPPEARANCE);
			}
		}


		setDatalist1(datalist);
	}


	public void compareCaption(DataListList datalistlist){

		compareCaption0(datalistlist);
		compareCaption1(datalistlist);
		uniquCaption(datalistlist.getDatalistList().get(0));
		uniquCaption(datalistlist.getDatalistList().get(1));
	}

	/**datalistの消失・出現フラグのあるキャプションを比較する
	 *
	 * @param datalist　
	 */
	public void uniquCaption(DataList datalist){
		for(int i = 0 ;i<datalist.getDatas().size()-1;i++){
			for(int j = i+1;j<datalist.getDatas().size()-1;j++){
				//出現もしくは消失したキャプションで、かつ、出現は出現、消失は消失と比較
				if(datalist.getDatas().get(i).getType() != QuestionType.NULL && datalist.getDatas().get(i).getType() == datalist.getDatas().get(j).getType()){
					if(cs.caluculate(datalist.getDatas().get(i).getCaption(),datalist.getDatas().get(j).getCaption())>0.6){
						datalist.getDatas().get(j).setLink(i);
					}
				}
			}
		}
	}

	/**増えたキャプションフラグがついたものが過去説明文にないかチェック
	 * @param datalistlist
	 */
	public void checkCaption0(DataListList datalistlist){

		DataList datalist = new DataList();

		for(Data d1: datalistlist.getDatalistList().get(1).getDatas()){

			//出現フラグチェック
			if(d1.getType()==QuestionType.APPEARANCE){
				for(Data d0: datalistlist.getDatalistList().get(0).getDatas()){

					//ひとつでも類似説明文があれば飛ばす
					if(cs.caluculate(d1.getCaption(),d0.getCaption())>0.6){
						d0.setType(QuestionType.NULL);
						break;
					}else{
						count++;
					}
				}

				//すべて類似しなかった場合
				if(count == datalistlist.getDatalistList().get(0).getDatas().size()){
					datalist.addData(d1);
					d1.setType(QuestionType.APPEARANCE);
				}

			}
		}
		setDatalist0(datalist);
	}
}

