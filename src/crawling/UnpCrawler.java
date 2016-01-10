package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl unp's business feed
 * url is: http://www.unp.ir/news/job
 */
public class UnpCrawler extends WebCrawler {
    Pattern filter = Pattern.compile
            ("http://www\\.unp\\.ir/news/job((\\?page=[\\d]+)|(/.+/[\\d]+))");


    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        /*try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() &&
                    !decodedString.startsWith("http://www.irankar.biz/category");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }*/
        String href = url.getURL();
        return filter.matcher(href).matches();
    }

    @Override
    public void visit(Page page) {

        if (page.getWebURL().getURL().equals("http://www.unp.ir/news/job"))
            return;

        else if (page.getWebURL().getURL().matches("http://www\\.unp\\.ir/news/job\\?page=[\\d]+")) {
            return;
        }

        else {
            try {
                System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        ("p font , .dirs:nth-child(2) , .newstitle a");
                for (Element element : elements) {
                    System.out.println(element.text());
                    System.out.println("==========");
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }

        }
    }
}
