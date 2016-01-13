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

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();
    Pattern filter = Pattern.compile("http://job\\.pnuna\\.com/page/[\\d]+/");
    Pattern filter1 = Pattern.compile("http://job\\.pnuna\\.com/[\\d]+/.*استخدام.*\\.html");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() ||
                    filter1.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        try {
            if (URLDecoder.decode(page.getWebURL().getURL(), "UTF8").toLowerCase().equals("http://job.pnuna.com/")) {
                return;
            }
            else if (filter.matcher(page.getWebURL().getURL()).matches()) {
                return;
            }
            else if (filter1.matcher(URLDecoder.decode(page.getWebURL().getURL(), "UTF8")).matches()) {
                System.out.println(page.getWebURL().toString());
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements1 = doc.select(".post-info li:nth-child(2) , .box-top a");
                Elements elements2 = doc.select(".post-info1 li:nth-child(1) a");
                Elements elements3 = doc.select("br+ p");

                //title
                String title = elements1.get(0).text().trim();
                elements1.remove(elements1.get(0));

                //city
                String city = elements2.text().trim();

                //date
                String[] dates = elements1.get(elements1.size() - 1).text().split(DATE_SPLITTER)[1].split(DATE_DETAIL_SPLITTER);
                dates[1] = CalendarUtility.getNumericMonth(dates[1]); // convert alphabet month to numeric
                String date = dates[2] + "/" +
                        (dates[1].length() == 1?"0":"") +
                        dates[1] + "/" +
                        (dates[0].length() == 1?"0":"") +
                        dates[0];
                date = CalendarUtility.getEnglishDate(date);
                System.out.println(date);
                elements1.remove(elements1.get(elements1.size() - 1));

                //body
                String body = elements3.text().trim();
                for (Element element : elements1)
                    body = body.concat(element.text().trim());

                try {
                    Feed feed = new Feed(
                            title,
                            body,
                            city,
                            page.getWebURL().toString(),
                            date,
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
