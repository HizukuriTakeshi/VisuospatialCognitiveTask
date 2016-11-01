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
	private JLabel readFileLabel;
	private JButton readFileButton;
	private final Action action = new ReadFileAction();
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

	private JButton coiceButton_1;
	private JButton coiceButton_2;
	private JButton coiceButton_3;
	private JButton coiceButton_4;
	private JButton createButton;
	private final Action action_1 = new CreateCoicesAction();

	private ChoicesCreate cc;
	private DataListList datalistList;
	private JButton readImgButton_1;
	private JButton readImgButton_2;
	private final Action action_2 = new ReadImgAction_1();
	private final Action action_3 = new ReadImgAction();
	private JLabel readImgLabel_1;
	private JLabel readImgLabel_2;


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

		readFileLabel = new JLabel("");
		readFileLabel.setBounds(53, 58, 418, 18);
		frame.getContentPane().add(readFileLabel);

		readFileButton = new JButton("New button");
		readFileButton.setBounds(544, 58, 152, 20);
		readFileButton.setAction(action);
		frame.getContentPane().add(readFileButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(510, 141, 227, 130);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(510, 290, 227, 130);
		frame.getContentPane().add(scrollPane_1);

		textArea_1 = new JTextArea();
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		scrollPane_1.setViewportView(textArea_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(510, 446, 227, 130);
		frame.getContentPane().add(scrollPane_2);

		textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(510, 600, 227, 130);
		frame.getContentPane().add(scrollPane_3);

		textArea_3 = new JTextArea();
		scrollPane_3.setViewportView(textArea_3);

		imgdisplaypanel = new ImageDisplayPanel();
		imgdisplaypanel.setBounds(52, 88, 419, 614);
		frame.getContentPane().add(imgdisplaypanel);

		coiceButton_1 = new JButton("New button");
		coiceButton_1.setBounds(128, 746, 227, 21);
		frame.getContentPane().add(coiceButton_1);

		coiceButton_2 = new JButton("New button");
		coiceButton_2.setBounds(412, 746, 244, 21);
		frame.getContentPane().add(coiceButton_2);

		coiceButton_3 = new JButton("New button");
		coiceButton_3.setBounds(128, 775, 227, 21);
		frame.getContentPane().add(coiceButton_3);

		coiceButton_4 = new JButton("New button");
		coiceButton_4.setBounds(412, 775, 244, 21);
		frame.getContentPane().add(coiceButton_4);

		createButton = new JButton("New button");
		createButton.setAction(action_1);
		createButton.setBounds(39, 746, 56, 52);
		frame.getContentPane().add(createButton);
		
		readImgButton_1 = new JButton("New button");
		readImgButton_1.setAction(action_2);
		readImgButton_1.setBounds(209, 12, 108, 23);
		frame.getContentPane().add(readImgButton_1);
		
		readImgButton_2 = new JButton("New button");
		readImgButton_2.setAction(action_3);
		readImgButton_2.setBounds(520, 12, 108, 23);
		frame.getContentPane().add(readImgButton_2);
		
		readImgLabel_1 = new JLabel("New label");
		readImgLabel_1.setBounds(69, 17, 64, 13);
		frame.getContentPane().add(readImgLabel_1);
		
		readImgLabel_2 = new JLabel("New label");
		readImgLabel_2.setBounds(394, 17, 64, 13);
		frame.getContentPane().add(readImgLabel_2);

		//翻訳用のクラス
		trans = new Transration();

		//選択肢作成クラス
		cc = new ChoicesCreate();

		datalistList = new DataListList();

	}

	private class ReadFileAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ReadFileAction() {
			putValue(NAME, "読み込み");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示

			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら
				File file = filechooser.getSelectedFile();
				readFileLabel.setText(file.getName()); //ラベルの文字をファイル名に

				JsonDataReader jsonReader = new JsonDataReader();//JSON形式ファイル読み込みクラス
				DenseCapList densecapList = new DenseCapList();
				densecapList = jsonReader.jsonDataRead(file.getPath());//ファイルから読み込む

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
					if(d.getType() == QuestionType.DISAPPEARANCE && d.getLink() == -1){
						textArea_2.append(trans.transrateCaption(d.getCaption())+"\n");
					}
				}

				for(Data d:datalistList.getDatalistList().get(1).getDatas()){
					if(d.getType() == QuestionType.APPEARANCE && d.getLink() == -1){
						textArea_3.append(trans.transrateCaption(d.getCaption())+"\n");
					}
				}

				imgdisplaypanel.ImageDisplay(datalistList);
			}
		}
	}
	private class CreateCoicesAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public CreateCoicesAction() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			cc.createCoices(datalistList, QuestionType.APPEARANCE);
			cc.displayCoices(coiceButton_1, coiceButton_2, coiceButton_3, coiceButton_4);
		}
	}
	private class ReadImgAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ReadImgAction_1() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示
			
			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら
				File file = filechooser.getSelectedFile();
				readImgLabel_1.setText(file.getName()); //ラベルの文字をファイル名に
			}
		}
	}
	private class ReadImgAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ReadImgAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示
			
			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら
				File file = filechooser.getSelectedFile();
				readImgLabel_2.setText(file.getName()); //ラベルの文字をファイル名に
			}
		}
	}
}
