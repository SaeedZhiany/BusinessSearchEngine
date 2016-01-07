package shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */
public class Feed {
    private String title;
    private String date;
    private String body;
    private String city;

    public Feed() throws ParseException {
        this(
            "",
            "",
            "",
            new SimpleDateFormat(Params.DATE_FORMAT_YYYY_MM_DD).format(new Date()),
            Params.DATE_FORMAT_YYYY_MM_DD
        );
    }

    public Feed(String title, String body, String city, String date, String curFormat) throws ParseException {
        this.title = title;
        setDate(date, curFormat);
        this.body = body;
        this.city = city;
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
     * @param curFormat format of the date
     * @throws ParseException
     */
    public void setDate(String date, String curFormat) throws ParseException {
        SimpleDateFormat userFormat = new SimpleDateFormat(curFormat);
        SimpleDateFormat finalFormat = new SimpleDateFormat(Params.DATE_FORMAT_YYYY_MM_DD);
        this.date = finalFormat.format(userFormat.parse(date));
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
}
