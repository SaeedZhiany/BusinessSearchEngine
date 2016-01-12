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

    private static final ArrayList<String> months2 = new ArrayList<String>(Arrays.asList(
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تير",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دى",
            "بهمن",
            "اسفند"
    ));

    private static final String EnglishNumber = "0123456789";
    private static final String PersianNumber = "۰۱۲۳۴۵۶۷۸۹";

    public static String getNumericMonth(String month){
        Integer index = months.indexOf(month) + 1;
        if (index == 0)
            index = months2.indexOf(month) + 1;
        return (index > 9 ? index.toString() : "0"+index);
    }

    public static String getEnglishDate(String persianDate){
        StringBuilder builder = new StringBuilder("");
        for (char c : persianDate.toCharArray()){
            if(PersianNumber.contains("" + c))
                builder.append(EnglishNumber.charAt(PersianNumber.indexOf(c)));
            else
                builder.append(c);
        }
        return builder.toString();
    }
}
