package createCogitiveTask;

import java.io.FileReader;
import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class JsonDataReader {

	public static void main(String[] args){
		try {
			ResultsDenseCaption resultsDensecap = JSON.decode(new FileReader("src/results.json"), ResultsDenseCaption.class);
			//System.out.println(resultsDensecap.getOpt());
			System.out.println(resultsDensecap.getResults());
		} catch (JSONException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
