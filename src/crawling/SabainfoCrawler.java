package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import shared.Feed;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl sabainfo's business feed
 * url is: http://www.sabainfo.ir/
 */
public class SabainfoCrawler extends WebCrawler {

    private final String DATE_SPLITTER = ": ";
    private final String DATE_DETAIL_SPLITTER = " ";
    private final String pnunaDateFormat = "yyyy/mm/dd";
    private final Pattern filter = Pattern.compile("http://www.sabainfo.ir/fa/ssp/employnotic.*آگهی استخدام.*/");

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    /*@Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        if (!page.getWebURL().getURL().toLowerCase().equals("http://www.sabainfo.ir/fa/ssp/employnotic/")) {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
        return super.shouldVisit(referringPage, url);
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }*/
}
