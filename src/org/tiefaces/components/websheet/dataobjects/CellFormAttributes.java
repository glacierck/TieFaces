/*
 * Copyright 2017 TieFaces.
 * Licensed under MIT
 */

package org.tiefaces.components.websheet.dataobjects;

import java.io.Serializable;

/**
 * Form attributes object defined in configuration tab.
 * 
 * @author Jason Jiang
 * This object corresponds to attributes columns ( type, value, message)
 *       in configuration tab.
 */

public class CellFormAttributes implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 904076322986778019L;

	/** The type. */
	private String type;
	
	/** The value. */
	private String value;
	
	/** The message. */
	private String message;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param ptype
	 *            the new type
	 */
	public final void setType(final String ptype) {
		this.type = ptype;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param pvalue
	 *            the new value
	 */
	public final void setValue(final String pvalue) {
		this.value = pvalue;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param pmessage
	 *            the new message
	 */
	public final void setMessage(final String pmessage) {
		this.message = pmessage;
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
		sb.append("type = " + type);
		sb.append(",");
		sb.append("value = " + value);
		sb.append(",");
		sb.append("message = " + message);
		sb.append("}");
		return sb.toString();
	}

}
