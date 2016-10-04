package createCogitiveTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDisplayPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ImageDisplayPanel() {
		setLayout(null);

		ImageIcon icon = new ImageIcon();


		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(20, 20, lblNewLabel.getIcon().getIconWidth(), lblNewLabel.getIcon().getIconHeight());
		add(lblNewLabel);

		
		JLabel lblTest = new JLabel("test");
		lblTest.setBounds(0, 10, 407, 13);
		add(lblTest);

	}
}
