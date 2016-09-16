package createCogitiveTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class JsonDataReader {

	public static void main(String[] args){
			
			//JSON形式のファイルからResultsDenseCaptionクラスに読み込む
			
		
		ResultsDenseCaption	resultsDensecap = null;
		
			try {
			resultsDensecap = JSON.decode(new FileReader("src/results.json"), ResultsDenseCaption.class);
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
				
			
		//	System.out.println(resultsDensecap.getOpt());
			
			System.out.println(resultsDensecap.getResults().get(0).get("scores").get(0));
			System.out.println(resultsDensecap.getResults().get(0).get("captions").get(0));
			
			Object ac = resultsDensecap.getResults().get(0).get("scores").get(0);
			
			System.out.println(ac.getClass());
			
		for(int i = 0; i<10;i++){
				System.out.println(resultsDensecap.getResults().get(0).get("scores").get(i));			
		}

		
	}
}
