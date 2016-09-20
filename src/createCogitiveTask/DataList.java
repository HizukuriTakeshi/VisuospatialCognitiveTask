package createCogitiveTask;

import java.util.ArrayList;
import java.util.List;

public class DataList {
	private List<Data> datas;

	public List<Data> toDataList(DenseCapList densecapList){

		datas = new ArrayList<Data>();

		for (DenseCap tmp : densecapList.getResults()) {
			for(int i = 0;tmp.getScores()[i]>0;i++){
				Data tmp2 = new Data(tmp.getImg_name(), tmp.getScores()[i], tmp.getCaptions().get(i),tmp.getBoxes()[i]);
				System.out.println(tmp2.getCaption());
				datas.add(tmp2);		
			}
			break;
		}

		return datas;

	}

	public List<Data> getDatas() {
		return datas;
	}

	public void setDataList(List<Data> datas) {
		this.datas = datas;
	}

}
