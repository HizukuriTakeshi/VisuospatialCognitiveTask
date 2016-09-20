package createCogitiveTask;

import java.util.ArrayList;
import java.util.List;

public class DataList {
	private List<Data> dataList;

	public List<Data> toDataList(DenseCapList densecapList){

		dataList = new ArrayList<Data>();

		for (DenseCap tmp : densecapList.getResults()) {
			for(int i = 0;tmp.getScores()[i]>0;i++){
				Data tmp2 = new Data(tmp.getImg_name(), tmp.getScores()[i], tmp.getCaptions().get(i),tmp.getBoxes()[i]);
				System.out.println(tmp2.getCaption());
				dataList.add(tmp2);		
			}
		}

		return dataList;

	}

	public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

}
