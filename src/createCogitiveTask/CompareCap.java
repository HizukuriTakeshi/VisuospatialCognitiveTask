package createCogitiveTask;


public class CompareCap {

	public DataList datalist0 = new DataList();
	public DataList datalist1 = new DataList();
	private int count=0;

	

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



	public void compareCaption0(DataListList datalistlist){

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

		setDatalist0(datalist);
	}
	
	public void compareCaption1(DataListList datalistlist){

		DataList datalist = new DataList();


		for(Data d0: datalistlist.getDatalistList().get(1).getDatas()){

			count = 0;
			for(Data d1: datalistlist.getDatalistList().get(0).getDatas()){
				//System.out.println(d1.getCaption()+" "+d0.getCaption());
				if(d0.getCaption().equals(d1.getCaption())){
					break;
				}else{
					count++;
				}

			}
			System.out.println(d0.getCaption());

			//kokogaokasiiyo
			if(count == datalistlist.getDatalistList().get(1).getDatas().size()){
			datalist.addData(d0);
			}
		}

		setDatalist0(datalist);
	}
	
}
