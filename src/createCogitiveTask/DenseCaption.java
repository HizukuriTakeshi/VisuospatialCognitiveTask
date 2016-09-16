package createCogitiveTask;


import java.util.Arrays;


public class DenseCaption implements DisplayObject {

	private String img_name;
	private double score;
	private String caption;
	private double[] boxes;
	
	public DenseCaption(String img_name, double score, String caption, double[] boxes){
		this.img_name = img_name;
		this.score = score;
		this.caption = caption;
		this.boxes = boxes;
	}
	


	public String getImg_name() {
		return img_name;
	}



	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}



	public double getScore() {
		return score;
	}



	public void setScore(double score) {
		this.score = score;
	}



	public String getCaption() {
		return caption;
	}



	public void setCaption(String caption) {
		this.caption = caption;
	}



	public double[] getBoxes() {
		return boxes;
	}



	public void setBoxes(double[] boxes) {
		this.boxes = boxes;
	}



	@Override
	public void display() {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("score="+score);
		System.out.println("caption="+caption);
		System.out.println("boxes="+Arrays.asList(boxes));
	}
	
	public String toString(){
		return "["
				+ score + ","
				+ caption + ","
				+ Arrays.asList(boxes) 
				+ "]";
	}

}
