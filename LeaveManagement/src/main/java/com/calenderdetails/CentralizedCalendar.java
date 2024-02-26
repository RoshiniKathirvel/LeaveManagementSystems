package com.calenderdetails;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * The  CentralizedCalendar class extends  LeaveInformation and represents a centralized calendar
 * containing predefined holidays.
 *
 * @author Roshini Kathirvel (Expleo)
 * @since  13 FEB 2024
 */
public class CentralizedCalendar extends LeaveInformation {
    private static List<LeaveInformation> predefinedHolidays;
    /**
     * Constructs a  CentralizedCalendar instance with specified parameters.
     *
     * @param leaveTypeId      The ID of the leave type.
     * @param leaveName        The name of the leave.
     * @param leaveDescription The description of the leave.
     * @param noOfDaysAllowed  The number of days allowed for the leave.
     * @param holidayDate      The date of the holiday.
     */
    
	public CentralizedCalendar(int leaveTypeId, String leaveName, String leaveDescription, int noOfDaysAllowed, Date holidayDate) {
        super(leaveTypeId, leaveName, leaveDescription, noOfDaysAllowed, holidayDate);
        this.predefinedHolidays = initializePredefinedHolidays();
    }
    /**
     * Gets the list of predefined holidays.
     *
     * @return List of predefined holidays.
     */
    public List<LeaveInformation> getPredefinedHolidays() {
        return predefinedHolidays;
    }

    private List<LeaveInformation> initializePredefinedHolidays() {
        List<LeaveInformation> holidays = new ArrayList<>();
        holidays.add(new LeaveInformation(1,"New Year", "Public holiday to celebrate the New Year", 1, createDate(2024, 0, 1)));
        holidays.add(new LeaveInformation(2,"Pongal", "Harvest festival  in South India", 1, createDate(2024, 0, 15)));
        holidays.add(new LeaveInformation(3,"Republic Day", "Public holiday for Republic Day", 1, createDate(2024, 0, 26)));
        holidays.add(new LeaveInformation(4,"Ugadi", "It is a Telugu and Kannada New Year festival", 1, createDate(2024, 3, 9)));
        holidays.add(new LeaveInformation(5,"Ramzan", "Ramzan is a Muslim Festival", 1, createDate(2024, 3, 11)));
        holidays.add(new LeaveInformation(6,"May Day", "Celebration of labor and the working class", 1, createDate(2024, 4, 1)));
        holidays.add(new LeaveInformation(7,"Independence Day", "Independence Day is a national holiday", 1, createDate(2024, 7, 15)));
        holidays.add(new LeaveInformation(8,"Gandhi Jayanthi", "Mark the birthday of Mahatma Gandhi", 1, createDate(2024, 9, 2)));
        holidays.add(new LeaveInformation(9,"Ayudha Pooja", "Celebration of the ninth day of Navaratri", 1, createDate(2024, 9, 11)));
        holidays.add(new LeaveInformation(10,"Diwali", "victory of light over darkness and good over evil.", 1, createDate(2024, 9, 31)));
        holidays.add(new LeaveInformation(11,"Laxmi Pojan", "the goddess of wealth and prosperity", 1, createDate(2024, 10, 1)));
        holidays.add(new LeaveInformation(12,"Christmas", "Celebrates the birth of Jesus Christ", 1, createDate(2024, 11, 25)));
        return holidays;
    }

    public static void displayLeaveCalendar() {
        for (LeaveInformation holiday : predefinedHolidays) {
        	System.out.println();
        	System.out.println("|----------------------------------------------------------------------|");
        	System.out.printf("| %-30s%-40s |\n", "Leave Type ID:", holiday.getLeaveTypeId());
        	System.out.printf("| %-30s%-40s |\n", "Leave Name:", holiday.getLeaveName());
        	System.out.printf("| %-30s%-40s |\n", "Leave Description:", holiday.getLeaveDescription());
        	System.out.printf("| %-30s%-40s |\n", "No. of Days:", holiday.getNoOfDaysAllowed());
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	System.out.printf("| %-30s%-40s |\n", "Holiday Date:", dateFormat.format(holiday.getHolidayDates().get(0)));
        	System.out.println("|-----------------------------------------------------------------------|");
        	System.out.println();

        }
    }

    private Date createDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }
}
