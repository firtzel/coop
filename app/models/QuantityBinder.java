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
import utils.Quantity;

//@Global
//public class QuantityBinder implements TypeBinder<Quantity> {
//
//	@Override
//	public Object bind(String name, Annotation[] annotations, String value,
//			Class actualClass, Type genericType) throws Exception {
//		return Quantity.fromString(value);
//	}
//}
