import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import engine.State;
import engine.StateManager;


/**
 * State for instructions.
 * @author Stefan Jeremic
 *
 */
public class HelpState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7959973683031670289L;

	/**
	 * Creates interface.
	 */
	public HelpState(){
		super();
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StateManager.changeState(new MainMenu());
			}
		});
		add(backButton);
		add(new JLabel("Under construction!"));
	}
}
