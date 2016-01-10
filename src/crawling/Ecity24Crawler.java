package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Ecity24's business feed
 * url is: http://www.ecity24.ir/RJobs.aspx
 */
public class Ecity24Crawler extends WebCrawler {
    Pattern filter = Pattern.compile
            ("http://www\\.ecity24\\.ir/RJobDetails\\.aspx\\?JobID=.*");


    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        /*try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() &&
                    !decodedString.startsWith("http://www.irankar.biz/category");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;           // commit
        }*/
        String href = url.getURL();
        return href.startsWith("http://www.ecity24.ir/RJobs.aspx");
    }

    @Override
    public void visit(Page page) {

        if (page.getWebURL().getURL().equals("http://www.ecity24.ir/RJobs.aspx")) {
            System.out.println("ali");

            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());

            System.out.println(doc.getElementsByTag("script").text());



        /*else {
            try {
                System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        ("#ctl00_ContentPlaceHolder1_Fieldset4 , #ctl00_ContentPlaceHolder1_lblPlaceJob , #ctl00_ContentPlaceHolder1_date , #ctl00_ContentPlaceHolder1_title");
                for (Element element : elements) {
                    System.out.println(element.text());
                    System.out.println("==========");
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }*/

        }
    }
}
