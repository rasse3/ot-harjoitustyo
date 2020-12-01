/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.List;
import java.util.ArrayList;
import org.knowm.xchart.style.Styler.ChartTheme;



/**
 *
 * @author Rasmus
 */
public class SolarPowerChart {
    

    
    
  private XYChart xyChart;

  private List<Double> yData;
  public static final String SERIES_NAME = "Kelvin";
  public static final String X_NAME = "Päivä";
  public Logger logger;
 public PlanetControl pc;
 public StarControl sc;
 public Day day;

  
  
  public SolarPowerChart(Logger logger, PlanetControl pc, StarControl sc, Day day){
      this.logger = logger;
      this.pc = pc;
      this.sc = sc;
      this.day = day;
  }

  public static void main(String[] args) {

    // Setup the panel
  //  final SolarPowerChart solarChart1 = new SolarPowerChart(new Logger(), new PlanetControl(1), new StarControl(), new Day(new PlanetControl(1), new StarControl, new Logger() );
  //  solarChart1.go();
  }

  public void go(){

    final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<XYChart>(getChart());
    swingWrapper.displayChart();

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

  public void updateData() {

    // Get some new data
    List<Double> newData = getRandomData(1);

    yData.addAll(newData);

    // Limit the total number of points
    while (yData.size() > 20) {
      yData.remove(0);
    }

    xyChart.updateXYSeries(SERIES_NAME, null, yData, null);
  }

  public List<Double> getRandomData(int numPoints) {

    List<Double> data = new CopyOnWriteArrayList<Double>();
    for (int i = 0; i < numPoints; i++) {
      
      data.add(logger.getNewestPlanetTemperature());
      
    }
    return data;
  }
    
    
    
}
