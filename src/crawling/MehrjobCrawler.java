package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import shared.CalendarUtility;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
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

    private ArrayList<Feed> feeds;


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
                        (".blog-post p:nth-child(1) , .blog-post-meta , .blog-post-title");

                String title = elements.get(0).text();

                String city = "";
                for (String c: Params.citiesList) {
                    if (title.contains(c)) {
                        city = c;
                        break;
                    }
                }

                System.out.println(city);

                String[] date = elements.get(1).text().split(" ");
                date[1] = CalendarUtility.getNumericMonth(date[1]).toString();

                //System.out.println(date[2] + "/" + date[1] + "/" + date[0]);

                String body = elements.get(2).text();

                Feed feed = new Feed(
                        title,
                        body,
                        city,
                        page.getWebURL().toString(),
                        date[2]+"/"+date[1]+"/"+date[0],
                        Params.DATE_FORMAT_YYYY_MM_DD
                );
                feeds.add(feed);
            }
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBeforeExit() { // write the results in excel before exit
        ExcelUtility.writeToExcel(feeds, Params.SHEET_MEHRJOB);
    }

}
