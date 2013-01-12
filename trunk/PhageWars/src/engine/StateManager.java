package engine;
import javax.swing.JFrame;



/**
 * Manages states.
 * @author Stefan Jeremic
 *
 */
public class StateManager {
	/**
	 * Currently active state.
	 */
	private static State currentState;
	/**
	 * static state manager instance.
	 */
	private static StateManager stateManger;
	/**
	 * app that this state manager belongs to.
	 */
	JFrame app;
	/**
	 * State manager initialization.
	 * @param application
	 */
	public StateManager(JFrame application){
		app = application;
	}
	/**
	 * Method used to change state.
	 * @param state will become the next current state
	 */
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
	/**
	 * @return current state
	 */
	public static State getCurrentState(){
		return currentState;
	}
	/**
	 * calls the contstructor.
	 * @param app
	 */
	public static void init(JFrame app) {
		stateManger = new StateManager(app);
	}
	/**
	 * @return static state manager instance
	 */
	public static StateManager getInstance() {
		return stateManger;
	}
}
