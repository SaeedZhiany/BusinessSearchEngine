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

    private static final String EnglishNumber = "0123456789";
    private static final String PersianNumber = "۰۱۲۳۴۵۶۷۸۹";

    public static Integer getNumericMonth(String month){
        return months.indexOf(month) + 1;
    }

    public static String getEnglishDate(String persianDate){
        StringBuilder builder = new StringBuilder("");
        for (char c : persianDate.toCharArray()){
            if(c != '/')
                builder.append(EnglishNumber.charAt(PersianNumber.indexOf(c)));
            else
                builder.append(c);
        }
        return builder.toString();
    }
}
