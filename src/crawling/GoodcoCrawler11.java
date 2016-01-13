package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.swing.plaf.synth.Paint9Painter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * Created by Mostafa on 1/12/2016.
 */

/**
 * this class has been write to
 * crawl goodCo's business feed
 * url is: http://goodco.ir/opportunities/type/%D9%81%D8%B1%D8%B5%D8%AA-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85
 */
public class GoodcoCrawler11 extends WebCrawler{

    Pattern filter =
            Pattern.compile("http://goodco\\.ir/opportunities/type/%D9%81%D8%B1%D8%B5%D8%AA-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85(\\?page=[\\d]+)?");
    Pattern filter1 = Pattern.compile("http://goodco\\.ir/opportunities/alphabet/.");
    Pattern filter2 = Pattern.compile("http://job\\.mida-co\\.ir/EmploymentAds/[\\d]+/.*");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toString();
            String decodedStrins = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedStrins).matches() ||
                   filter1.matcher(decodedStrins).matches() ||
                   filter2.matcher(decodedStrins).matches();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public void visit(Page page) {
        if (filter.matcher(page.getWebURL().toString()).matches()){
            return;
        }
        if (filter1.matcher(page.getWebURL().toString()).matches()){
            return;
        }
        else{
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select("td tr+ tr td , .logo2+ div");
            int i = 0;
            String s = "";

            for (Element element: elements){
                System.out.println(page.getWebURL().toString() + "  \n\n  " +elements.text());
                System.out.println("++++++++");
            }
        }
    }

    @Override
    public void onBeforeExit() {
        super.onBeforeExit();
    }
}
