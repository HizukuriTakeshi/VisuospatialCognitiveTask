package createCogitiveTask;


public class CompareCap {

	public DataList datalist = new DataList();
	private int count=0;

	public DataList getDatalist() {
		return datalist;
	}

	public void setDatalist(DataList datalist) {
		this.datalist = datalist;
	}


	public void compareCaption(DataListList datalistlist){

		DataList datalist = new DataList();


		for(Data d1: datalistlist.getDatalistList().get(1).getDatas()){

			count = 0;
			for(Data d0: datalistlist.getDatalistList().get(0).getDatas()){
				//System.out.println(d1.getCaption()+" "+d0.getCaption());
				if(d1.getCaption().equals(d0.getCaption())){
					break;
				}else{
					count++;
				}

			}
			System.out.println(d1.getCaption());

			//kokogaokasiiyo
			if(count == datalistlist.getDatalistList().get(0).getDatas().size()){
			datalist.addData(d1);
			}
		}

		setDatalist(datalist);
	}
}
