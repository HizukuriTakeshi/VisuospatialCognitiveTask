package createCogitiveTask;

import java.util.Collections;

import javax.swing.JButton;

public class ChoicesCreate {

	public DataListList coiceslist = new DataListList();;

	/**
	 * @param datalistlist　過去の説明文のリストと現在の説明文のリスト
	 * @param a　選択肢ボタン
	 * @param b　選択肢ボタン
	 * @param c　選択肢ボタン
	 * @param d　選択肢ボタン
	 * @param type 問題のタイプ
	 */
	public void displayCoices(JButton a,JButton b,JButton c,JButton d){
		//翻訳して出力
		a.setText(coiceslist.getDatalistList().get(0).getDatas().get(0).getCaption());
		b.setText(coiceslist.getDatalistList().get(0).getDatas().get(1).getCaption());
		c.setText(coiceslist.getDatalistList().get(0).getDatas().get(2).getCaption());
		d.setText(coiceslist.getDatalistList().get(0).getDatas().get(3).getCaption());
	}

	/**
	 * @param datalistlist
	 * @param type
	 * @return
	 */
	public DataListList createCoices(DataListList datalistlist, QuestionType type){

		int opt = 0;
		if(type == QuestionType.APPEARANCE){
			opt = 1;
		}

		for(Data answer: searchAnswer(datalistlist.getDatalistList().get(opt), type).getDatas()){

			DataList coices = new DataList();


			coices = setCoices(datalistlist.getDatalistList().get(opt), answer, type);



			Collections.shuffle(coices.getDatas());


			//ここで3つまでリストを減らす
			for(int i = coices.getDatas().size()-1; i>=3;i--){
				coices.getDatas().remove(i);
			}

			//さらに正解のキャプションをcoices
			coices.addData(answer);

			//もう一度シャッフル
			Collections.shuffle(coices.getDatas());

			coiceslist.addDataList(coices);

		}

		return coiceslist;

	}

	/**
	 * @param datalist 説明文のリスト
	 * @param type	問題のタイプ
	 * @return　問題の答えとなる説明文のリスト
	 */
	public DataList searchAnswer(DataList datalist, QuestionType type){
		DataList dlist = new DataList();

		for(Data d: datalist.getDatas()){
			if(d.getType() == type && d.getLink() == -1){
				dlist.addData(d);
			}
		}

		return dlist;

	}

	/**
	 * @param datalistlist　説明文のリスト
	 * @param answer　正解の説明文
	 * @param type　問題のタイプ
	 * @return　正解を除いた選択肢のリスト
	 */
	public DataList setCoices(DataList datalist , Data answer, QuestionType type){
		DataList Coices = new DataList();


		for(Data d: datalist.getDatas()){
			//正解文ではない、出現or消失していない、正解文とのリンクはない
			if(!d.getCaption().equals(answer.getCaption()) && d.getType() != type && d.getLink() == -1){
				Coices.addData(d);
			}
		}

		return Coices;
	}
}
