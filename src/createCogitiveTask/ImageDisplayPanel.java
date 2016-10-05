package createCogitiveTask;

import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDisplayPanel extends JPanel {
	private JLabel lblNewLabel_1;

	/**
	 * Create the panel.
	 */
	public ImageDisplayPanel() {
		setLayout(null);
		
		lblNewLabel_1 = new JLabel("");	
		lblNewLabel_1.setBounds(0, 0, 360, 270);
		add(lblNewLabel_1);

	}
	
	public void ImageDisplay(DataListList datalistList){
		ImageIcon icon = new ImageIcon("./src/"+datalistList.getDatalistList().get(0).getDatas().get(0).getImg_name());	
		
		
		MediaTracker tracker = new MediaTracker(this);
		Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * 0.25), -1,Image.SCALE_SMOOTH);
		tracker.addImage(smallImg, 1);
		
		ImageIcon smallIcon = new ImageIcon(smallImg);
		
		lblNewLabel_1.setIcon(smallIcon);
		lblNewLabel_1.setBounds(53, 51, 269, 199);
		add(lblNewLabel_1);
		
	}
}
