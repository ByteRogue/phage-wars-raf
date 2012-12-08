package core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ExcelFileUtils {
	
	public static void makeExcelFile(Map<String,Map<Double,Double>> chartData, String chartName){
		
		XYSeriesCollection dataset = new XYSeriesCollection();  
		
		for (Entry<String, Map<Double, Double>> seriesData : chartData.entrySet()) {
			XYSeries xySeries = new XYSeries( seriesData.getKey() );  
			dataset.addSeries(xySeries);
			for (Entry<Double, Double> pointData : seriesData.getValue().entrySet()) {
				
				xySeries.add(pointData.getKey(),pointData.getValue());
			}
		}
		
		
		JFreeChart chart=ChartFactory.createXYLineChart(chartName, "Time in seconds", "", dataset, PlotOrientation.VERTICAL, true, true, false);
		BufferedImage bImage=  chart.createBufferedImage(800, 400);
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "png", baos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] imageInByte=baos.toByteArray();
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		
		int pictureIdx = wb.addPicture(imageInByte, HSSFWorkbook.PICTURE_TYPE_PNG);
		
		CreationHelper helper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet();
		
		Drawing drawing = sheet.createDrawingPatriarch();
		
		ClientAnchor anchor = helper.createClientAnchor();
		
		anchor.setCol1(0);
		anchor.setRow1(0);
		Picture pict = drawing.createPicture(anchor, pictureIdx);
		
		pict.resize();
		
		String file = "benchmark.xls";

		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
