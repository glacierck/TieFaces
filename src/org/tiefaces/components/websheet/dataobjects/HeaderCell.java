/*
 * Copyright 2017 TieFaces.
 * Licensed under MIT
 */

package org.tiefaces.components.websheet.dataobjects;

import java.io.Serializable;

/**
 * Header cell object used for Primefaces datatable header.
 * 
 * @author Jason Jiang
 */
public class HeaderCell implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1669739622422183590L;
	
	/** The rowspan. */
	private String rowspan; // cell row span attribute

	/** The colspan. */
	private String colspan; // cell column span attribute

	/** The cell value. */
	private String cellValue; // header text label

	/** The style. */
	private String style; // cell web css style attriubte

	/** The column style. */
	private String columnStyle; // cell web css style attriubte

	/** The rendered. */
	private boolean rendered = true;

	/** The column rendered. */
	private boolean columnRendered = true;

	/**
	 * Constructor.
	 *
	 * @param prowspan
	 *            cell row span attribute
	 * @param pcolspan
	 *            cell column span attribute
	 * @param pstyle
	 *            cell web css style attriubte
	 * @param pcolumnStyle
	 *            the column style
	 * @param pcellValue
	 *            the cell value
	 * @param prendered
	 *            the rendered
	 * @param pcolumnRendered
	 *            the column rendered
	 */
	public HeaderCell(final String prowspan, final String pcolspan,
			final String pstyle, final String pcolumnStyle,
			final String pcellValue, final boolean prendered,
			final boolean pcolumnRendered) {
		super();
		this.rowspan = prowspan;
		this.colspan = pcolspan;
		this.style = pstyle;
		this.columnStyle = pcolumnStyle;
		this.cellValue = pcellValue;
		this.rendered = prendered;
		this.columnRendered = pcolumnRendered;
	}

	/**
	 * Gets the rowspan.
	 *
	 * @return the rowspan
	 */
	public final String getRowspan() {
		return rowspan;
	}

	/**
	 * Sets the rowspan.
	 *
	 * @param prowspan
	 *            the new rowspan
	 */
	public final void setRowspan(final String prowspan) {
		this.rowspan = prowspan;
	}

	/**
	 * Gets the colspan.
	 *
	 * @return the colspan
	 */
	public final String getColspan() {
		return colspan;
	}

	/**
	 * Sets the colspan.
	 *
	 * @param pcolspan
	 *            the new colspan
	 */
	public final void setColspan(final String pcolspan) {
		this.colspan = pcolspan;
	}

	/**
	 * Gets the cell value.
	 *
	 * @return the cell value
	 */
	public final String getCellValue() {
		return cellValue;
	}

	/**
	 * Sets the cell value.
	 *
	 * @param pcellValue
	 *            the new cell value
	 */
	public final void setCellValue(final String pcellValue) {
		this.cellValue = pcellValue;
	}

	/**
	 * Gets the column style.
	 *
	 * @return the column style
	 */
	public final String getColumnStyle() {
		return columnStyle;
	}

	/**
	 * Sets the column style.
	 *
	 * @param pcolumnStyle
	 *            the new column style
	 */
	public final void setColumnStyle(final String pcolumnStyle) {
		this.columnStyle = pcolumnStyle;
	}

	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public final String getStyle() {
		return style;
	}

	/**
	 * Sets the style.
	 *
	 * @param pstyle
	 *            the new style
	 */
	public final void setStyle(final String pstyle) {
		this.style = pstyle;
	}

	/**
	 * Checks if is rendered.
	 *
	 * @return true, if is rendered
	 */
	public final boolean isRendered() {
		return rendered;
	}

	/**
	 * Sets the rendered.
	 *
	 * @param prendered
	 *            the new rendered
	 */
	public final void setRendered(final boolean prendered) {
		this.rendered = prendered;
	}

	/**
	 * Checks if is column rendered.
	 *
	 * @return true, if is column rendered
	 */
	public final boolean isColumnRendered() {
		return columnRendered;
	}

	/**
	 * Sets the column rendered.
	 *
	 * @param pcolumnRendered
	 *            the new column rendered
	 */
	public final void setColumnRendered(final boolean pcolumnRendered) {
		this.columnRendered = pcolumnRendered;
	}

	/**
	 * Obtain a human readable representation.
	 * 
	 * @return String Human readable label
	 */
	@Override
	public final String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("rowspan = " + rowspan);
		sb.append(",");
		sb.append("colspan = " + colspan);
		sb.append(",");
		sb.append("style = " + style);
		sb.append(",");
		sb.append("columnStyle = " + columnStyle);
		sb.append(",");
		sb.append("cellValue = " + cellValue);
		sb.append(",");
		sb.append("rendered = " + rendered);
		sb.append(",");
		sb.append("columnRendered = " + columnRendered);
		sb.append("}");
		return sb.toString();
	}

}
