package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import shared.Feed;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    Pattern filter = Pattern.compile("http://www\\.istgah\\.com/fireview/kid_318/page_[\\d]*/[\\d]*");
    Pattern filter1 = Pattern.compile("http://www\\.istgah\\.com/fireview/kid_318/page_[\\d]+/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toString();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (page.getWebURL().toString().equals("http://www.istgah.com/fireview/kid_318/") ||
            filter1.matcher(page.getWebURL().toString()).matches()){System.out.println("123");
            return;
        }
        Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
        Elements elements = doc.select("");
    }

    @Override
    public void onBeforeExit() {
        super.onBeforeExit();
    }
}
