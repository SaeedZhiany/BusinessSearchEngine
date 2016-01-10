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
 * crawl Aftabir's business feed
 * url is: http://www.aftabir.com/advertising/category/82/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85
 */
public class AftabirCrawler extends WebCrawler {
    Pattern filter1 = Pattern.compile
            ("http://www\\.aftabir\\.com/advertising/referrer\\.php\\?id=[\\d]+");

    Pattern filter2 = Pattern.compile
            ("http://www\\.aftabir\\.com/advertising/view/[\\d]+/.+");

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

        if (page.getWebURL().getURL().equals("http://www.aftabir.com/advertising/job.php"))
            return;

        else if (filter1.matcher(page.getWebURL().getURL()).matches()) {
            return;
        }

        else {
            try {
                System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".text-info+ span , .ad-body p:nth-child(4) , .spacer+ div , h1");
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
