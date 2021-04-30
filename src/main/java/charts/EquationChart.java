package charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public class EquationChart  extends JFrame {
    public EquationChart(Chart chart){
        JFreeChart jFreeChart = chart.getChart();
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("LineChart");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
