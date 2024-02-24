package com.calenderdetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * The LeaveInformation class represents information about different types of leaves.
 * It includes details such as leave type ID, name, description, allowed days, and holiday dates.
 *
 * @author Roshini Kathirvel(Expleo)
 * @since 13 FEB 2024
 */
public class LeaveInformation {
    private int leaveTypeId;
    private String leaveName;
    private String leaveDescription;
    private int noOfDaysAllowed;
    private List<Date> holidayDates;
    /**
     * Constructs a {@code LeaveInformation} instance with the specified parameters.
     *
     * @param leaveTypeId      The unique identifier for the leave type.
     * @param leaveName        The name of the leave type.
     * @param leaveDescription A brief description of the leave type.
     * @param noOfDaysAllowed  The maximum number of days allowed for this leave type.
     * @param holidayDate      The initial holiday date associated with this leave type.
     */
    public LeaveInformation(int leaveTypeId, String leaveName, String leaveDescription, int noOfDaysAllowed, Date holidayDate) {
        this.leaveTypeId = leaveTypeId;
        this.leaveName = leaveName;
        this.leaveDescription = leaveDescription;
        this.noOfDaysAllowed = noOfDaysAllowed;
        this.holidayDates = new ArrayList<>();
        this.holidayDates.add(holidayDate);
    }
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getLeaveDescription() {
		return leaveDescription;
	}
	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}
	public int getNoOfDaysAllowed() {
		return noOfDaysAllowed;
	}
	public void setNoOfDaysAllowed(int noOfDaysAllowed) {
		this.noOfDaysAllowed = noOfDaysAllowed;
	}
	public List<Date> getHolidayDates() {
		return holidayDates;
	}
	public void setHolidayDates(List<Date> holidayDates) {
		this.holidayDates = holidayDates;
	}

}