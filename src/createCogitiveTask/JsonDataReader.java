package createCogitiveTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class JsonDataReader {

	public static void main(String[] args) throws JSONException, FileNotFoundException, IOException{
			
			//JSON形式のファイルからResultsDenseCaptionクラスに読み込む
			
		ResultsDenseCaption resultsDensecap = JSON.decode(new FileReader("src/results.json"), ResultsDenseCaption.class);
			
		
			
			System.out.println(resultsDensecap.getOpt());
			System.out.println(resultsDensecap.getResults().get(1));
		
			
		
	}
}
