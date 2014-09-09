package com.ibm.fireapp;

import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMDataObjectSpecialization;

@IBMDataObjectSpecialization("IncidentList")
public class IncidentList extends IBMDataObject {
	public static final String CLASS_NAME = "IncidentList";
	private static final String IncidentNo = "Incident_No";
	private static final String Age = "Age";
	private static final String Description = "Description";
	private static final String CurrentStatus = "Current_Status";
	private static final String Greater90 = "Greater than 90";
	private static final String Between60To90 = "Between 60 To 90 Days";
	private static final String ResolverName = "Resolver Name";
	private static final String Priority = "Priority";
	private static final String Between5To30 = "Between 5 To 30 Days";
	private static final String LessThan5 = "Less than 5 Days";
	private static final String Status = "Status";
	private static final String AssigneeGroup = "Assignee_Group";
	private static final String Between30To60 = "Between 30 To 60 Days";
	private static final String BusinessArea = "Business_area";
	private static final String ReportedDate = "Reported_Date";
	private static final String ResolvedDate = "Resolved Date";
	/**
	 * @return the incidentno
	 */
	public String getIncidentno() {
		return (String) getObject(IncidentNo);
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return (String) getObject(Age);
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return (String) getObject(Description);
	}
	/**
	 * @return the currentstatus
	 */
	public String getCurrentstatus() {
		return (String) getObject(CurrentStatus);
	}
	/**
	 * @return the greater90
	 */
	public String getGreater90() {
		return (String) getObject(Greater90);
	}
	/**
	 * @return the between60to90
	 */
	public String getBetween60to90() {
		return (String) getObject(Between60To90);
	}
	/**
	 * @return the resolvername
	 */
	public String getResolvername() {
		return (String) getObject(ResolverName);
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return (String) getObject(Priority);
	}
	/**
	 * @return the between5to30
	 */
	public String getBetween5to30() {
		return (String) getObject(Between5To30);
	}
	/**
	 * @return the lessthan5
	 */
	public String getLessthan5() {
		return (String) getObject(LessThan5);
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return (String) getObject(Status);
	}
	/**
	 * @return the resolveddate
	 */
	public String getResolveddate() {
		return (String) getObject(ResolvedDate);
	}
	/**
	 * @return the assigneegroup
	 */
	public String getAssigneegroup() {
		return (String) getObject(AssigneeGroup);
	}
	/**
	 * @return the between30to60
	 */
	public String getBetween30to60() {
		return (String) getObject(Between30To60);
	}
	/**
	 * @return the businessarea
	 */
	public String getBusinessarea() {
		return (String) getObject(BusinessArea);
	}
	

	/**
	 * @return the reporteddate
	 */
	public String getReporteddate() {
		return (String) getObject(ReportedDate);
	}
	/**
	 * @param reporteddate the reporteddate to set
	 */
	public void setReporteddate(String itemName) {
		setObject(ReportedDate, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the incidentno
	 */
	public void setIncidentno(String itemName) {
		setObject(IncidentNo, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the age
	 */
	public void setAge(String itemName) {
		setObject(Age, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the description
	 */
	public void setDescription(String itemName) {
		setObject(Description, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the currentstatus
	 */
	public void setCurrentstatus(String itemName) {
		setObject(CurrentStatus, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the greater90
	 */
	public void setGreater90(String itemName) {
		setObject(Greater90, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the between60to90
	 */
	public void setBetween60to90(String itemName) {
		setObject(Between60To90, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the resolvername
	 */
	public void setResolvername(String itemName) {
		setObject(ResolverName, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the priority
	 */
	public void setPriority(String itemName) {
		setObject(Priority, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the between5to30
	 */
	public void setBetween5to30(String itemName) {
		setObject(Between5To30, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the lessthan5
	 */
	public void setLessthan5(String itemName) {
		setObject(LessThan5, (itemName != null) ? itemName : "");;
	}
	/**
	 * @return the status
	 */
	public void setStatus(String itemName) {
		setObject(Status, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the resolveddate
	 */
	public void setResolveddate(String itemName) {
		setObject(ResolvedDate, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the assigneegroup
	 */
	public void setAssigneegroup(String itemName) {
		setObject(AssigneeGroup, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the between30to60
	 */
	public void setBetween30to60(String itemName) {
		setObject(Between30To60, (itemName != null) ? itemName : "");
	}
	/**
	 * @return the businessarea
	 */
	public void setBusinessarea(String itemName) {
		setObject(BusinessArea, (itemName != null) ? itemName : "");
	}
	
}
