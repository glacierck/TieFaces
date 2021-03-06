/*
 * Copyright 2017 TieFaces.
 * Licensed under MIT
 */

package org.tiefaces.components.websheet.utility;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.tiefaces.common.FacesUtility;
import org.tiefaces.components.websheet.dataobjects.XColor;

/**
 * Utility Class for Color.
 * 
 * @author Jason Jiang
 *
 */
public final class ColorUtility {

	/** one million. */
	private static final double MILLION_NUMBERS = 100000.00;
	/** default automatic color numbers. */
	private static final int AUTOCOLORSIZE = 6;
	/** default automatic color names. */
	private static final String AUTOCOLORNAME = "accent";
	/** max rgb color. */
	private static final short RGB8BITS = 256;

	/** logger. */
	private static final Logger LOG = Logger
			.getLogger(ColorUtility.class.getName());

	/**
	 * prevent initialize.
	 */
	private ColorUtility() {
		// not called
	}

	/**
	 * retrieve background color for plot area.
	 * 
	 * @param ctPlot
	 *            plot area instance.
	 * @param themeTable
	 *            theme table contain theme color.
	 * @return xssfcolor with alpha setting.
	 */
	public static XColor getBgColor(final CTPlotArea ctPlot,
			final ThemesTable themeTable) {

		CTSolidColorFillProperties colorFill = null;
		try {
			colorFill = ctPlot.getSpPr().getSolidFill();
		} catch (Exception ex) {
			LOG.log(Level.FINE, "No entry in bgcolor for solidFill", ex);
		}
		XColor xcolor = findAutomaticFillColor(themeTable, colorFill);
		if (xcolor != null) {
			return xcolor;
		} else {
			return new XColor(new XSSFColor(Color.WHITE));
		}
	}

	/**
	 * get line color of line chart from CTLineSer.
	 *
	 * @param index
	 *            line index.
	 * @param ctSpPr
	 *            the ct sp pr
	 * @param themeTable
	 *            themeTable.
	 * @param isLineColor
	 *            is line color.
	 * @return xcolor.
	 */
	public static XColor geColorFromSpPr(final int index,
			final CTShapeProperties ctSpPr, final ThemesTable themeTable,
			final boolean isLineColor) {

		CTSolidColorFillProperties colorFill = null;

		try {
			if (isLineColor) {
				colorFill = ctSpPr.getLn().getSolidFill();
			} else {
				colorFill = ctSpPr.getSolidFill();
			}
		} catch (Exception ex) {
			LOG.log(Level.FINE, "No entry for solidFill", ex);
		}
		XColor xcolor = findAutomaticFillColor(themeTable, colorFill);
		if (xcolor != null) {
			return xcolor;
		} else {
			return getXColorWithAutomaticFill(index, themeTable);
		}

	}


	/**
	 * Find automatic fill color.
	 *
	 * @param themeTable
	 *            the theme table
	 * @param colorFill
	 *            the color fill
	 * @return the x color
	 */
	private static XColor findAutomaticFillColor(final ThemesTable themeTable,
			final CTSolidColorFillProperties colorFill) {
		// if there's no solidFill, then use automaticFill color
		if (colorFill == null) {
			return null;
		}	
		CTSchemeColor ctsColor = colorFill.getSchemeClr();
		if (ctsColor != null) {
			return getXColorFromSchemeClr(ctsColor, themeTable);
		} else {
			CTSRgbColor ctrColor = colorFill.getSrgbClr();
			if (ctrColor != null) {
				return getXColorFromRgbClr(ctrColor);
			}
		}
		return null;
	}
	
	
	
	/**
	 * assemble xssfcolor with tint/lumoff/lummod/alpha to xcolor.
	 * 
	 * @param bcolor
	 *            xssfcolor
	 * @param preTint
	 *            tint
	 * @param lumOff
	 *            lumOff
	 * @param lumMod
	 *            lummod
	 * @param alphaInt
	 *            alpha
	 * @return xcolor
	 */
	private static XColor assembleXcolor(final XSSFColor bcolor,
			final double preTint, final int lumOff, final int lumMod,
			final int alphaInt) {

		if (bcolor == null) {
			return null;
		}

		double tint = preTint;
		if (Double.compare(tint, 0)  == 0) {
			// no preTint
			if (lumOff > 0) {
				tint = lumOff / MILLION_NUMBERS;
			} else {
				if (lumMod > 0) {
					tint = -1 * (lumMod / MILLION_NUMBERS);
				}
			}
		}

		bcolor.setTint(tint);

		double alpha = 0;
		if (alphaInt > 0) {
			alpha = alphaInt / MILLION_NUMBERS;
		}

		return new XColor(bcolor, alpha);

	}

