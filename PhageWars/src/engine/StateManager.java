package engine;
import javax.swing.JFrame;



public class StateManager {
	private static State currentState;
	private static StateManager stateManger;
	JFrame app;
	public StateManager(JFrame application){
		app = application;
	}
	public static void changeState(State state) {
		if (currentState != null) {
			//currentState.setThreadRun(false);
			stateManger.app.getContentPane().remove(currentState);
		}
		currentState = state;
		//currentState.setThreadRun(true);
		stateManger.app.getContentPane().add(currentState);
		currentState.setFocusable(true);
		currentState.requestFocusInWindow();
		stateManger.app.validate();
	}
	public static State getCurrentState(){
		return currentState;
	}
	public static void init(JFrame app) {
		stateManger = new StateManager(app);
	}
	public static StateManager getInstance(){
		return stateManger;
	}
}
