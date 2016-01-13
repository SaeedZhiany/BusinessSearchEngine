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
 * Created by SAEED on 2016-01-13
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Estekhdamia's business feed
 * url is: http://goodco.ir/
 * seed url : http://estekhdamia.ir/category/استخدام/
 */

public class EstekhdamiaCrawler extends WebCrawler {

    private Pattern filter1 = Pattern.compile("http://estekhdamia\\.ir/.*استخدام.*/"); // feeds is here
    private Pattern filter2 = Pattern.compile("http://estekhdamia\\.ir/category/استخدام/page/[0-9]*/"); // next page Button
    private Pattern filter3 = Pattern.compile("http://estekhdamia\\.ir/category/.*استخدام.*/"); // should not visit
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return ( filter1.matcher(decodedString).matches() ||
                    filter2.matcher(decodedString).matches() ) &&
                    !filter3.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (!page.getWebURL().getURL().equals("http://estekhdamia.ir/category/استخدام/") &&
                !filter2.matcher(page.getWebURL().getURL()).matches()){
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select(".single .code:nth-child(3) , .cadr p , h1 a");

            String date = elements.get(0).text().split(":")[1].split("-")[0];
            String year = date.split(" ")[3];
            String month = date.split(" ")[2];
            month = month.substring(0,month.length()-1);
            month = CalendarUtility.getNumericMonth(month);
            String day = date.split(" ")[1];
            date =  year + "/" +
                    month + "/" +
                    day;
            date = CalendarUtility.getEnglishDate(date);

            String title = elements.get(1).text(); // title and city is here
            String city = "نامعلوم";
            if (title.contains("در")){
                city = title.split("در")[1].split(" ")[0];
                title = title.split("در")[0];
            }
            String body = elements.get(2).text();

            /**
             * 3 states exist for remain elements
             * element.size() == 0 : city does not exist
             * element.size() == 1 : city exist in index 0
             * element.size() == 3 : city exist in index 2, index 0 and 1 is empty
             */
            try {
                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString(), "UTF8"),
                        date,
                        Params.DATE_FORMAT_YYYY_MM_DD
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ESTEKHDAMIA);
    }
}
