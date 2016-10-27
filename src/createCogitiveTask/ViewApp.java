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

import nlp.KeitaisoKaiseki;
import translation.Transration;

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
	private JScrollPane scrollPane_3;
	private JTextArea textArea_3;

	private Transration trans;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;


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
	//
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
		frame.setBounds(100, 100, 784, 852);
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
		scrollPane_1.setBounds(509, 238, 227, 130);
		frame.getContentPane().add(scrollPane_1);

		textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(509, 394, 227, 130);
		frame.getContentPane().add(scrollPane_2);

		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(509, 548, 227, 130);
		frame.getContentPane().add(scrollPane_3);

		textArea_3 = new JTextArea();
		scrollPane_3.setViewportView(textArea_3);

		imgdisplaypanel = new ImageDisplayPanel();
		imgdisplaypanel.setBounds(52, 88, 419, 614);
		frame.getContentPane().add(imgdisplaypanel);

		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(108, 712, 227, 21);
		frame.getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(411, 712, 244, 21);
		frame.getContentPane().add(btnNewButton_2);

		btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(107, 762, 227, 21);
		frame.getContentPane().add(btnNewButton_3);

		btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(411, 762, 244, 21);
		frame.getContentPane().add(btnNewButton_4);

		//翻訳用のクラス
		trans = new Transration();

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



				/*テキストエリアにキャプション表示*/
				for(Data d: datalistList.getDatalistList().get(0).getDatas()){

					textArea.append(trans.transrateCaption(d.getCaption())+"\n");
				}


				for(Data d: datalistList.getDatalistList().get(0).getDatas()){
					KeitaisoKaiseki k = new KeitaisoKaiseki();

					k.Keitaiso(trans.transrateCaption(d.getCaption()));
				}


				for(Data d: datalistList.getDatalistList().get(1).getDatas()){

					textArea_1.append(trans.transrateCaption(d.getCaption())+"\n");

				}


				/*テキストエリアに比較キャプション表示*/
				CompareCap compareCap = new CompareCap();
				compareCap.compareCaption(datalistList);

				for(Data d:datalistList.getDatalistList().get(0).getDatas()){
					if(d.getType() == 1 && d.getLink() == -1){
					textArea_2.append(trans.transrateCaption(d.getCaption())+"\n");
					}
				}

				for(Data d:datalistList.getDatalistList().get(1).getDatas()){
					if(d.getType() == 0 && d.getLink() == -1){
					textArea_3.append(trans.transrateCaption(d.getCaption())+"\n");
					}
				}

				imgdisplaypanel.ImageDisplay(datalistList);
			}
		}
	}
}
