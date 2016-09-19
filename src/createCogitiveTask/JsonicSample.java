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


     try {


	DenseCapList hogeList1 = JSON.decode(new FileReader("src/results.json"), DenseCapList.class);

	     for (DenseCap tmp1 : hogeList1.getDenseCaps()) {
	         System.out.println("tmp.name:" + tmp1.getName());
	         System.out.println("tmp.age:" + tmp1.getAge());
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
