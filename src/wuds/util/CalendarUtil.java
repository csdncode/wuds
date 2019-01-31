package wuds.util;

import java.util.Calendar;

/**日期工具<BR/>
 * 包含一些对日期的类型或格式的转换
 * @author liulei
 * @see Calendar
 */
public class CalendarUtil {

	/**Sting类型日期转为Calendar类型日期
	 * @param date 格式必须为YYYY-MM-DD，否则会抛出异常。例如：1970-01-01
	 * @return
	 * @throws NumberFormatException
	 */
	public static Calendar StringToCalendar(String date) throws NumberFormatException {
		Calendar calendar = null;
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, 0, 0, 0);
		return calendar;
	}
}
