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
 * crawl pnuna's business feed
 * url is: http://job.pnuna.com/
 */
public class PnunaCrawler extends WebCrawler {

    private final String DATE_SPLITTER = ": ";
    private final String DATE_DETAIL_SPLITTER = " ";
    private final String pnunaDateFormat = "yyyy/mm/dd";
    private final Pattern filter = Pattern.compile("^http://job.pnuna.com/13[0-9][2]/.*استخدام.*\\.html/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return decodedString.startsWith("http://job.pnuna.com/13") && decodedString.contains("استخدام") && !decodedString.startsWith("http://job.pnuna.com/category") && !decodedString.startsWith("http://job.pnuna.com/tag");

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        try {
            if (!URLDecoder.decode(page.getWebURL().getURL(), "UTF8").toLowerCase().equals("http://job.pnuna.com/")) {
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select("br+ p > span , span span strong , .post-info li:nth-child(2) , .box-top a");

                for (Element element : elements)
                    System.out.println(element.text());
                //title
                String title = elements.get(0).text().trim();
                elements.remove(elements.get(0));

                //city
                String city = "";

                //date
                String[] date = elements.get(elements.size() - 1).text().split(DATE_SPLITTER)[1].split(DATE_DETAIL_SPLITTER);
                date[1] = CalendarUtility.getNumericMonth(date[1]).toString(); // convert alphabet month to numeric
                elements.remove(elements.get(elements.size() - 1));

                //body
                String body = "";
                for (Element element : elements)
                    body = body.concat(element.text().trim());
                //System.out.println(body);
                try {
                    Feed feed = new Feed(
                            title,
                            body,
                            city,
                            page.getWebURL().toString(),
                            date[2] + "/" + date[1] + "/" + date[0],
                            pnunaDateFormat);
                    feeds.add(feed);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
        @Override
        public void onBeforeExit(){
            ExcelUtility.writeToExcel(feeds, Params.SHEET_PNUNA);
        }

}
