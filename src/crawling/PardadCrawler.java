package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl pardad's business feed
 * url is: http://estekhdam.pardad.ir/
 */
public class PardadCrawler extends WebCrawler {
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return super.shouldVisit(referringPage, url);
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }
}