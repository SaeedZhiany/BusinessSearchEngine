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
import shared.CalendarUtility;
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
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl donyayekar's business feed
 * url is: http://donyayekar.ir/
 */
public class DonyayekarCrawler extends WebCrawler {
    private Pattern filter1 = Pattern.compile
            ("http://donyayekar\\.ir/فهرست-آگهی-های-استخدام/page/[\\d]+/");
    private Pattern filter2 = Pattern.compile
            ("http://donyayekar\\.ir/job/.+/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return (filter1.matcher(decodedString).matches() || filter2.matcher(decodedString).matches())
                    && !decodedString.contains("category")
                    && !decodedString.contains("type");

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        try {
            String href = page.getWebURL().getURL();
            String decodedString = URLDecoder.decode(href, "UTF8");
            if (decodedString.startsWith("http://donyayekar.ir/فهرست-آگهی-های-استخدام/")) {

            }
            else if (filter1.matcher(page.getWebURL().getURL()).matches()) {

            } else {
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".jobrob , .wpjb-expanded-map-row+ tr .wpjb-info-label+ td , .wpjb-expand-map , .entry-title");
                String title = elements.get(0).text();
                String body = elements.get(1).text();
                String city = elements.get(2).text();
                String date = elements.get(3).text();
                if(date.equals("امروز")){
                    PersianCalendar calendar = new PersianCalendar(new Date());
                    date = String.valueOf(calendar.get(Calendar.YEAR)) + "/" +
                            (calendar.get(Calendar.MONTH) > 10? "" : "0") +
                            String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                            (calendar.get(Calendar.DAY_OF_MONTH) > 10? "" : "0") +
                            String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                }
                else{
                    date = "13" + CalendarUtility.getEnglishDate(date);
                }
                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString(), "UTF8"),
                        date
                ));
            }
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_DONYAYEKAR);
    }
}
