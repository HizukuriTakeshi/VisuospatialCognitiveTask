package createCogitiveTask;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CoicesPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton coiceButton_1;
	private JButton coiceButton_2;
	private JButton coiceButton_3;
	private JButton coiceButton_4;
	private JButton createButton;
	private final Action action_1 = new CreateCoicesAction();

	private ChoicesCreate cc;
	private DataListList datalistlist;
	private final Action action = new SwingAction_0();
	private final Action action_2 = new SwingAction_1();
	private final Action action_3 = new SwingAction_2();
	private final Action action_4 = new SwingAction_3();
	private JButton btnNewButton;
	private final Action action_5 = new SwingAction();

	int times=0;
	private JLabel questionLabel;

	/**
	 * Create the panel.
	 */
	public CoicesPanel() {
		setLayout(null);
		coiceButton_1 = new JButton("1");
		coiceButton_1.setBounds(96, 37, 201, 23);
		coiceButton_1.setAction(action);
		add(coiceButton_1);

		coiceButton_2 = new JButton("2");
		coiceButton_2.setBounds(321, 37, 218, 23);
		coiceButton_2.setAction(action_2);
		add(coiceButton_2);

		coiceButton_3 = new JButton("3");
		coiceButton_3.setBounds(96, 72, 201, 23);
		coiceButton_3.setAction(action_3);
		add(coiceButton_3);

		coiceButton_4 = new JButton("4");
		coiceButton_4.setBounds(321, 72, 218, 23);
		coiceButton_4.setAction(action_4);

		add(coiceButton_4);

		createButton = new JButton("push");
		createButton.setBounds(12, 53, 60, 23);

		createButton.setAction(action_1);
		add(createButton);

		btnNewButton = new JButton("New button");
		btnNewButton.setBounds(551, 53, 53, 23);
		btnNewButton.setAction(action_5);
		add(btnNewButton);

		questionLabel = new JLabel("");
		questionLabel.setBounds(96, 10, 440, 13);
		add(questionLabel);

		//選択肢作成クラス
				cc = new ChoicesCreate();
	}

	public void setDataListList(DataListList datalistlist){
		this.datalistlist = datalistlist;
	}


	private class CreateCoicesAction extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public CreateCoicesAction() {
			putValue(NAME, "出題");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			times = 0;
			cc.createCoices(datalistlist, QuestionType.APPEARANCE);

			questionLabel.setText("昔の様子と比べて、出現した物体について説明した文を選んでください。");

			cc.displayCoices(coiceButton_1, coiceButton_2, coiceButton_3, coiceButton_4, times);
		}
	}

	private class SwingAction_0 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_0() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(cc.getCoiceslist().getDatalistList().get(times).getDatas().get(0).getType() == QuestionType.APPEARANCE){
				System.out.println("ok0");
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(cc.getCoiceslist().getDatalistList().get(times).getDatas().get(1).getType() == QuestionType.APPEARANCE){
				System.out.println("ok1");
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(cc.getCoiceslist().getDatalistList().get(times).getDatas().get(2).getType() == QuestionType.APPEARANCE){
				System.out.println("ok2");
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "SwingAction_3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(cc.getCoiceslist().getDatalistList().get(times).getDatas().get(0).getType() == QuestionType.APPEARANCE){
				System.out.println("ok3");
			}
		}
	}
	private class SwingAction extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "次");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			times++;
			if(cc.displayCoices(coiceButton_1, coiceButton_2, coiceButton_3, coiceButton_4, times)!=0){
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton);
				JOptionPane.showMessageDialog(f,"問題終了","確認",JOptionPane.PLAIN_MESSAGE);

			}


		}
	}
}
