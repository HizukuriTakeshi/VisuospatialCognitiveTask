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

		ImageIcon icon = new ImageIcon(".\\src\\nc107711.jpg");


		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(28, 46, 124, 116);
		add(lblNewLabel);


		JLabel lblTest = new JLabel("test");
		lblTest.setBounds(0, 10, 407, 13);
		add(lblTest);

	}
}
