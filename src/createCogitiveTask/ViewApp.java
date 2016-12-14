package createCogitiveTask;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

//
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;

import densecapProcess.DenseCapProcess;
import fileUtils.FileUtils;
import java.awt.event.ActionListener;

public class ViewApp {

	private JFrame frame;
	private JButton readFileButton;
	private final Action action = new ReadFileAction();
	private ImageDisplayPanel imgdisplaypanel;

	
	private DataListList datalistList;
	private JButton readImgButton_1;
	private JButton readImgButton_2;
	private final Action action_2 = new ReadImgAction_1();
	private final Action action_3 = new ReadImgAction_2();
	private JLabel readImgLabel_1;
	private JLabel readImgLabel_2;
	private final Action action_4 = new SwingAction();


	private File in1;
	private File in2;
	private ConfirmPanel confirmpanel;
	private ChoicesPanel choicespanel;
	private final Action action_1 = new SwingAction_1();
	
	

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
		frame.setBounds(100, 100, 783, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		readFileButton = new JButton("New button");
		readFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		readFileButton.setBounds(546, 148, 152, 20);
		readFileButton.setAction(action);
		frame.getContentPane().add(readFileButton);

		imgdisplaypanel = new ImageDisplayPanel();
		imgdisplaypanel.setBounds(52, 88, 419, 308);
		frame.getContentPane().add(imgdisplaypanel);

		readImgButton_1 = new JButton("画像1");
		readImgButton_1.setBounds(209, 12, 108, 23);
		readImgButton_1.setAction(action_2);
		frame.getContentPane().add(readImgButton_1);

		readImgButton_2 = new JButton("画像2");
		readImgButton_2.setBounds(520, 12, 108, 23);
		readImgButton_2.setAction(action_3);
		frame.getContentPane().add(readImgButton_2);

		readImgLabel_1 = new JLabel("");
		readImgLabel_1.setBounds(52, 17, 139, 13);
		frame.getContentPane().add(readImgLabel_1);

		readImgLabel_2 = new JLabel("");
		readImgLabel_2.setBounds(335, 17, 167, 13);
		frame.getContentPane().add(readImgLabel_2);

		JButton densecapButton = new JButton("DenseCap");
		densecapButton.setBounds(546, 114, 152, 23);
		densecapButton.setAction(action_4);
		frame.getContentPane().add(densecapButton);
		
		confirmpanel = new ConfirmPanel();
		confirmpanel.setBounds(513, 180, 227, 39);
		frame.getContentPane().add(confirmpanel);
		
		choicespanel = new ChoicesPanel();
		choicespanel.setBounds(18, 410, 753, 110);
		frame.getContentPane().add(choicespanel);
		
		JButton btnNewButton = new JButton("輪郭抽出");
		btnNewButton.setAction(action_1);
		btnNewButton.setBounds(546, 68, 152, 23);
		frame.getContentPane().add(btnNewButton);
	
		datalistList = new DataListList();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

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

				
			File file1 = new File("./imgs/results1.json");
			File file2 = new File("./imgs/results2.json");
			
			if (file1.exists() && file2.exists()){ //ファイルが選択されたら


				JsonDataReader jsonReader = new JsonDataReader();//JSON形式ファイル読み込みクラス


				DenseCapList densecapList1 = new DenseCapList();
				densecapList1 = jsonReader.jsonDataRead("./imgs/results1.json");//ファイルから読み込む

				//二つ目のjsonファイルを読み込み、datalistlist1に datalistlist2のdatalistを追加することで一つのデータリストリストに過去現在の説明文を入れる。
				DenseCapList densecapList2 = new DenseCapList();
				densecapList2 = jsonReader.jsonDataRead("./imgs/results2.json");//ファイルから読み込む

				DataListList tmp = new DataListList();

				tmp = densecapList2.toDataListList();
				
				datalistList = densecapList1.toDataListList();
				datalistList.addDataList(tmp.getDatalistList().get(0));

				//差分と説明文BB比較
				CompareBoundingBox cbb = new CompareBoundingBox();
				cbb.compareBoundingBox(datalistList, QuestionType.APPEARANCE);
				
				
				//候補とキャプション群比較
				CompareCap cc = new CompareCap();
				cc.checkCaption0(datalistList);
				for(Data d: cc.getDatalist0().getDatas()){
					System.out.println(d.getCaption());
				}
				System.out.println("test");
									
				

				
				
				confirmpanel.setDataListList(datalistList);
				choicespanel.setDataListList(datalistList);
				imgdisplaypanel.ImageDisplay(datalistList);
			}
		}
	}
	
	
	private class ReadImgAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ReadImgAction_1() {
			putValue(NAME, "画像1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示

			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら

				in1 = filechooser.getSelectedFile();	
				readImgLabel_1.setText(in1.getName()); //ラベルの文字をファイル名に

				File out1 = new File("./imgs/"+in1.getName());
				try {
					FileUtils.copyFile(in1, out1);
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}

			}
		}
	}
	private class ReadImgAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ReadImgAction_2() {
			putValue(NAME, "画像2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {


			JFileChooser filechooser = new JFileChooser(); // ファイル選択用クラス

			int selected = filechooser.showOpenDialog(frame); //「開く」ダイアログ表示

			if (selected == JFileChooser.APPROVE_OPTION){ //ファイルが選択されたら

				in2 = filechooser.getSelectedFile();
				readImgLabel_2.setText(in2.getName()); //ラベルの文字をファイル名に

				File out2 = new File("./imgs/"+in2.getName());
				try {
					FileUtils.copyFile(in2, out2);
				} catch (IOException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}				
			}


		}
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "DenseCap");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {

			String filePath1 = "./imgs/tmp1/";
			String filePath2 = "./imgs/tmp2/";
			
			File tmpdir1 = new File(filePath1);
			tmpdir1.mkdir();

			File out1 = new File(filePath1+in1.getName());
			try {
				FileUtils.copyFile(in1, out1);
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			File tmpdir2 = new File(filePath2);
			tmpdir2.mkdir();
			File out2 = new File(filePath2+in2.getName());
			try {
				FileUtils.copyFile(in2, out2);
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			try {
				DenseCapProcess.DenseCapScript1();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			try {
				DenseCapProcess.DenseCapScript2();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			FileUtils.delete(tmpdir1);
			FileUtils.delete(tmpdir2);

		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "輪郭抽出");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {

//			String path1 = "./imgs/"+in1.getName();
//			String path2= "./imgs/"+in2.getName();
			
			//FindDiff fd = new FindDiff();
			//fd.selectChangeContour(path1, path2);
			System.out.println("このボタンの機能はありません");
			
		}
	}
}
