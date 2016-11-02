package createCogitiveTask;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDisplayPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel label_1;
	
	/**
	 * Create the panel.
	 */
	public ImageDisplayPanel() {
		setLayout(null);
		
		label = new JLabel("");
		label.setBounds(0, 0, 360, 270);
		add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(0, 300, 360, 270);
		add(label_1);

	}
	
	public void ImageDisplay(DataListList datalistList){
		ImageIcon icon = new ImageIcon("./imgs/"+datalistList.getDatalistList().get(0).getDatas().get(0).getImg_name());	
		
		
		MediaTracker tracker = new MediaTracker(this);
		Image smallImg = icon.getImage().getScaledInstance((int) (360), (int) (360*icon.getIconHeight()/icon.getIconWidth()),Image.SCALE_SMOOTH);
		tracker.addImage(smallImg, 1);
		ImageIcon smallIcon = new ImageIcon(smallImg);
		
		label.setIcon(smallIcon);
		label.setBounds(0, 0, 360, 270);
		add(label);
		
		ImageIcon icon_1 = new ImageIcon("./imgs/"+datalistList.getDatalistList().get(1).getDatas().get(0).getImg_name());
		
		Image smallImg_1 = icon_1.getImage().getScaledInstance((int) (360), (int) (360*icon_1.getIconHeight()/icon_1.getIconWidth()),Image.SCALE_SMOOTH);
		tracker.addImage(smallImg_1, 1);
		ImageIcon smallIcon_1 = new ImageIcon(smallImg_1);
		
		label_1.setIcon(smallIcon_1);
		label_1.setBounds(0, 300, 360, 270);
		add(label_1);
	}
}
