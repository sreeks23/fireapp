package com.ibm.fireapp;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("AppList")
public class AppList extends IBMDataObject {
	public static final String CLASS_NAME = "AppList";
	private static final String AppName = "app_name";
	private static final String AppID = "app_id";
	

	/**
	 * Gets the name of the Item.
	 * @return String itemName
	 */
	public String getAppname() {
		return (String) getObject(AppName);
	}

	/**
	 * Sets the name of a list item, as well as calls setCreationTime().
	 * @param String itemName
	 */
	public void setAppName(String itemName) {
		setObject(AppName, (itemName != null) ? itemName : "");
	}
	
	/**
	 * Gets the name of the Item.
	 * @return String itemName
	 */
	public String getAppid() {
		return (String) getObject(AppID);
	}

	/**
	 * Sets the name of a list item, as well as calls setCreationTime().
	 * @param String itemName
	 */
	public void setAppID(String itemName) {
		setObject(AppID, (itemName != null) ? itemName : "");
	}
	
	/**
	 * When calling toString() for an item, we'd really only want the name.
	 * @return String theItemName
	 */
	public String toStringID() {
		String theItemName = "";
		theItemName = getAppname();
		return theItemName;
	}
	/**
	 * When calling toString() for an item, we'd really only want the name.
	 * @return String theItemName
	 */
	public String toStringName() {
		String theItemName = "";
		theItemName = getAppid();
		return theItemName;
	}
}

