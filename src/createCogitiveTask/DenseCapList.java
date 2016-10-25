package createCogitiveTask;

import java.util.List;
import java.util.Map;

import nlp.MorphologicalAnalysis;

public class DenseCapList {
//
	private Map<String, Object> opt;

	private List<DenseCap> results;

	public Map<String, Object> getOpt() {
		return opt;
	}

	public void setOpt(Map<String, Object> opt) {
		this.opt = opt;
	}

	public List<DenseCap> getResults() {
		return results;
	}

	public void setResults(List<DenseCap> results) {
		this.results = results;
	}

	public DataListList toDataListList() {
		// TODO 自動生成されたメソッド・スタブ

		//Dataを入れる

		DataListList dataListList = new DataListList();

		for (DenseCap tmp : results) {
			DataList dataList = new DataList();
			for(int i = 0;tmp.getScores()[i]>0;i++){


				String buf = tmp.getCaptions().get(i);

				try {
					if(MorphologicalAnalysis.checkV(tmp.getCaptions().get(i))){
						buf = buf + ".";
					}
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}


				Data tmp2 = new Data(tmp.getImg_name(), tmp.getScores()[i], buf,tmp.getBoxes()[i]);
				dataList.addData(tmp2);
			}
			dataListList.addDataList(dataList);

		}

		return dataListList;
	}
}
