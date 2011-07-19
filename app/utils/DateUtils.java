package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateUtils {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	static {
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
}
