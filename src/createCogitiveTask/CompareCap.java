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
	public void compareCaption0(DataListList datalistlist) throws Exception{

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
			}
		}

		setDatalist1(datalist);
	}


	public void compareCaption(DataListList datalistlist){

		try {
			compareCaption0(datalistlist);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		compareCaption1(datalistlist);

	}

}
