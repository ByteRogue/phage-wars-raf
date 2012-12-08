import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import core.Benchmark;
import engine.GameThread;
import engine.StateManager;

public class Application extends JFrame{
	/**
	 * 
	 */
	private static Application app;
	private static final long serialVersionUID = -2517616123689799182L;
	private GameThread gameThread;
	public Application(){
		super("Phage Wars");
		StateManager.init(this);
		
		//getContentPane().add(new State());
		setSize(640, 480);
		//pack();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				Benchmark.getInstance().stop();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		StateManager.changeState(new MainMenu());
		gameThread = new GameThread();
		
		
		
	}
	
	public GameThread getGameThread() {
		return gameThread;
	}

	public static void main(String args[]){
		
		app = new Application();
	}
	public static Application getApp(){
		return app;
	}
}
