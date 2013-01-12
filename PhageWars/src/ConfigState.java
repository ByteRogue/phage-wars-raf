import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import components.AlphaContainer;
import components.ComponentFactory;
import components.GameScrollBar;
import components.GameScrollBarUI;

import engine.Sprite;
import engine.SpritePanel;
import engine.State;
import engine.StateManager;


/**
 * State for configuration of difficulty, choosing map and viruse.
 * @author Stefan Jeremic
 *
 */
public class ConfigState extends State{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3352197009780906978L;
	/**
	 * radio buttion group.
	 */
	ButtonGroup rbGroup;
	/**
	 * Creates config state interface.
	 */
	public ConfigState(){
		SpritePanel sp = getSpritePanel();
		Sprite background = new Sprite();
		background.setImg(sp.loadImage("/resources/background.png").getImage());
		sp.getSprites().add(background);
		setLayout(new BorderLayout());
		
		JPanel upperPanel = ComponentFactory.createPanel("");
		
		//upperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 20, 0);
		upperPanel.setLayout(flowLayout);
		//Back dugme
		JButton backButton = ComponentFactory.createButton("Back");
		backButton.setPreferredSize(new Dimension(100, 30));
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StateManager.changeState(new MainMenu());
			}
		});
		upperPanel.add(backButton);
		//upperPanel.setPreferredSize(new Dimension(640,80));
		//Tezina
		JPanel tezinaPanel = ComponentFactory.createPanel("Tezina");
		tezinaPanel.setOpaque(false);
		tezinaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		tezinaPanel.setPreferredSize(new Dimension(350,50));
		rbGroup = new ButtonGroup();
		JRadioButton radioButton = ComponentFactory.createRadioButton("Easy");
		rbGroup.add(radioButton);
		tezinaPanel.add(radioButton);
		radioButton = ComponentFactory.createRadioButton("Normal");
		radioButton.setSelected(true);
		rbGroup.add(radioButton);
		tezinaPanel.add(radioButton);
		radioButton = ComponentFactory.createRadioButton("Hard");
		rbGroup.add(radioButton);
		tezinaPanel.add(radioButton);

		
		upperPanel.add(tezinaPanel);
		//Play dugme
		JButton playButton = ComponentFactory.createButton("Play");
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StateManager.changeState(new GameState(rbGroup.getSelection().getActionCommand()));
			}
		});
		playButton.setPreferredSize(new Dimension(100, 30));
		upperPanel.add(playButton);
		
		add(new AlphaContainer(upperPanel), BorderLayout.SOUTH);
		//Virus panel
		JPanel virusSelectionPanel = ComponentFactory.createPanel("Izaberi virus");
		virusSelectionPanel.setLayout(new BorderLayout());
		virusSelectionPanel.setPreferredSize(new Dimension(270, 300));
		
		DefaultListModel virusListModel = new DefaultListModel();
		for (int i = 1; i < 7; i++)
			virusListModel.addElement("Virus " + i);
		
		JList virusList = ComponentFactory.createList(virusListModel);
		JScrollPane scrollPane = new JScrollPane(virusList);
		scrollPane.setPreferredSize(new Dimension(180, 120));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		virusSelectionPanel.add(scrollPane, BorderLayout.SOUTH);
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/resources/virus.png"));
		virusSelectionPanel.add(new JLabel(ii),BorderLayout.NORTH);
		
		add(virusSelectionPanel, BorderLayout.WEST);
		
		//Map panel
		JPanel mapSelectionPanel = ComponentFactory.createPanel("Izaberi mapu");
		mapSelectionPanel.setLayout(new BorderLayout());
		mapSelectionPanel.setPreferredSize(new Dimension(340, 300));
		
		DefaultListModel mapListModel = new DefaultListModel();
		for (int i = 1; i < 30; i++)
			mapListModel.addElement("Mapa " + i);
		
		JList mapList = ComponentFactory.createList(mapListModel);
		scrollPane = new JScrollPane(mapList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUI(new GameScrollBarUI());
		scrollPane.setVerticalScrollBar(new GameScrollBar(JScrollBar.VERTICAL));
		scrollPane.setOpaque(true);
		scrollPane.setPreferredSize(new Dimension(120, 120));
		
		mapSelectionPanel.add(scrollPane, BorderLayout.SOUTH);
		
		ii = new ImageIcon(this.getClass().getResource("/resources/mapa.png"));
		mapSelectionPanel.add(new JLabel(ii),BorderLayout.NORTH);
		
		add(mapSelectionPanel, BorderLayout.EAST);
		
		
	}

}
