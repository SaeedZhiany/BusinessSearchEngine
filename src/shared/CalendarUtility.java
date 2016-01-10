package shared;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SAEED on 2016-01-10
 * for project BusinessSearchEngine .
 */
public class CalendarUtility {
    private static final ArrayList<String> months = new ArrayList<String>(Arrays.asList(
"فروردین",
"اردیبهشت",
"خرداد",
"تیر",
"مرداد",
"شهریور",
"مهر",
"آبان",
"آذر",
"دی",
"بهمن",
"اسفند"
    ));
    public static Integer getNumericMonth(String month){
        return months.indexOf(month) + 1;
    }
}
