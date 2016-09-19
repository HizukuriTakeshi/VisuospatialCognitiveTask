package createCogitiveTask;


public class DenseCaption  {

	private String img_name;
	private double[] scores;
	private String[] caption;
	private double[][] boxes;
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public double[] getScores() {
		return scores;
	}
	public void setScores(double[] scores) {
		this.scores = scores;
	}
	public String[] getCaption() {
		return caption;
	}
	public void setCaption(String[] caption) {
		this.caption = caption;
	}
	public double[][] getBoxes() {
		return boxes;
	}
	public void setBoxes(double[][] boxes) {
		this.boxes = boxes;
	}

}
