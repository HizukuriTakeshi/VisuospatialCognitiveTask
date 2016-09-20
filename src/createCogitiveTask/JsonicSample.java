package createCogitiveTask;

public class JsonicSample {
	public static void main(String[] args) {

		String path = "src/results.json";
		JsonDataReader jsonDataReader = new JsonDataReader();
		
		jsonDataReader.jsonDataRead(path);
	}
}
