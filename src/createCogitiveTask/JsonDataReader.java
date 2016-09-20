package createCogitiveTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class JsonDataReader {

	public void jsonDataRead(String path){
		try {
			DenseCapList denseList = JSON.decode(new FileReader(path), DenseCapList.class);

			/*
			for (DenseCap tmp : denseList.getResults()) {
				System.out.println("tmp.img_name:" + tmp.getImg_name());
				for(int i = 0;tmp.getScores()[i]>0;i++){
					System.out.println("tmp.score:" + tmp.getScores()[i]);
					System.out.println("tmp.caption:" + tmp.getCaptions().get(i));
					System.out.println("tmp.boxes:" + tmp.getBoxes()[i][0]);
				}
				System.out.println("");
			}
			*/

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
