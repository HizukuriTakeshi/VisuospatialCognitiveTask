package createCogitiveTask;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;

//
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewApp {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private final Action action = new SwingAction();
	private JScrollPane scrollPane;
	private JTextArea textArea;


	private JTextArea textArea_1;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextArea textArea_2;
	private ImageDisplayPanel imgdisplaypanel;
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
		frame.setBounds(100, 100, 786, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(52, 38, 444, 18);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("New button");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(553, 38, 152, 20);
		frame.getContentPane().add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(509, 88, 227, 130);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(509, 254, 227, 130);
		frame.getContentPane().add(scrollPane_1);

		textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(509, 430, 227, 130);
		frame.getContentPane().add(scrollPane_2);

		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);

		imgdisplaypanel = new ImageDisplayPanel();
		imgdisplaypanel.setBounds(52, 88, 419, 730);
		frame.getContentPane().add(imgdisplaypanel);
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

				JsonDataReader jsonReader = new JsonDataReader();//JSON形式ファイル読み込みクラス
				DenseCapList densecapList = new DenseCapList();
				densecapList = jsonReader.jsonDataRead(file.getPath());//ファイルから読み込む
				DataListList datalistList = new DataListList();
				datalistList = densecapList.toDataListList();


				for(Data d: datalistList.getDatalistList().get(0).getDatas()){
					textArea.append(d.getCaption()+"\n");
				}


				for(Data d: datalistList.getDatalistList().get(1).getDatas()){
				 	textArea_1.append(d.getCaption()+"\n");
				}

				CompareCap compareCap = new CompareCap();
				compareCap.compareCaption(datalistList);

				for(Data d:compareCap.getDatalist().getDatas()){
					textArea_2.append(d.getCaption()+"\n");
				}
				
				imgdisplaypanel.ImageDisplay(datalistList);
			}
		}
	}
}
