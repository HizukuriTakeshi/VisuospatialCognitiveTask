package createCogitiveTask;

import java.util.Collections;

import javax.swing.JButton;

public class ChoicesCreate {

	public DataListList choiceslist;

	public DataListList getChoiceslist() {
		return choiceslist;
	}

	/**
	 * @param datalistlist　過去の説明文のリストと現在の説明文のリスト
	 * @param a　選択肢ボタン
	 * @param b　選択肢ボタン
	 * @param c　選択肢ボタン
	 * @param d　選択肢ボタン
	 * @param type 問題のタイプ
	 */
	public int displayChoices(JButton a,JButton b,JButton c,JButton d, int i){
		//翻訳して出力

		if(i<choiceslist.getDatalistList().size()){
			a.setText(choiceslist.getDatalistList().get(i).getDatas().get(0).getCaption());
			b.setText(choiceslist.getDatalistList().get(i).getDatas().get(1).getCaption());
			c.setText(choiceslist.getDatalistList().get(i).getDatas().get(2).getCaption());
			d.setText(choiceslist.getDatalistList().get(i).getDatas().get(3).getCaption());
			return 0;
		}else{
			return 1;
		}
	}

	/**
	 * @param datalistlist
	 * @param type
	 * @return
	 */
	public DataListList createChoices(DataListList datalistlist, QuestionType type){

		choiceslist =  new DataListList();
		
		int opt = 0;
		if(type == QuestionType.APPEARANCE){
			opt = 1;
		}

		for(Data answer: searchAnswer(datalistlist.getDatalistList().get(opt), type).getDatas()){

			
			DataList choices = new DataList();


			choices = setChoices(datalistlist, answer, type);



			Collections.shuffle(choices.getDatas());


			//ここで3つまでリストを減らす
			for(int i = choices.getDatas().size()-1; i>=3;i--){
				choices.getDatas().remove(i);
			}

			//さらに正解のキャプションをcoices
			choices.addData(answer);

			//もう一度シャッフル
			Collections.shuffle(choices.getDatas());

			choiceslist.addDataList(choices);
			
		}

		return choiceslist;

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
	public DataList setChoices(DataListList datalistlist , Data answer, QuestionType type){
		DataList choices = new DataList();


		for(Data d: datalistlist.getDatalistList().get(0).getDatas()){
			//正解文ではない、出現or消失していない、正解文とのリンクはない
			if(!d.getCaption().equals(answer.getCaption()) && d.getType() != QuestionType.APPEARANCE && d.getLink() == -1){
				choices.addData(d);
			}
		}
		
		for(Data d: datalistlist.getDatalistList().get(1).getDatas()){
			//正解文ではない、出現or消失していない、正解文とのリンクはない
			if(!d.getCaption().equals(answer.getCaption()) && d.getType() != QuestionType.APPEARANCE && d.getLink() == -1){
				choices.addData(d);
			}
		}

		return choices;
	}
}
