package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
 * seed is: http://www.mehrjob.com/
 */
public class MehrjobCrawler extends WebCrawler {
    Pattern filter1 = Pattern.compile
            ("http://www\\.mehrjob\\.com/display/[\\d]+/.*استخدام.*");
    Pattern filter2 = Pattern.compile
            ("http://www\\.mehrjob\\.com/page/[\\d]+/");

    private ArrayList<Feed> feeds = new ArrayList<Feed>();


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
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".blog-post p:nth-child(1) , .blog-post-meta , .blog-post-title");

                String title = elements.get(0).text();

                String city = "نامعلوم";
                for (String c: Params.citiesList) {
                    if (title.contains(c)) {
                        city = c;
                        break;
                    }
                }

                String[] dates = elements.get(1).text().split(" ");
                dates[1] = CalendarUtility.getNumericMonth(dates[1]);
                String date = dates[2]+"/"+dates[1]+"/"+dates[0];
                date = CalendarUtility.getEnglishDate(date);

                String body = elements.get(2).text();

                Feed feed = new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString(), "UTF8"),
                        date
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
