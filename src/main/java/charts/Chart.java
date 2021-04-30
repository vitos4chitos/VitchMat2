package charts;

import equations.Equations;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Chart {
    private final JFreeChart chart;
    public Chart(Equations equations, double leftBorder, double rightBorder, int numOfFunction){
        XYSeries series = new XYSeries("series");
        for(double i = leftBorder; i<= rightBorder;
            i+= (rightBorder - leftBorder)/100){
            series.add(i,equations.getValue(numOfFunction, i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        chart = ChartFactory.createXYLineChart(equations.getEquation(numOfFunction), "x", "y",dataset, PlotOrientation.VERTICAL ,false, false, false);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2f));
        renderer.setSeriesShapesVisible(0,false);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeCrosshairValue(0);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairPaint(Color.BLACK);
        plot.setRangeCrosshairStroke(new BasicStroke(2f));
        plot.getDomainAxis().setRange(new Range(leftBorder, rightBorder));
        chart.setTitle(new TextTitle(equations.getEquation(numOfFunction), new Font("Serif",Font.BOLD,18)));
    }

    public JFreeChart getChart() {
        return chart;
    }
}