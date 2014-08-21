package com.ibm.fireapp;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;
@IBMDataObjectSpecialization("EmpDetails")
public class EmpDetails extends IBMDataObject {
	
	public static final String CLASS_NAME = "EmpDetails";
	private static final String AppName = "app_name";
	private static final String Name = "employee_name";
	private static final String Role = "role";
	private static final String Skill = "skill";
	

	public  String getName() {
		return (String) getObject(Name);
	}

	public  String getRole() {
		return (String) getObject(Role);
	}

	public  String getSkill() {
		return (String) getObject(Skill);
	}

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
	 * Sets the name of a list item, as well as calls setCreationTime().
	 * @param String itemName
	 */
	public void setName(String name) {
		setObject(Name, (name != null) ? name : "");
	}
	
	public void setSkill(String name) {
		setObject(Skill, (name != null) ? name : "");
	}
	public void setRole(String name) {
		setObject(Role, (name != null) ? name : "");
	}
	/**
	 * When calling toString() for an item, we'd really only want the name.
	 * @return String theItemName
	 */
	public String toStringappname() {
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
		theItemName = getName();
		return theItemName;
	}
	public String toStringSkill() {
		String theItemName = "";
		theItemName = getSkill();
		return theItemName;
	}
	public String toStringRole() {
		String theItemName = "";
		theItemName = getRole();
		return theItemName;
	}

}
