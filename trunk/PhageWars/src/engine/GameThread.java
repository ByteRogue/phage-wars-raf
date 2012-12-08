package engine;

import core.Benchmark;



public class GameThread implements Runnable {
	private final int DELAY = 50;
	private Thread animator;
	
	public GameThread(){
		animator = new Thread(this);
		animator.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        
        Benchmark.getInstance().start("Phage Wars chart");
        Benchmark.getInstance().addSeries("Memory usage in MB");
        Benchmark.getInstance().addSeries("Total number of cells");

        while (true) {
        	
        	Double memory= (Runtime.getRuntime().totalMemory() -Runtime.getRuntime().freeMemory()) / (1024.0*1024.0);
        	
        	
        	Benchmark.getInstance().addValue("Memory usage in MB",memory);

            StateManager.getInstance().getCurrentState().cycle();
            StateManager.getInstance().getCurrentState().repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
	}

}
