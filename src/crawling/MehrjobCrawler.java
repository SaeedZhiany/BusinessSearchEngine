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
 * crawl mehrjob's business feed
 * url is: http://www.mehrjob.com/
 */
public class MehrjobCrawler extends WebCrawler {
    Pattern filter1 = Pattern.compile
            ("http://www\\.mehrjob\\.com/display/[\\d]+/.*استخدام.*");
    Pattern filter2 = Pattern.compile
            ("http://www\\.mehrjob\\.com/page/[\\d]+/");


    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter1.matcher(decodedString).matches() ||
                    filter2.matcher(decodedString).matches();

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
            if (decodedString.equals("http://www.mehrjob.com/")) {
                return;
            }
            else if (filter2.matcher(page.getWebURL().getURL()).matches()) {
                return;
            } else {

                System.out.println(URLDecoder.decode(href, "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".blog-post p , .blog-post-title");
                for (Element element : elements) {
                    System.out.println(element.text());
                    System.out.println("==========");
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }


    }

}
