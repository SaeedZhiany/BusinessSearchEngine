package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Baroot's business feed
 * url is: http://www.baroot.com/
 * seed : http://www.baroot.com/result/index.php?page=1
 */
public class BarootCrawler extends WebCrawler {
    Pattern filter = Pattern.compile
            ("http://www\\.baroot\\.com/detail/.*[\\d]+\\.html");

    private ArrayList<Feed> feeds = new ArrayList<Feed>();
    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() ||
                    decodedString.matches("http://www\\.baroot\\.com/result/index\\.php\\?page=[\\d]+");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public void visit(Page page) {

        if (page.getWebURL().getURL().equals("http://www.baroot.com/result/index.php?page=1"))
            return;

        else if (page.getWebURL().getURL().matches("http://www\\.baroot\\.com/result/index\\.php\\?page=[\\d]+")) {
            return;
        }

        else {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select
                    (".col-md-12 span , .date-publish , p.col-xs-12 , h1");
            String date = elements.get(0).text();
            String title = elements.get(1).text().split(" : ")[1];
            String body = elements.get(2).text().split(" : ")[1];
            String city = elements.get(3).text().split(" - ")[1];

            try {
                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString()),
                        date,
                        Params.DATE_FORMAT_YYYY_MM_DD
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_BAROOT);
    }
}
