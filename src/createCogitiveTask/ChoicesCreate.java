package createCogitiveTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;

public class ChoicesCreate {

	public List<String> coices = null;

	/**
	 * @param datalistlist　過去の説明文のリストと現在の説明文のリスト
	 * @param a　選択肢ボタン
	 * @param b　選択肢ボタン
	 * @param c　選択肢ボタン
	 * @param d　選択肢ボタン
	 * @param type 問題のタイプ
	 */
	public void createCoices(DataListList datalistlist, JButton a,JButton b,JButton c,JButton d, QuestionType type){

		coices = new ArrayList<>();


		String seikai = searchAnswer(datalistlist, type).getDatas().get(0).getCaption();
		coices = setCoices(datalistlist, seikai, type);



		Collections.shuffle(coices);
		//ここで3つまでリストを減らす

		for(int i = coices.size()-1; i>=3;i--){
			coices.remove(i);
		}
		//さらに正解のキャプションをcoices

		coices.add(seikai);

		//もう一度シャッフル
		Collections.shuffle(coices);

		//翻訳して出力
		a.setText(coices.get(0));
		b.setText(coices.get(1));
		c.setText(coices.get(2));
		d.setText(coices.get(3));
	}

	/**
	 * @param datalistlist 過去の説明文のリストと現在の説明文のリスト
	 * @param type	問題のタイプ
	 * @return　問題の答えとなる説明文のリスト
	 */
	public DataList searchAnswer(DataListList datalistlist, QuestionType type){
		int opt = 0;
		DataList datalist = new DataList();

		if(type == QuestionType.DISAPPEARANCE){
			opt = 0;
		}else if(type == QuestionType.APPEARANCE){
			opt = 1;
		}

		for(Data d: datalistlist.getDatalistList().get(opt).getDatas()){
			if(d.getType() == type){
				datalist.addData(d);
			}
		}

		return datalist;

	}

	/**
	 * @param datalistlist　過去の説明文のリストと現在の説明文のリスト
	 * @param seikai　正解の説明文
	 * @param type　問題のタイプ
	 * @return　選択肢のリスト
	 */
	public List<String> setCoices(DataListList datalistlist , String seikai, QuestionType type){
		List<String> Coices = new ArrayList<>();


		//仕方なくopt使用　のちに変更
		int opt = 0;
		if(type == QuestionType.APPEARANCE){
			opt = 1;
		}

		for(Data d: datalistlist.getDatalistList().get(opt).getDatas()){
			if(!d.getCaption().equals(seikai) && d.getType() != QuestionType.APPEARANCE && d.getLink() == -1){
				Coices.add(d.getCaption());
			}
		}

		return Coices;

	}
}