	/**
	 * retrieve xcolor from scheme color.
	 * 
	 * @param ctsColor
	 *            scheme color
	 * @param themeTable
	 *            theme table
	 * @return xcolor
	 */
	private static XColor getXColorFromSchemeClr(
			final CTSchemeColor ctsColor, final ThemesTable themeTable) {
		if (ctsColor.getVal() != null) {
			return getXColorWithSchema(ctsColor.getVal().toString(), 0,
					ctsColor, themeTable);
		}
		return null;
	}

	/**
	 * Get xcolor with color schema name. Normally the names are accent1 to 7.
	 * Sometimes also have lumOff/lumMod/alphaInt setting.
	 * 
	 * @param colorSchema
	 *            color schema
	 * @param preTint
	 *            tint
	 * @param ctsColor
	 *            ctsColor instance.
	 * @param themeTable
	 *            themeTable.
	 * @return xcolor.
	 */
	private static XColor getXColorWithSchema(final String colorSchema,
			final double preTint, final CTSchemeColor ctsColor,
			final ThemesTable themeTable) {
		int colorIndex = getThemeIndexFromName(colorSchema);
		if (colorIndex < 0) {
			return null;
		}
		XSSFColor bcolor = themeTable.getThemeColor(colorIndex);
		if (bcolor == null) {
			return null;
		}
		int lumOff = 0;
		int lumMod = 0;
		int alphaInt = 0;
		if (ctsColor != null) {
			try {
				lumOff = ctsColor.getLumOffArray(0).getVal();
			} catch (Exception ex) {
				LOG.log(Level.FINE, "No lumOff entry", ex);
			}
			try {
				lumMod = ctsColor.getLumModArray(0).getVal();
			} catch (Exception ex) {
				LOG.log(Level.FINE, "No lumMod entry", ex);
			}
			try {
				alphaInt = ctsColor.getAlphaArray(0).getVal();
			} catch (Exception ex) {
				LOG.log(Level.FINE, "No alpha entry", ex);
			}
		}
		return assembleXcolor(bcolor, preTint, lumOff, lumMod, alphaInt);
	}

