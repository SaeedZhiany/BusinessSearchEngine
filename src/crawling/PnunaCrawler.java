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
    private final Pattern filter = Pattern.compile("http://job.pnuna.com/.*استخدام.*/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() &&
                    !decodedString.startsWith("http://www.irankar.biz/category") && !decodedString.startsWith("http://www.irankar.biz/tag");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (!page.getWebURL().getURL().toLowerCase().equals("http://job.pnuna.com/")) {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements DateElements = doc.getElementsByClass(".fa fa-clock-o");
            Elements BodyElements = doc.getElementsByClass(".entry-content");
            Elements TitleElements = doc.getElementsByClass(".box .box-top");
            Elements CityElements = doc.getElementsByClass(".post-info1 li");

            //Date
            String[] Date = DateElements.text().split(DATE_SPLITTER)[1].split(DATE_DETAIL_SPLITTER);
            Date[1] = CalendarUtility.getNumericMonth(Date[1]).toString(); // convert alphabet month to numeric

            //Body
            String Body;
            Element p = null;
            for (Element element : BodyElements)
                if (element.getElementsByTag("p") != null)
                    p = element.getElementsByTag("p").get(0);
            if (p == null) {
                System.err.println("No title found");
                Body = null;
            }
            else
                Body = p.text().trim();

            //Title
            String Title;
            Element h1 = null;
            for (Element element : TitleElements)
                if (element.getElementsByTag("p") != null)
                    h1 = element.getElementsByTag("p").get(0);
            if (p == null) {
                System.err.println("No title found");
                Title = null;
            }
            else
                Title = p.text().trim();

            //City
            String[] Cities = CityElements.get(0).text().split("-");
            String City = "";
            for (String st : Cities)
                City.concat(st + " ");
            try {
                Feed feed = new Feed(
                        Title,
                        Body,
                        City,
                        page.getWebURL().toString(),
                        Date[2] + "/" + Date[1] + "/" + Date[0],
                        pnunaDateFormat);
                feeds.add(feed);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_PNUNA);
    }
}
