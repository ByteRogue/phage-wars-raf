import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.ComponentFactory;
import core.Benchmark;

import engine.Sprite;
import engine.SpritePanel;
import engine.State;
import engine.StateManager;


public class MainMenu extends State{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3088890058631223710L;

	public MainMenu() {
		SpritePanel sp = getSpritePanel();
		Sprite background = new Sprite();
		background.setImg(sp.loadImage("/resources/background.png").getImage());
		sp.getSprites().add(background);
		Sprite s = new TestSprite();
		s.setImg(sp.loadImage("/resources/header.png").getImage());
		sp.getSprites().add(s);
		//setSpritePanel(new MenuSpritePanel());
		setLayout(new BorderLayout());
		JPanel emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		emptyPanel.setPreferredSize(new Dimension(200, 100));
		add(emptyPanel, BorderLayout.WEST);
		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		emptyPanel.setPreferredSize(new Dimension(200, 100));
		add(emptyPanel, BorderLayout.EAST);
		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		emptyPanel.setPreferredSize(new Dimension(100, 100));
		add(emptyPanel, BorderLayout.NORTH);
		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
		emptyPanel.setPreferredSize(new Dimension(100, 100));
		add(emptyPanel, BorderLayout.SOUTH);
		JPanel mainPanel = ComponentFactory.createPanel("");
		mainPanel.setMaximumSize(new Dimension(200, 400));
		mainPanel.setPreferredSize(new Dimension(200, 400));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);

		JButton button =ComponentFactory.createButton("Play game");// new JButton("Play game");
		button.setAlignmentX(0.5f);
		button.setMaximumSize(new Dimension(100, 30));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StateManager.changeState(new ConfigState());
			}
		});
		
		mainPanel.add(Box.createVerticalStrut(5));
		JLabel logo = new JLabel(getSpritePanel().loadImage("/resources/logo.png"));
		logo.setAlignmentX(0.5f);
		mainPanel.add(logo);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(button);
		mainPanel.add(Box.createVerticalGlue());
		
		button = ComponentFactory.createButton("Editor");
		button.setAlignmentX(0.5f);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StateManager.changeState(new EditorState());
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		mainPanel.add(button);
		mainPanel.add(Box.createVerticalGlue());
		
		button = ComponentFactory.createButton("Help");
		button.setAlignmentX(0.5f);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StateManager.changeState(new HelpState());
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		mainPanel.add(button);
		mainPanel.add(Box.createVerticalGlue());
		
		button = ComponentFactory.createButton("Exit");
		button.setAlignmentX(0.5f);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Benchmark.getInstance().stop();
				Application.getApp().dispose();
				System.exit(0);
			}
		});
		button.setMaximumSize(new Dimension(100, 30));
		mainPanel.add(button);
		mainPanel.add(Box.createVerticalStrut(20));
	}
}
