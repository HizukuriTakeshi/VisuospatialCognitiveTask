package createCogitiveTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;

public class ChoicesCreate {

public List<String> coices = null;

	public void createCoices(DataListList datalistlist, JButton a,JButton b,JButton c,JButton d, int opt){

		coices = new ArrayList<>();

		coices = setCoices(datalistlist, "a bottle of water", opt);

		Collections.shuffle(coices);
		//ここで3つまでリストを減らし、さらに正解のキャプションをcoicesに追加＋シャッフル

		coices.add("a bottle of water");

		Collections.shuffle(coices);

		//翻訳して出力
		a.setText(coices.get(0));
		b.setText(coices.get(1));
		c.setText(coices.get(2));
		d.setText(coices.get(3));
	}

	public List<String> setCoices(DataListList datalistlist , String seikai, int opt){
		List<String> Coices = new ArrayList<>();
		for(Data d: datalistlist.getDatalistList().get(opt).getDatas()){
			if(!d.getCaption().equals(seikai) && d.getType() != 0 && d.getLink() == -1){
				Coices.add(d.getCaption());
			}
		}

		return Coices;

	}
}
