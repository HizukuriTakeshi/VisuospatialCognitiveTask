package createCogitiveTask;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.FileHandler;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class ViewApp {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private final Action action = new SwingAction();
	private JScrollPane scrollPane;
	private JTextArea textArea;

	
	private DenseCapList densecapList=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewApp window = new ViewApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(52, 38, 136, 18);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("New button");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(260, 36, 108, 23);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 68, 312, 168);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "読み込み");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示
			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら
				File file = filechooser.getSelectedFile(); 
				lblNewLabel.setText(file.getName()); //ラベルの文字をファイル名に
				JsonDataReader jsonReader = new JsonDataReader();
				DenseCapList tmp = new DenseCapList();
				tmp = jsonReader.jsonDataRead(file.getPath());
				DataList tmp2 = new DataList();
				List<Data> tmp3 = tmp2.toDataList(tmp);
				textArea.setText(tmp3.get(0).getCaption());
				textArea.append("\n");
				textArea.append(tmp3.get(1).getCaption());
				
			}
		}
	}
}
