import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.State;
import engine.StateManager;


public class EditorState extends State implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293327047569637118L;
	protected EditPanel editPanel;
	public EditorState(){
		addKeyListener(this);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20,20, 20));
		editPanel = new EditPanel();
		setPaintSpritePanel(false);
		spritePanel = editPanel;
		JScrollPane scrollPane = new JScrollPane(editPanel);
		scrollPane.setPreferredSize(new Dimension(440,440));
		add(scrollPane, BorderLayout.WEST);
		
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
		JButton button;// = new JButton("Add Planet");
		/*button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);*/
		commandPanel.add(Box.createVerticalGlue());
		button = new JButton("Save");
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		commandPanel.add(Box.createVerticalStrut(20));
		button = new JButton("Save As..");
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		commandPanel.add(Box.createVerticalStrut(20));
		button = new JButton("Load");
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		add(commandPanel, BorderLayout.EAST);
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
			StateManager.changeState(new MainMenu());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
