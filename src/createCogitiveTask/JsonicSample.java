package createCogitiveTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//JSONICをインポート
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class JsonicSample {
 public static void main(String[] args) {

     
     // 配列を含むJSON形式のデータを用意
     
     //final HogeList hogeList = JSON.decode(hoges, HogeList.class);

     try {
		HogeList hogeList = JSON.decode(new FileReader("src/results.json"), HogeList.class);
		 
	     for (final Hoge tmp : hogeList.getHoges()) {
	         System.out.println("tmp.name:" + tmp.getName());
	         System.out.println("tmp.age:" + tmp.getAge());
	}
	     
     } catch (JSONException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
    
 } 
}
