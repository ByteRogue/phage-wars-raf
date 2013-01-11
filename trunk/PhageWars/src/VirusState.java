import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components.ComponentFactory;
import components.GameScrollBar;
import components.GameScrollBarUI;

import engine.Sprite;
import engine.SpritePanel;
import engine.State;


public class VirusState extends State {

	private static final long serialVersionUID = -8430175730126949552L;
	private JList<String> virusList;
	private JButton addButton;
	private JButton removeButton;
	private DefaultListModel virusListModel;
	private VirusViewPanel virusPanel;
	private Color virusColor;
	private ArrayList<VirusDefinition> viruses = new ArrayList<VirusDefinition>();
	private int virusCounter;
	public VirusState(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 20,20, 20));
		SpritePanel sp = getSpritePanel();
		Sprite background = new Sprite();
		background.setImg(sp.loadImage("/resources/background.png").getImage());
		sp.getSprites().add(background);
		
		JPanel leftPanel = ComponentFactory.createPanel("");
		//leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20,20, 20));
		
		JPanel rightPanel = ComponentFactory.createPanel("");
		rightPanel.setLayout(new BorderLayout(20,20));
		virusPanel = new VirusViewPanel();
		rightPanel.add(virusPanel, BorderLayout.CENTER);
		rightPanel.setPreferredSize(new Dimension(350,400));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 55, 60, 55));
		
		JButton colorButton = ComponentFactory.createButton("Choose color");
		colorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				virusColor = JColorChooser.showDialog(VirusState.this, "Virus color", Color.white);
			}
		});
		rightPanel.add(colorButton, BorderLayout.SOUTH);
		
		JPanel buttonPanel = ComponentFactory.createPanel("");
		buttonPanel.setOpaque(false);
		leftPanel.setLayout(new BorderLayout());
		
		addButton = ComponentFactory.createButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				virusListModel.addElement("Virus "+virusCounter);
				virusCounter++;
				VirusDefinition def = new VirusDefinition();
				def.setStrength(virusPanel.getStrengthPoints());
				def.setDexterity(virusPanel.getDexterityPoints());
				def.setVitality(virusPanel.getVitalityPoints());
				def.setColor(virusColor);
				viruses.add(def);
			}
		});
		removeButton = ComponentFactory.createButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = virusList.getSelectedIndex();
				virusListModel.remove(index);
				viruses.remove(index);
				
			}
		});
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20,20, 20));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		viruses = Loader.loadViruses();
		
		virusListModel = new DefaultListModel();
		for (int i = 0; i < viruses.size(); i++)
			virusListModel.addElement("Virus " + i + 1);
		virusCounter = viruses.size();
		virusList = ComponentFactory.createList(virusListModel);
		virusList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				VirusDefinition def = viruses.get(e.getFirstIndex());
				virusPanel.setStrengthPoints(def.getStrength());
				virusPanel.setDexterityPoints(def.getDexterity());
				virusPanel.setVitalityPoints(def.getVitality());
				virusPanel.setDefinition(def);
				//System.out.println("lalala");
			}
		});
		JScrollPane scrollPane = new JScrollPane(virusList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUI(new GameScrollBarUI());
		scrollPane.setVerticalScrollBar(new GameScrollBar(JScrollBar.VERTICAL));
		scrollPane.setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(120,340));
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		//scrollPane.setBorder(BorderFactory.createEmptyBorder());
		leftPanel.add(scrollPane, BorderLayout.NORTH);
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//virusPanel.paint(g);
	}

}
