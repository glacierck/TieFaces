/*
 * Copyright 2017 TieFaces.
 * Licensed under MIT
 */

package org.tiefaces.components.websheet.configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.tiefaces.components.websheet.dataobjects.CellFormAttributes;
import org.tiefaces.components.websheet.dataobjects.CellRange;
import org.tiefaces.components.websheet.serializable.SerialCellMap;
import org.tiefaces.components.websheet.serializable.SerialWorkbook;

/**
 * Configuration object hold all the attributes defined in Configuration tab
 * Also some attributes hold runtime value for each web sheet.
 *
 * @author Jason Jiang
 */
public class SheetConfiguration implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -108522960628560962L;

	/** The form name. */
	private String formName; // corresponds to tab name of configuration tab

	/** The sheet name. */
	private String sheetName; // corresponds to sheet name of configuration tab

	/** The form header range. */
	private String formHeaderRange; // corresponds to formHeaderRange of

	/** The form body range. */
	// configuration tab
	private String formBodyRange; // corresponds to formBodyRange of

	/** The form footer range. */
	// configuration tab
	private String formFooterRange; // corresponds to formFooterRange of

	/** The header cell range. */
	private CellRange headerCellRange; // transfer formHeaderRange to CellRange

	/** The body cell range. */
	// object
	private CellRange bodyCellRange; // transfer formBodyRange to CellRange

	/** The footer cell range. */
	// object
	private CellRange footerCellRange; // transfer formFooterRange to CellRange

	/** The form body type. */
	// object
	private String formBodyType; // formBodyType: Repeat or Free

	/** The body allow add rows. */
	private boolean bodyAllowAddRows; // whether allow dynamic insert row in

	/** The body initial rows. */
	// form body
	private int bodyInitialRows; // initial rows number for form body. support

	/** The body populated. */
	// EL.
	private boolean bodyPopulated; // runtime holder

	/** The max row per page. */
	private int maxRowPerPage; // max rows per page

	/** The cell form attributes. */
	private Map<String, List<CellFormAttributes>> cellFormAttributes;
	/**
	 * The form command.
	 */
	private FormCommand formCommand;

	/** The shift map. */
	private NavigableMap<String, ConfigRangeAttrs> shiftMap;

	/** The command index map. */
	private HashMap<String, Command> commandIndexMap;

	/** The collection obj name map. */
	private Map<String, String> collectionObjNameMap;

	/** The watch list. */
	private List<Integer> watchList;

	/** The hidden. */
	private boolean hidden = false; // in some case e.g. prepop, we choose to
									// hide the sheet.
	/** fixedWidthStyle. */
	private boolean fixedWidthStyle = false;

	/** The cached origin formulas. */
	private SerialCellMap serialCachedCells = new SerialCellMap();

	/**
	 * due to poi bug. cannot set comment during evaluate cell time. have to
	 * output comments following sequence. i.e. row by row. hold comments for
	 * the final sheet config form.
	 * 
	 * key is cell. value is the comments.
	 */
	private SerialCellMap serialFinalCommentMap = new SerialCellMap();

	/** logger. */
	private static final Logger LOG = Logger
			.getLogger(SerialWorkbook.class.getName());

	/**
	 * Instantiates a new sheet configuration.
	 */
	public SheetConfiguration() {
		super();
		LOG.fine("Sheet Configuration constructor");
	}

	/**
	 * Gets the sheet name.
	 *
	 * @return the sheet name
	 */
	public final String getSheetName() {
		return sheetName;
	}

	/**
	 * Sets the sheet name.
	 *
	 * @param psheetName
	 *            the new sheet name
	 */
	public final void setSheetName(final String psheetName) {
		this.sheetName = psheetName;
	}

	/**
	 * Gets the form name.
	 *
	 * @return the form name
	 */
	public final String getFormName() {
		return formName;
	}

	/**
	 * Sets the form name.
	 *
	 * @param pformName
	 *            the new form name
	 */
	public final void setFormName(final String pformName) {
		this.formName = pformName;
	}

	/**
	 * Gets the form header range.
	 *
	 * @return the form header range
	 */
	public final String getFormHeaderRange() {
		return formHeaderRange;
	}

	/**
	 * Sets the form header range.
	 *
	 * @param pformHeaderRange
	 *            the new form header range
	 */
	public final void setFormHeaderRange(final String pformHeaderRange) {
		this.formHeaderRange = pformHeaderRange;
	}

	/**
	 * Gets the form body range.
	 *
	 * @return the form body range
	 */
	public final String getFormBodyRange() {
		return formBodyRange;
	}

	/**
	 * Sets the form body range.
	 *
	 * @param pformBodyRange
	 *            the new form body range
	 */
	public final void setFormBodyRange(final String pformBodyRange) {
		this.formBodyRange = pformBodyRange;
	}

	/**
	 * Gets the form footer range.
	 *
	 * @return the form footer range
	 */
	public final String getFormFooterRange() {
		return formFooterRange;
	}

	/**
	 * Sets the form footer range.
	 *
	 * @param pformFooterRange
	 *            the new form footer range
	 */
	public final void setFormFooterRange(final String pformFooterRange) {
		this.formFooterRange = pformFooterRange;
	}

	/**
	 * Gets the cell form attributes.
	 *
	 * @return the cell form attributes
	 */
	public final Map<String, List<CellFormAttributes>> getCellFormAttributes() {
		return cellFormAttributes;
	}

	/**
	 * Sets the cell form attributes.
	 *
	 * @param pcellFormAttributes
	 *            the cell form attributes
	 */
	public final void setCellFormAttributes(
			final Map<String, List<CellFormAttributes>> pcellFormAttributes) {
		this.cellFormAttributes = pcellFormAttributes;
	}

	/**
	 * Gets the header cell range.
	 *
	 * @return the header cell range
	 */
	public final CellRange getHeaderCellRange() {
		return headerCellRange;
	}

	/**
	 * Sets the header cell range.
	 *
	 * @param pheaderCellRange
	 *            the new header cell range
	 */
	public final void setHeaderCellRange(final CellRange pheaderCellRange) {
		this.headerCellRange = pheaderCellRange;
	}

	/**
	 * Gets the body cell range.
	 *
	 * @return the body cell range
	 */
	public final CellRange getBodyCellRange() {
		return bodyCellRange;
	}

	/**
	 * Sets the body cell range.
	 *
	 * @param pbodyCellRange
	 *            the new body cell range
	 */
	public final void setBodyCellRange(final CellRange pbodyCellRange) {
		this.bodyCellRange = pbodyCellRange;
	}

	/**
	 * Gets the footer cell range.
	 *
	 * @return the footer cell range
	 */
	public final CellRange getFooterCellRange() {
		return footerCellRange;
	}

	/**
	 * Sets the footer cell range.
	 *
	 * @param pfooterCellRange
	 *            the new footer cell range
	 */
	public final void setFooterCellRange(final CellRange pfooterCellRange) {
		this.footerCellRange = pfooterCellRange;
	}

	/**
	 * Gets the form body type.
	 *
	 * @return the form body type
	 */
	public final String getFormBodyType() {
		return formBodyType;
	}

	/**
	 * Sets the form body type.
	 *
	 * @param pformBodyType
	 *            the new form body type
	 */
	public final void setFormBodyType(final String pformBodyType) {
		this.formBodyType = pformBodyType;
	}

	/**
	 * Checks if is body allow add rows.
	 *
	 * @return true, if is body allow add rows
	 */
	public final boolean isBodyAllowAddRows() {
		return bodyAllowAddRows;
	}

	/**
	 * Sets the body allow add rows.
	 *
	 * @param pbodyAllowAddRows
	 *            the new body allow add rows
	 */
	public final void setBodyAllowAddRows(final boolean pbodyAllowAddRows) {
		this.bodyAllowAddRows = pbodyAllowAddRows;
	}

	/**
	 * Gets the body initial rows.
	 *
	 * @return the body initial rows
	 */
	public final int getBodyInitialRows() {
		return bodyInitialRows;
	}

	/**
	 * Sets the body initial rows.
	 *
	 * @param pbodyInitialRows
	 *            the new body initial rows
	 */
	public final void setBodyInitialRows(final int pbodyInitialRows) {
		this.bodyInitialRows = pbodyInitialRows;
	}

	/**
	 * Checks if is body populated.
	 *
	 * @return true, if is body populated
	 */
	public final boolean isBodyPopulated() {
		return bodyPopulated;
	}

	/**
	 * Sets the body populated.
	 *
	 * @param pbodyPopulated
	 *            the new body populated
	 */
	public final void setBodyPopulated(final boolean pbodyPopulated) {
		this.bodyPopulated = pbodyPopulated;
	}


	/**
	 * Gets the max row per page.
	 *
	 * @return the max row per page
	 */
	public final int getMaxRowPerPage() {
		return maxRowPerPage;
	}

	/**
	 * Sets the max row per page.
	 *
	 * @param pmaxRowPerPage
	 *            the new max row per page
	 */
	public final void setMaxRowPerPage(final int pmaxRowPerPage) {
		this.maxRowPerPage = pmaxRowPerPage;
	}

	/**
	 * Gets the watch list.
	 *
	 * @return the watch list
	 */
	public final List<Integer> getWatchList() {
		return watchList;
	}

	/**
	 * Sets the watch list.
	 *
	 * @param pwatchList
	 *            the new watch list
	 */
	public final void setWatchList(final List<Integer> pwatchList) {
		this.watchList = pwatchList;
	}

	/**
	 * Gets the shift map.
	 *
	 * @return the shift map
	 */
	public final NavigableMap<String, ConfigRangeAttrs> getShiftMap() {
		return shiftMap;
	}

	/**
	 * Sets the shift map.
	 *
	 * @param pshiftMap
	 *            the shift map
	 */
	public final void setShiftMap(
			final NavigableMap<String, ConfigRangeAttrs> pshiftMap) {
		this.shiftMap = pshiftMap;
	}

	/**
	 * Gets the command index map.
	 *
	 * @return the command index map
	 */
	public final Map<String, Command> getCommandIndexMap() {
		return commandIndexMap;
	}

	/**
	 * Sets the command index map.
	 *
	 * @param pcommandIndexMap
	 *            the command index map
	 */
	public final void setCommandIndexMap(
			final Map<String, Command> pcommandIndexMap) {
		if (pcommandIndexMap instanceof HashMap) {
			this.commandIndexMap = (HashMap<String, Command>) pcommandIndexMap;
		} else {
			this.commandIndexMap = new HashMap<>();
			this.commandIndexMap.putAll(pcommandIndexMap);
		}
	}

	/**
	 * Gets the collection obj name map.
	 *
	 * @return the collection obj name map
	 */
	public final Map<String, String> getCollectionObjNameMap() {
		return collectionObjNameMap;
	}

	/**
	 * Sets the collection obj name map.
	 *
	 * @param pcollectionObjNameMap
	 *            the collection obj name map
	 */
	public final void setCollectionObjNameMap(
			final Map<String, String> pcollectionObjNameMap) {
		this.collectionObjNameMap = pcollectionObjNameMap;
	}

	/**
	 * Gets the form command.
	 *
	 * @return the form command
	 */
	public final FormCommand getFormCommand() {
		if (this.formCommand == null) {
			this.formCommand = new FormCommand();
		}
		return formCommand;
	}

	/**
	 * Sets the form command.
	 *
	 * @param pformCommand
	 *            the new form command
	 */
	public final void setFormCommand(final FormCommand pformCommand) {
		this.formCommand = pformCommand;
	}

	/**
	 * Checks if is hidden.
	 *
	 * @return true, if is hidden
	 */
	public final boolean isHidden() {
		return hidden;
	}

	/**
	 * Sets the hidden.
	 *
	 * @param phidden
	 *            the new hidden
	 */
	public final void setHidden(final boolean phidden) {
		this.hidden = phidden;
	}

	/**
	 * Gets the cached origin formulas.
	 *
	 * @return the cached origin formulas
	 */
	public final Map<Cell, String> getCachedCells() {
		return serialCachedCells.getMap();
	}

	/**
	 * Gets the serial cached cells.
	 *
	 * @return the serialCachedCells
	 */
	public final SerialCellMap getSerialCachedCells() {
		return serialCachedCells;
	}

	/**
	 * Gets the final comment map.
	 *
	 * @return the final comment map
	 */
	public final Map<Cell, String> getFinalCommentMap() {
		return serialFinalCommentMap.getMap();
	}

	/**
	 * Gets the serial final comment map.
	 *
	 * @return the serialFinalCommentMap
	 */
	public final SerialCellMap getSerialFinalCommentMap() {
		return serialFinalCommentMap;
	}

	/**
	 * recover the cell reference to the sheet.
	 * 
	 * @param wb
	 *            workbook.
	 */
	public void recover(final Workbook wb) {
		Sheet sheet = wb.getSheet(this.sheetName);
		this.getSerialCachedCells().recover(sheet);
		this.getSerialFinalCommentMap().recover(sheet);
		this.getFormCommand().recover(sheet);
		if (this.getShiftMap() != null) {
			for (Map.Entry<String, ConfigRangeAttrs> entry : this
					.getShiftMap().entrySet()) {
				entry.getValue().recover(sheet);
			}
		}
		if (this.getCommandIndexMap() != null) {
			for (Map.Entry<String, Command> entry : this
					.getCommandIndexMap().entrySet()) {
				entry.getValue().recover(sheet);
			}
		}

	}

	
	
	/**
     * @return the fixedWidthStyle
     */
    public final boolean isFixedWidthStyle() {
        return fixedWidthStyle;
    }

    /**
     * @param pfixedWidthStyle the fixedWidthStyle to set
     */
    public final void setFixedWidthStyle(final boolean pfixedWidthStyle) {
        this.fixedWidthStyle = pfixedWidthStyle;
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
		sb.append("formName = " + formName);
		sb.append(",");
		sb.append("sheetName = " + sheetName);
		sb.append(",");
		sb.append("formHeaderRange = " + formHeaderRange);
		sb.append(",");
		sb.append("formBodyRange = " + formBodyRange);
		sb.append(",");
		sb.append("formFooterRange = " + formFooterRange);
		sb.append(",");
		sb.append("formBodyType = " + formBodyType);
		sb.append(",");
		sb.append("bodyAllowAddRows = " + bodyAllowAddRows);
		sb.append(",");
		sb.append("bodyInitialRows = " + bodyInitialRows);
		sb.append(",");
		sb.append("HeaderCellRange = " + headerCellRange);
		sb.append(",");
		sb.append("BodyCellRange = " + bodyCellRange);
		sb.append(",");
		sb.append("BodyPopulated = " + bodyPopulated);
		sb.append(",");
		sb.append("FooterCellRange = " + footerCellRange);
		sb.append(",");
		sb.append("cellFormAttributes = " + cellFormAttributes);
		sb.append(",");
		sb.append("cachedOriginFormulas = " + serialCachedCells);
		sb.append(",");
		sb.append("finalCommentMap = " + serialFinalCommentMap);
		sb.append(",");
		sb.append("formCommand = " + formCommand);
		sb.append(",");
		sb.append("shiftMap = " + shiftMap);
		sb.append(",");
		sb.append("commandIndexMap = " + commandIndexMap);
		sb.append(",");
		sb.append("collectionObjNameMap = " + collectionObjNameMap);
		sb.append(",");
		sb.append("watchList = " + watchList);
		sb.append("}");
		return sb.toString();
	}

}
