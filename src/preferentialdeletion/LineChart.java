/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preferentialdeletion;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

//Standard Class for Plotting, It follows a demo of XY series at https://stackoverflow.com/questions/16714738/xy-plotting-with-java

/**
 *
 * @author Mnassar Alyami
 */
public class LineChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3752045447709559783L;
	public LineChart(String applicationTitle) {
		super(applicationTitle);
	}
	
        public void LineChart1( String applicationTitle, String chartTitle, ArrayList<Integer> X, ArrayList<Double> Y, String label ) {
		//super(applicationTitle);
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle , "Graph Number" , "Assortativity" , createDataset(X,Y,label) , PlotOrientation.VERTICAL , true , true , false);

		ChartPanel chartPanel = new ChartPanel( xylineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		final XYPlot plot = xylineChart.getXYPlot( );

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
		renderer.setSeriesPaint( 0 , Color.RED );
		renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
		plot.setRenderer( renderer ); 
		setContentPane( chartPanel ); 
	}

	private XYDataset createDataset(ArrayList<Integer> X, ArrayList<Double> Y, String label) {
		final XYSeries data = new XYSeries(label );          
		for(int i = 0 ; i < X.size() ; i++) {
			data.add(X.get(i),Y.get(i));
		}
		final XYSeriesCollection dataset = new XYSeriesCollection( );          
		dataset.addSeries( data );          
		return dataset;
	}


}
