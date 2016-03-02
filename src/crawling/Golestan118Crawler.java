package crawling;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Mostafa on 1/13/2016.
 */

/**
 * this class has been write to
 * crawl Golestan118's business feed
 * url is: http://www.golestan118.com/Hire
 */
public class Golestan118Crawler extends WebCrawler {

    private final String CITY_NOT_FOUND = "نامعلوم";
    Pattern filter = Pattern.compile("http://www\\.golestan118\\.com/HireDetail/[\\d]+/.*");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toString();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return decodedString.equals("http://www.golestan118.com/Hire") ||
                   filter.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (page.getWebURL().toString().equals("http://www.golestan118.com/Hire")){
            return;}
        Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
        Elements elements =
                doc.select("#ContentPlaceHolder1_lbadr , #ContentPlaceHolder1_lbcomm , #ContentPlaceHolder1_lbtit");

        //title
        String  title = elements.get(0).text().trim();
        elements.remove(elements.get(0));

        //city
        String city = elements.get(0).text().trim();
        if (city.contains("-"))
            city = city.split("-")[0];
        else if (city.contains("،"))
            city = city.split("،")[0];
        else if (city.contains("_"))
            city = city.split("_")[0];
        else if (city.contains("."))
            city = city.split(".")[0];
        else
            city = city.split(" ")[0];
        if (city.equals(""))
            city = CITY_NOT_FOUND;
        elements.remove(elements.get(0));

        //body
        String body = elements.text().trim();

        //date
        PersianCalendar calendar = new PersianCalendar(new Date());
        String date = String.valueOf(calendar.get(Calendar.YEAR)) + "/" +
                (calendar.get(Calendar.MONTH) > 10? "" : "0") +
                String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                (calendar.get(Calendar.DAY_OF_MONTH) > 10? "" : "0") +
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(title + " :: "+city+" :: "+date+"  :: "+body);
        try {
            Feed feed = new Feed(
                    title,
                    body,
                    city,
                    page.getWebURL().toString(),
                    date
            );
            feeds.add(feed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_GOLESTAN118);
    }
}
