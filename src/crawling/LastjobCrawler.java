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
 * crawl lastjob's business feed
 * url is: http://lastjob.ir/
 */

/**
 * مشکل این سایت :
 * محتواها با قالب یکسان ساخته نشده و نمی توان داده های لازم را به
 * درستی استخراج کرد.
 * به همین دلیل این سایت را کنار گذاشتیم
 */
public class LastjobCrawler extends WebCrawler {

    private final Pattern filter = Pattern.compile("http://lastjob\\.ir/.*(استخدام|دعوت به همکاری).*/");
    private final Pattern filter2 = Pattern.compile("http://lastjob\\.ir/category/استخدام-عمومی/page/[0-9]*/");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return (filter.matcher(decodedString).matches() && !decodedString.contains("هفته"))
                    || filter2.matcher(decodedString).matches();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if(filter2.matcher(page.getWebURL().toString()).matches())
            return;
        if (!page.getWebURL().getURL().toLowerCase().equals("http://lastjob.ir/")) {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select("#single-ul li~ li+ li , #produce+ #single p+ p , #single p a");

            try {
                System.out.println("**" + URLDecoder.decode(page.getWebURL().toString(), "UTF8") + "**");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            for (Element element: elements){
                System.out.println(element.text());
                System.out.println("---------------------");
            }
        }
    }
}
