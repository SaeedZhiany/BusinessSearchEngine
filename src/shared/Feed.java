package shared;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */
public class Feed {
    private String title;
    private String body;
    private String city;
    private String url;
    private String date;

    public Feed() throws ParseException {
        this("", "", "", "", "");
    }

    public Feed(String title, String body, String city, String url, String date) throws ParseException {
        this.title = title;
        this.body = body;
        this.city = city;
        this.url = url;
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    /**
     * set date in base format using given date in parameter
     * @param date fetched date directly from website
     * @throws ParseException
     */
    public void setDate(String date) {
        if(date.equals("")){
            PersianCalendar calendar = new PersianCalendar(new Date());
            date = String.valueOf(calendar.get(Calendar.YEAR)) + "/" +
                    (calendar.get(Calendar.MONTH) > 10? "" : "0") +
                    String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                    (calendar.get(Calendar.DAY_OF_MONTH) > 10? "" : "0") +
                    String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        }
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
