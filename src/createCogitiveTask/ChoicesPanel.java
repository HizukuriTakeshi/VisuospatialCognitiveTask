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

public class ChoicesPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton choiceButton_1;
	private JButton choiceButton_2;
	private JButton choiceButton_3;
	private JButton choiceButton_4;
	private JButton createButton;
	private final Action action_1 = new CreateChoicesAction();

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
	public ChoicesPanel() {
		setLayout(null);
		choiceButton_1 = new JButton("1");
		choiceButton_1.setBounds(96, 37, 201, 23);
		choiceButton_1.setAction(action);
		add(choiceButton_1);

		choiceButton_2 = new JButton("2");
		choiceButton_2.setBounds(321, 37, 218, 23);
		choiceButton_2.setAction(action_2);
		add(choiceButton_2);

		choiceButton_3 = new JButton("3");
		choiceButton_3.setBounds(96, 72, 201, 23);
		choiceButton_3.setAction(action_3);
		add(choiceButton_3);

		choiceButton_4 = new JButton("4");
		choiceButton_4.setBounds(321, 72, 218, 23);
		choiceButton_4.setAction(action_4);

		add(choiceButton_4);

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


	private class CreateChoicesAction extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public CreateChoicesAction() {
			putValue(NAME, "出題");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			times = 0;
			cc.createChoices(datalistlist, QuestionType.APPEARANCE);

			questionLabel.setText("昔の様子と比べて、出現した物体について説明した文を選んでください。");

			cc.displayChoices(choiceButton_1, choiceButton_2, choiceButton_3, choiceButton_4, times);
		}
	}

	private class SwingAction_0 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_0() {
			putValue(NAME, "1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFrame f = (JFrame) SwingUtilities.getWindowAncestor(choiceButton_1);
			if(cc.getChoiceslist().getDatalistList().get(times).getDatas().get(0).getType() == QuestionType.APPEARANCE){
				JOptionPane.showMessageDialog(f,"正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(f,"不正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFrame f = (JFrame) SwingUtilities.getWindowAncestor(choiceButton_2);

			if(cc.getChoiceslist().getDatalistList().get(times).getDatas().get(1).getType() == QuestionType.APPEARANCE){
				JOptionPane.showMessageDialog(f,"正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(f,"不正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFrame f = (JFrame) SwingUtilities.getWindowAncestor(choiceButton_3);

			if(cc.getChoiceslist().getDatalistList().get(times).getDatas().get(2).getType() == QuestionType.APPEARANCE){
				JOptionPane.showMessageDialog(f,"正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(f,"不正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}

		}
	}
	private class SwingAction_3 extends AbstractAction {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "4");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JFrame f = (JFrame) SwingUtilities.getWindowAncestor(choiceButton_4);
			if(cc.getChoiceslist().getDatalistList().get(times).getDatas().get(3).getType() == QuestionType.APPEARANCE){
				JOptionPane.showMessageDialog(f,"正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(f,"不正解","メッセージ",JOptionPane.PLAIN_MESSAGE);
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
			if(cc.displayChoices(choiceButton_1, choiceButton_2, choiceButton_3, choiceButton_4, times)!=0){
				JFrame f = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton);
				JOptionPane.showMessageDialog(f,"問題終了","確認",JOptionPane.PLAIN_MESSAGE);

			}


		}
	}
}
