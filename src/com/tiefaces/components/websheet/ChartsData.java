package com.tiefaces.components.websheet;

import java.awt.image.BufferedImage;
import java.util.Map;

import org.apache.poi.ss.usermodel.ClientAnchor;

import com.tiefaces.components.websheet.chart.ChartData;

public class ChartsData {
	/** hold chart data for current display sheet. */
	public Map<String, ChartData> chartDataMap;
	/**
	 * hold charts for current display sheet. each chart is a image generated by
	 * jfreechart.
	 */
	public Map<String, BufferedImage> chartsMap;
	/** hold chart anchor for each chart in current display sheet. */
	public Map<String, ClientAnchor> chartAnchorsMap;
	/**
	 * Index map for chartAnchorsMap. This map's key is chart top left cell
	 * position. e.g. key = Sheet1!A1 value = chartId of chartAnchorsMap.
	 */
	public Map<String, String> chartPositionMap;

	public ChartsData() {
	}
}