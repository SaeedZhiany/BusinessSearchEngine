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
 * crawl karan's business feed
 * url is: http://www.karan.ir/
 */
public class KaranCrawler extends WebCrawler {
    Pattern filter = Pattern.compile
            ("http://www\\.karan\\.ir/request/(index|index#page=[\\d]+|showrequest/.*)");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if(filter.matcher(href).matches())
            System.out.println(href);
        return filter.matcher(href).matches();
    }

    @Override
    public void visit(Page page) {
        /*if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Document document = Jsoup.parse(htmlParseData.getHtml());
            Elements elements = document.getElements(".job-title");
            System.out.println(elements.get(0).text());
        }*/
    }
}
