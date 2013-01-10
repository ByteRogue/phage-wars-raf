import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
	private String mapName = null;
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
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				save();
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		commandPanel.add(Box.createVerticalStrut(20));
		button = new JButton("Save As..");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveAs();
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		commandPanel.add(Box.createVerticalStrut(20));
		button = new JButton("Load");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				load();
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		commandPanel.add(button);
		add(commandPanel, BorderLayout.EAST);
		
		
	}
	private void load(){
		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("maps"));
		fc.showOpenDialog(this);
		File file = fc.getSelectedFile();
		if (file != null){
			try {
				DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				try {
					editPanel.clear();
					int n = in.readInt();
					for (int i = 0; i < n; i++) {
						Planet planet = new Planet();
						planet.setX(in.readFloat());
						planet.setY(in.readFloat());
						planet.setRadius(in.readInt());
						planet.setNumber(in.readInt());
						planet.setOwner(in.readInt());
						editPanel.planets.add(planet);
					}
					editPanel.getSprites().addAll(editPanel.planets);
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	private void saveAs() {
		mapName = (String) JOptionPane.showInputDialog(this, "Map name", "Save as..", JOptionPane.PLAIN_MESSAGE);
		save();
		
	}
	private void save() {
		if (mapName != null) {
			try {
				DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("maps/"+mapName)));
				ArrayList<Planet> planets = editPanel.planets;
				try {
					out.writeInt(planets.size());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Iterator<Planet> it = planets.iterator();
				while (it.hasNext()){
					Planet p = it.next();
					try {
						out.writeFloat(p.getX());
						out.writeFloat(p.getY());
						out.writeInt(p.getRadius());
						out.writeInt(p.getNumber());
						out.writeInt(p.getOwner());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else
			saveAs();
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
