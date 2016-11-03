package createCogitiveTask;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;

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
	
	
	/**
	 * Create the panel.
	 */
	public CoicesPanel() {
		setLayout(null);
		coiceButton_1 = new JButton("1");
		coiceButton_1.setBounds(110, 12, 201, 23);
		add(coiceButton_1);

		coiceButton_2 = new JButton("2");
		coiceButton_2.setBounds(335, 12, 218, 23);
		add(coiceButton_2);

		coiceButton_3 = new JButton("3");
		coiceButton_3.setBounds(110, 47, 201, 23);
		add(coiceButton_3);

		coiceButton_4 = new JButton("4");
		coiceButton_4.setBounds(335, 47, 218, 23);
		coiceButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(coiceButton_4);

		createButton = new JButton("push");
		createButton.setBounds(24, 28, 60, 23);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		createButton.setAction(action_1);
		add(createButton);
		
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
			cc.createCoices(datalistlist, QuestionType.APPEARANCE);
			cc.displayCoices(coiceButton_1, coiceButton_2, coiceButton_3, coiceButton_4);
		}
	}

}
