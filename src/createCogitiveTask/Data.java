package createCogitiveTask;

public class Data {
	private String img_name;
	private double score;
	private String caption;
	private double[] box;
	
	Data(String img_name, double score, String caption, double[] box){
		this.img_name = img_name;
		this.score = score;
		this.caption = caption;
		this.box = box;
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
	public double[] getBox() {
		return box;
	}
	public void setBox(double[] box) {
		this.box = box;
	}
}