package course.legacy.scheduler;
import java.util.Date;

public class TimeServices {

	public static boolean isPastDue(Date date) {
		// imagine this method has a real implementation
		return false;
	}
	
	public static boolean isWorkDay(Date date) {
		// imagine this method has a real implementation
		return false;
	}
	
	public static boolean isHoliday(Date date) {
		// imagine this method has a real implementation
		return false;
	}
	
	public boolean isDateAHoliday(Date date) {
		return TimeServices.isHoliday(date);
	}
}
