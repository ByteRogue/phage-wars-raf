package core;

import java.util.LinkedHashMap;
import java.util.Map;


public class Benchmark {
	private static Benchmark instance = null;
	protected Benchmark() {
		// Exists only to defeat instantiation.
		}
    public static Benchmark getInstance() {
       if(instance == null) {
          instance = new Benchmark();
       }
       return instance;
    }
    
    
    
    private Map<String,Map<Double,Double>> chartData;
    private Long startTime;
    private String chartName;
    private boolean started=false;
    
    public void start(String chartName){
    	System.out.println("Benchmark start");
    	this.chartName=chartName;
    	chartData=new LinkedHashMap<String, Map<Double,Double>>();
    	startTime=System.currentTimeMillis();
    	started=true;
    }
    
    public void addSeries(String seriesName){
    	if(started){
    		chartData.put(seriesName, new LinkedHashMap<Double, Double>());
    	}
    }
    public void addValue(String seriesName, Double value){
    	if(started){
	    	Long currentTime=System.currentTimeMillis();
	    	
	    	Double secondsFromStart=(currentTime-startTime)/1000.0;
	    	
	    	
	    	chartData.get(seriesName).put(secondsFromStart, value);
    	}
    }
    
    
    public void stop(){
    	started=false;
    	System.out.println("Benchmark stop");
    	ExcelFileUtils.makeExcelFile(chartData, chartName);
    }
}
