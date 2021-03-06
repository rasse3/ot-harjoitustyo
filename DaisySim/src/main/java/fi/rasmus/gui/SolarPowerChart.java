


package fi.rasmus.gui;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import fi.rasmus.logic.*;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javafx.application.Application;
import javafx.application.Platform;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.ChartTheme;
import javax.swing.JPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.AnchorPane;
import org.knowm.xchart.internal.chartpart.Chart;
/**
 *
 * @author Rasmus
 */
public class SolarPowerChart {

    private XYChart xyChart;
    private XYChart xyChart2;
    private XYChart xyChart3;
    private XYChart xyChart4;

    private AnchorPane chartArea;
    
    private List<Double> yData;
    private List<Double> yData2;
    private List<Double> yData3;
    private List<Double> yData4;
    public static final String SERIES_NAME = "Kelvin";
    public static final String SERIES_NAME2 = "Wattia";
    public static final String SERIES_NAME3 = "Tummia kasveja prosenttina pinta-alasta";
    public static final String SERIES_NAME4 = "Vaaleita kasvjea prosenttina pinta-alasta";
    public static final String X_NAME = "Päivä";
    public Logger logger;
    public PlanetControl pc;
    public StarControl sc;
    public Day day;
    

    public SolarPowerChart(Logger logger, PlanetControl pc, StarControl sc, Day day) {
        this.logger = logger;
        this.pc = pc;
        this.sc = sc;
        this.day = day;
    }

    public void go() {

        final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<XYChart>(getChart());
        swingWrapper.displayChart();

        final SwingWrapper<XYChart> swingWrapper2 = new SwingWrapper<XYChart>(getChart2());
        swingWrapper2.displayChart();
        
        final SwingWrapper<XYChart> swingWrapper3 = new SwingWrapper<XYChart>(getChart3());
        swingWrapper3.displayChart();
        
        final SwingWrapper<XYChart> swingWrapper4 = new SwingWrapper<XYChart>(getChart4());
        swingWrapper4.displayChart();
        
    
        // Simulate a data feed
        TimerTask chartUpdaterTask = new TimerTask() {

            @Override
            public void run() {

                updateData();
                day.executeADay();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        swingWrapper.repaintChart();
                        swingWrapper2.repaintChart();
                        swingWrapper3.repaintChart();
                        swingWrapper4.repaintChart();
                    }
                });
                
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(chartUpdaterTask, 0, 500);
    }

    public XYChart getChart() {

        yData = getRandomData(5);
        

        // Create Chart
        xyChart = new XYChartBuilder().width(500).height(400).theme(ChartTheme.Matlab).title("Lämpötila planeetalla").build();
        xyChart.addSeries(SERIES_NAME, null, yData);
        
        

        return xyChart;
    
    }
        
         public XYChart getChart2() {

        yData2 = getRandomData2(5);

        // Create Chart
        xyChart2 = new XYChartBuilder().width(500).height(400).theme(ChartTheme.Matlab).title("Tähden teho").build();
       
        xyChart2.addSeries(SERIES_NAME2, null, yData2);

        return xyChart2;
    }

            public XYChart getChart3() {

        yData3 = getRandomData3(5);

        // Create Chart
        xyChart3 = new XYChartBuilder().width(500).height(400).theme(ChartTheme.Matlab).title("Tummat kasvit").build();
       
        xyChart3.addSeries(SERIES_NAME3, null, yData3);

        return xyChart3;
    }

            
            public XYChart getChart4() {

        yData4 = getRandomData4(5);

        // Create Chart
        xyChart4 = new XYChartBuilder().width(500).height(400).theme(ChartTheme.Matlab).title("Vaaleat kasvit").build();
       
        xyChart4.addSeries(SERIES_NAME4, null, yData4);

        return xyChart4;
    }

         
         
         
    public void updateData() {

        // Get some new data
        List<Double> newData = getRandomData(1);
        List<Double> newData2 = getRandomData2(1);
        List<Double> newData3 = getRandomData3(1);
        List<Double> newData4 = getRandomData4(1);
        
        
        

        yData.addAll(newData);
        yData2.addAll(newData2);
        yData3.addAll(newData3);
        yData4.addAll(newData4);

        // Limit the total number of points
        while (yData.size() > 120) {
            yData.remove(0);
        }
        while (yData2.size() > 120) {
            yData2.remove(0);
        }
        while (yData3.size() > 120) {
            yData3.remove(0);
        }
        while (yData4.size() > 120) {
            yData4.remove(0);
        }

        xyChart.updateXYSeries(SERIES_NAME, null, yData, null);
        xyChart2.updateXYSeries(SERIES_NAME2, null, yData2, null);
        xyChart3.updateXYSeries(SERIES_NAME3, null, yData3, null);
        xyChart4.updateXYSeries(SERIES_NAME4, null, yData4, null);

    }

    public List<Double> getRandomData(int numPoints) {

        List<Double> data = new CopyOnWriteArrayList<Double>();
        for (int i = 0; i < numPoints; i++) {

           
            
            data.add(logger.getNewestPlanetTemperature());
                }
        
        return data;
    }

    public List<Double> getRandomData2(int numPoints) {

        List<Double> data = new CopyOnWriteArrayList<Double>();
        for (int i = 0; i < numPoints; i++) {
            
            
            data.add(logger.getNewestSolarLuminosity());
                }
        
        return data;
    }
    
      public List<Double> getRandomData3(int numPoints) {

        List<Double> data = new CopyOnWriteArrayList<Double>();
        for (int i = 0; i < numPoints; i++) {
            
            
            data.add(logger.getNewestCoverage(logger.plantsAsList.get(0)));
                }
        
        return data;
    }
    
    
      public List<Double> getRandomData4(int numPoints) {

        List<Double> data = new CopyOnWriteArrayList<Double>();
        for (int i = 0; i < numPoints; i++) {
            
            
            data.add(logger.getNewestCoverage(logger.plantsAsList.get(1)));
                }
        
        return data;
    }
    
      private void showChart(XYChart chart) {
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
 
        // for embeding swing in javafx
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
 
        // for resizing plot to window
        AnchorPane.setLeftAnchor(swingNode, 100.0);
        AnchorPane.setRightAnchor(swingNode, 100.0);
        AnchorPane.setTopAnchor(swingNode, 0.0);
        AnchorPane.setBottomAnchor(swingNode, 0.0);
        
        
        // add chart to the chart area
        chartArea.getChildren().add(swingNode);
 
    }
   
    
   
    
    
    
}
