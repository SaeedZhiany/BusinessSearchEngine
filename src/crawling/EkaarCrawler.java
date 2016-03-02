package crawling;

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
import java.util.regex.Pattern;

/**
 * Created by Mostafa on 1/12/2016.
 */

/**
 * this class has been write to
 * crawl Ekaar's business feed
 * url is: http://ekaar.ir/joblist.aspx
 */
public class EkaarCrawler extends WebCrawler {

    private final String DATE_DETAIL_SPLITTER = " ";
    private final String ekaarDateFormat = "yyyy/mm/dd";

    Pattern filter = Pattern.compile("http://ekaar\\.ir/joblist\\.aspx?page=[\\d]+");
    Pattern filter1 = Pattern.compile("http://ekaar\\.ir/job-[\\d]+\\..*");

    private final ArrayList<Feed> feeds = new ArrayList<shared.Feed>();
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return  filter.matcher(decodedString).matches() ||
                    filter1.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public void visit(Page page) {
        if (page.getWebURL().getURL().equals("http://ekaar.ir/joblist.aspx"))
            return;
        if (page.getWebURL().getURL().matches("http://ekaar\\.ir/joblist\\.aspx?page=[\\d]+"))
            return;
        else {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select(".jobrightinfo span:nth-child(3) , .addr a+ a , .text , #main h2");

            //title
            String title = elements.get(0).text().trim();
            elements.remove(elements.get(0));

            //body
            String body = elements.get(0).text().trim();
            elements.remove(elements.get(0));

            //city
            String city = elements.get(0).text().trim();
            elements.remove(elements.get(0));

            //title
            String date = elements.get(0).text().trim().split(DATE_DETAIL_SPLITTER)[1];

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
    }
    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_EKAAR);
    }
}
