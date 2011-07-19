package models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import play.data.binding.Global;
import play.data.binding.TypeBinder;

// FIXME maybe use Joda time instead of java.util.Date

//@Global
//public class DateBinder implements TypeBinder<Date> {
//	private static final String ISO8601 = "'ISO8601:'yyyy-MM-dd'T'HH:mm:ssZ";
//	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(ISO8601);
//
//	@Override
//	public Object bind(String name, Annotation[] annotations, String value,
//			Class actualClass, Type genericType) throws Exception {
//		Calendar cal = Calendar.getInstance();
//		String timeZone = cal.getTimeZone().getDisplayName();
//		Date date = DATE_FORMAT.parse(value);
//		Date changedTimeZoneDate = getDateInTimeZone(date, timeZone);
//		return changedTimeZoneDate;
//	}
//
//	// TODO see if this can be done more elegantly (copied from http://techinitiatives.blogspot.com/2007/07/getting-java-date-in-another-timezone.html)
//	private static Date getDateInTimeZone(Date currentDate, String timeZoneId) {
//		Calendar mbCal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
//		mbCal.setTimeInMillis(currentDate.getTime());
//
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
//		cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
//		cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
//		cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
//		cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
//		cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
//		cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
//
//		return cal.getTime();
//	}
//}