	/**
	 * get xcolor from ctsRgbColor.
	 * 
	 * @param ctrColor
	 *            ctsRgbColor
	 * @return xcolor
	 */
	private static XColor getXColorFromRgbClr(final CTSRgbColor ctrColor) {
		XSSFColor bcolor = null;
		try {
			byte[] rgb = ctrColor.getVal();
			bcolor = new XSSFColor(rgb);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "Cannot get rgb color error = "
					+ ex.getLocalizedMessage(), ex);
			return null;
		}
		int lumOff = 0;
		int lumMod = 0;
		int alphaStr = 0;
		try {
			lumOff = ctrColor.getLumOffArray(0).getVal();
		} catch (Exception ex) {
			LOG.log(Level.FINE, "No lumOff entry", ex);
		}
		try {
			lumMod = ctrColor.getLumModArray(0).getVal();
		} catch (Exception ex) {
			LOG.log(Level.FINE, "No lumMod entry", ex);
		}
		try {
			alphaStr = ctrColor.getAlphaArray(0).getVal();
		} catch (Exception ex) {
			LOG.log(Level.FINE, "No alpha entry", ex);
		}
		return assembleXcolor(bcolor, 0, lumOff, lumMod, alphaStr);
	}


	/**
	 * Get xcolor for automatic fill setting. This is the default setting in
	 * Excel for chart colors. Normally the colors will be accent1 to 7.
	 * 
	 * @param index
	 *            line index.
	 * @param themeTable
	 *            theme table.
	 * @return xcolor.
	 */
	private static XColor getXColorWithAutomaticFill(final int index,
			final ThemesTable themeTable) {

		int reminder = (index + 1) % AUTOCOLORSIZE;
		if (reminder == 0) {
			reminder = AUTOCOLORSIZE;
		}
		String schema = AUTOCOLORNAME + reminder;
		double tint = getAutomaticTint(index);
		return getXColorWithSchema(schema, tint, null, themeTable);
	}

	/**
	 * Get automatic tint number for specified index line. The default automatic
	 * color only have 6 colors. If there are more than 6 lines then use tint
	 * for next round, e.g. color7 = color1 + tint (0.25)
	 * 
	 * @param index
	 *            line index.
	 * @return tint.
	 */
	private static double getAutomaticTint(final int index) {

		final double[] idxArray = { 0, 0.25, 0.5, -0.25, -0.5, 0.1, 0.3,
				-0.1, -0.3 };
		int i = index / AUTOCOLORSIZE;
		if (i >= idxArray.length) {
			return 0;
		} else {
			return idxArray[i];
		}
	}

	/**
	 * convert xssf color to color.
	 * 
	 * @param xssfColor
	 *            xssf color
	 * @return color
	 */
	public static Color xssfClrToClr(final XSSFColor xssfColor) {
		short[] rgb = getTripletFromXSSFColor(xssfColor);
		return new Color(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * Return index number for giving index name. e.g. tx1 return 1.
	 * 
	 * @param idxName
	 *            theme index name.
	 * @return index number.
	 */

	private static int getThemeIndexFromName(final String idxName) {

		final String[] idxArray = { "bg1", "tx1", "bg2", "tx2", "accent1",
				"accent2", "accent3", "accent4", "accent5", "accent6",
				"hlink", "folHlink" };
		try {
			for (int i = 0; i < idxArray.length; i++) {
				if (idxArray[i].equalsIgnoreCase(idxName)) {
					return i;
				}
			}
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "error in getThemeIndexFromName :"
					+ ex.getLocalizedMessage(), ex);
		}

		return -1;
	}

	/**
	 * Convert xssfcolor to triple let numbers.
	 * 
	 * @param xssfColor
	 *            xssf color.
	 * @return triple lets.
	 */
	public static short[] getTripletFromXSSFColor(
			final XSSFColor xssfColor) {

		short[] rgbfix = { RGB8BITS, RGB8BITS, RGB8BITS };
		if (xssfColor != null) {
			byte[] rgb = xssfColor.getRGBWithTint();
			if (rgb == null) {
				rgb = xssfColor.getRGB();
			}
			// Bytes are signed, so values of 128+ are negative!
			// 0: red, 1: green, 2: blue
			rgbfix[0] = (short) ((rgb[0] < 0) ? (rgb[0] + RGB8BITS)
					: rgb[0]);
			rgbfix[1] = (short) ((rgb[1] < 0) ? (rgb[1] + RGB8BITS)
					: rgb[1]);
			rgbfix[2] = (short) ((rgb[2] < 0) ? (rgb[2] + RGB8BITS)
					: rgb[2]);
		}
		return rgbfix;
	}

	/**
	 * Gets the bg color from cell.
	 *
	 * @param wb
	 *            the wb
	 * @param poiCell
	 *            the poi cell
	 * @param cellStyle
	 *            the cell style
	 * @return the bg color from cell
	 */
	static String getBgColorFromCell(final Workbook wb, final Cell poiCell,
			final CellStyle cellStyle) {

		String style = "";
		if (poiCell instanceof HSSFCell) {
			int bkColorIndex = cellStyle.getFillForegroundColor();
			HSSFColor color = HSSFColor.getIndexHash().get(bkColorIndex);
			if (color != null) {
				// correct color for customPalette
				HSSFPalette palette = ((HSSFWorkbook) wb)
						.getCustomPalette();
				HSSFColor color2 = palette.getColor(bkColorIndex);
				if (!color.getHexString()
						.equalsIgnoreCase(color2.getHexString())) {
					color = color2;
				}
				style = "background-color:rgb("
						+ FacesUtility.strJoin(color.getTriplet(), ",")
						+ ");";
			}
		} else if (poiCell instanceof XSSFCell) {
			XSSFColor color = ((XSSFCell) poiCell).getCellStyle()
					.getFillForegroundColorColor();
			if (color != null) {
				style = "background-color:rgb(" + FacesUtility.strJoin(
						getTripletFromXSSFColor(color), ",") + ");";
			}
		}
		return style;
	}
}
