package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl lastjob's business feed
 * url is: http://lastjob.ir/
 */
public class LastjobCrawler extends WebCrawler {

    private final Pattern filter = Pattern.compile("http://www\\.lastjob\\.ir/.*[استخدام|آگهی].*/");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return super.shouldVisit(referringPage, url);
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }
}
