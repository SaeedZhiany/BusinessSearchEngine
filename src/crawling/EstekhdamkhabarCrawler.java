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
 * crawl Estekhdamkhabar's business feed
 * url is: http://estekhdamkhabar.com/
 */
public class EstekhdamkhabarCrawler extends WebCrawler {

    Pattern filter = Pattern.compile
            ("http://estekhdamkhabar\\.com/[\\d]+/[\\d]+/[\\d]+/.*استخدام.*");

    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() ||
                    decodedString.matches("http://estekhdamkhabar\\.com/page/[\\d]+/");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public void visit(Page page) {

        if (page.getWebURL().getURL().equals("http://estekhdamkhabar.com"))
            return;

        else if (page.getWebURL().getURL().matches("http://estekhdamkhabar\\.com/page/[\\d]+/")) {
            return;
        }

        else {
            try {
                System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".raquo+ a , .single-content div , .single-content p , .date , .title a");
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
