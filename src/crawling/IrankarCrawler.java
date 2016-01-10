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
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Irankar's business feed
 * url is: http://www.irankar.biz/
 */
public class IrankarCrawler extends WebCrawler {

    private final String TITLE_CITY_SPLITTER = "–";
    private final String DATE_SPLITTER = ": ";
    private final String DATE_DETAIL_SPLITTER = " ";
    private final String IranKarDateFormat = "yyyy/mm/dd";
    private final Pattern filter = Pattern.compile("http://www\\.irankar\\.biz/.*استخدام.*/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() &&
                    !decodedString.startsWith("http://www.irankar.biz/category");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {

        if (!page.getWebURL().getURL().toLowerCase().equals("http://www.irankar.biz/")) {
            //System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select(".datas , p , .adsm2+ .box h4");

            String titleAndCity = elements.get(0).text(); // title and city is here
            String [] splits = titleAndCity.split(TITLE_CITY_SPLITTER);

            String title = elements.get(0).text().substring(0, titleAndCity.lastIndexOf(TITLE_CITY_SPLITTER));
            String city = splits[splits.length-1];

            /**
             * date[0]: day
             * date[1]: farsi month // must convert to relevant numeric month
             * date[2]: year
             */
            String [] date = elements.get(elements.size()-1).text().split(DATE_SPLITTER)[1].split(DATE_DETAIL_SPLITTER);
            date[1] = CalendarUtility.getNumericMonth(date[1]).toString(); // convert alphabet month to numeric

            // remove title and city , date elements from "elements" list
            elements.remove(elements.get(0));
            elements.remove(elements.get(elements.size()-1));

            // for other remaining elements
            StringBuilder builder = new StringBuilder("");
            for (Element element : elements) {
                builder.append(element.text());
            }
            String body = builder.toString();

            // now, all information is ready to save into excel
            try {
                Feed feed = new Feed(
                        title,
                        body,
                        city,
                        page.getWebURL().toString(),
                        date[2]+"/"+date[1]+"/"+date[0],
                        IranKarDateFormat
                );
                feeds.add(feed);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBeforeExit() { // write the results in excel before exit
        ExcelUtility.writeToExcel(feeds, Params.SHEET_IRANKAR);
    }
}
