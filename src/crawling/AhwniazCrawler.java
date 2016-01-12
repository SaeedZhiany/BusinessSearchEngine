package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import shared.CalendarUtility;
import shared.Feed;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Mostafa on 1/12/2016.
 */

/**
 * this class has been write to
 * crawl AhwniazCrawler's business feed
 * url is: http://ahwniaz.ir/category/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/
 */
public class AhwniazCrawler extends WebCrawler {

    private final String DATE_SPLITTER = ": ";
    private final String DATE_DETAIL_SPLITTER = " ";
    private final String CITY_SPLITTER = "شماره تماس";
    private final String ADDRESS = "آدرس:";
    private final String AhwniazDateFormat = "yyyy/mm/dd";

    Pattern filter = Pattern.compile("http://ahwniaz\\.ir/category/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/page/[\\d]+/");
    Pattern filter1 = Pattern.compile("^http://ahwniaz\\.ir/.*استخدام.*/");
    private final ArrayList<Feed> Feed = new ArrayList<shared.Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return (filter.matcher(decodedString).matches() || filter1.matcher(decodedString).matches());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public void visit(Page page) {
        try {
            if (URLDecoder.decode(page.getWebURL().getURL().toString(), "UTF8").toLowerCase().equals("http://ahwniaz.ir/category/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/")){
                System.out.println("123");
                return;}
            if (filter.matcher(URLDecoder.decode(page.getWebURL().getURL(), "UTF8")).matches()){
                System.out.println("789");
                return;}
            else {
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());

                //title
                String title = doc.select("h1").text().trim();

                //date
                String[] date = doc.select(".text-info+ span").text().trim().split(DATE_SPLITTER)[1].split(DATE_DETAIL_SPLITTER);
                date[1] = CalendarUtility.getNumericMonth(date[1]).toString(); // convert alphabet month to numeric

                //city
                String city = URLDecoder.decode(doc.select(".matn p+ p").text().trim().split(CITY_SPLITTER)[0].split(DATE_SPLITTER)[1], "UTF8");
                if (URLDecoder.decode(doc.select(".matn p+ p").text().trim().split(CITY_SPLITTER)[0].split(DATE_SPLITTER)[0], "UTF8").contains("ایمیل")
                || !URLDecoder.decode(doc.select(".matn p+ p").text().trim().split(CITY_SPLITTER)[0].split(DATE_SPLITTER)[0], "UTF8").contains("آدرس"))
                    city = "";

                //body
                String body = doc.select(".matn p:nth-child(2) , .ad-body p:nth-child(1)").text().trim();
                if (body.isEmpty())
                    body = doc.select(".ad-body p").text().trim();

                //for (Element element : elements){
                    System.out.println(city.toString());
                    System.out.println("+++++++");
                //}

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
