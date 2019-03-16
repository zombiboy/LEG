/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sodigaz.gel.View;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author issouf
 */
@ManagedBean(name="dtchartView")
@ViewScoped
public class ChartView implements Serializable{
    private LineChartModel lineModel1;
    private LineChartModel lineModel2;
    private LineChartModel zoomModel;
    private CartesianChartModel combinedModel;
    private CartesianChartModel fillToZero;
    private LineChartModel areaModel;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private DonutChartModel donutModel1;
    private DonutChartModel donutModel2;
    private MeterGaugeChartModel meterGaugeModel1;
    private MeterGaugeChartModel meterGaugeModel2;
    private BubbleChartModel bubbleModel1;
    private BubbleChartModel bubbleModel2;
    private OhlcChartModel ohlcModel;
    private OhlcChartModel ohlcModel2;
    private PieChartModel livePieModel;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private LineChartModel multiAxisModel;
    private LineChartModel dateModel;
    
    
 
    
}
