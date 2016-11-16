package nlp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import createCogitiveTask.*;
public class WordPool {

	public ArrayList<String> wordpool = null;


	/**
	 * datalistの単語を重複を許さずプーリングする関数
	 * @param datalist
	 */
	public void poolingWords(DataList datalist){

		List<String> tmp = new ArrayList<String>();

		//説明文を単語に分解
		for(Data d: datalist.getDatas()){	
			tmp.addAll(MorphologicalAnalysis.breakUp(d.getCaption()));
		}

		//重複を消す
		Set<String> set = new HashSet<>(tmp);
		wordpool = new ArrayList<>(set);

	}

	/**
	 * 引数の説明文の単語がプーリングした単語群の中にあるか
	 * @param str
	 * @return
	 */
	public Boolean containsWord(String caption){
		List<String> words = MorphologicalAnalysis.breakUp(caption);

		Boolean result = false;


		for(String word: words){
			if(MorphologicalAnalysis.checkNoun(word)){
				if(wordpool.contains(word)){	
					result = true;
				}else{
					result = false;
					break;
				}
			}
		}

		return result;
	}
}
