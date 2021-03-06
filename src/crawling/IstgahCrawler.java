package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import shared.CalendarUtility;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Mostafa on 1/13/2016.
 */

/**
 * this class has been write to
 * crawl istgah's business feed
 * url is: http://www.istgah.com/fireview/kid_318/
 */
public class IstgahCrawler extends WebCrawler {

    private final String CITY_NOT_FOUND = "نامعلوم";
    Pattern filter = Pattern.compile("http://www\\.istgah\\.com/fireview/kid_318/page_[\\d]+/[\\d]+\\.html");
    Pattern filter1 = Pattern.compile("http://www\\.istgah\\.com/fireview/kid_318/page_[\\d]*/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() ||
                    filter1.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (page.getWebURL().toString().equals("http://www.istgah.com/fireview/kid_318/") ||
                filter1.matcher(page.getWebURL().toString()).matches()){
            return;
        }
        Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
        Elements elements = doc.select(".avp p , .uinfo , dd:nth-child(10) , h1");

        //title
        String title = elements.get(0).text().trim();
        elements.remove(elements.get(0));

        //city
        String city = elements.get(elements.size()-1).text().trim();
        if (city.equals(""))
            city = CITY_NOT_FOUND;
        elements.remove(elements.get(elements.size()-1));

        //body
        String body = elements.get(elements.size()-1).text().trim();
        elements.remove(elements.size()-1);

        //date
        String[] dates = elements.text().trim().split("،")[1].split(" ");
        dates[2] = CalendarUtility.getNumericMonth(dates[2]); // convert alphabet month to numeric

        String date = dates[3]+ "/" +
                (dates[2].length() == 1? "0":"") +
                dates[2]+ "/" +
                (dates[1].length() == 1? "0":"") +
                dates[1];
        date = CalendarUtility.getEnglishDate(date);
        System.out.println(title);
        System.out.println(body);
        System.out.println(city);
        System.out.println(date);
        System.out.println("=====================================");
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
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ISTGAH);
    }
}
