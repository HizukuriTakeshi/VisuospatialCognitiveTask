package createCogitiveTask;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class ConfirmPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Action action = new SwingAction();
	private JComboBox<Object> comboBox;
	private JButton button;
	
	private DataListList datalistlist;
	
	
	/**
	 * Create the panel.
	 */
	public ConfirmPanel() {
		setLayout(null);

		String[] caption = {"before","after","disappearance", "appearance"};


		comboBox = new JComboBox<Object>(caption);
		comboBox.setBounds(0, 12, 111, 22);
		add(comboBox);

		button = new JButton("確認");
		button.setAction(action);
		button.setBounds(146, 12, 68, 23);
		add(button);

	}

	public void setDataListList(DataListList datalistlist){
		this.datalistlist = datalistlist;
	}
	

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "確認");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int index = comboBox.getSelectedIndex();

			
		    StringBuffer buf = new StringBuffer();
		    buf.append("<HTML>");
			
			switch (index){
			case 0:
				
				for(Data d: datalistlist.getDatalistList().get(0).getDatas()){					
					buf.append(d.getCaption());
					buf.append("<BR>");
				}
				
				break;
			case 1:
				for(Data d: datalistlist.getDatalistList().get(1).getDatas()){
					buf.append(d.getCaption());
					buf.append("<BR>");
				}

				break;
			case 2:
				for(Data d:datalistlist.getDatalistList().get(0).getDatas()){
					if(d.getType() == QuestionType.DISAPPEARANCE && d.getLink() == -1){
						buf.append(d.getCaption());
						buf.append("<BR>");
					}
				}
				break;
			case 3:
				for(Data d:datalistlist.getDatalistList().get(1).getDatas()){
					if(d.getType() == QuestionType.APPEARANCE && d.getLink() == -1){
						buf.append(d.getCaption());
						buf.append("<BR>");
					}
				}
				break;
			}
			
			

			
			
			buf.append("</HTML>");
			String str = buf.toString();
			JLabel label = new JLabel(str);
			
			JFrame f = (JFrame) SwingUtilities.getWindowAncestor(label);
			JOptionPane.showMessageDialog(f,label,"確認",JOptionPane.PLAIN_MESSAGE);
			
		}
	}
}
