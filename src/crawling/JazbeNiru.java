package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-13
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Bazarkariran's business feed
 * url is: http://www.jazbeniru.com/
 * seed url : http://www.jazbeniru.com/job_posting/reputable_company/%D8%A2%DA%AF%D9%87%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D8%B1%DA%A9%D8%AA-%D9%87%D8%A7%DB%8C-%D9%85%D8%B9%D8%AA%D8%A8%D8%B1.aspx
 */
public class JazbeNiru extends WebCrawler {

    private Pattern filter1 =
            Pattern.compile("http://www\\.jazbeniru\\.com/[0-9]*/.*\\.aspx"); // feeds is here
    private Pattern filter2 =
            Pattern.compile("http://www\\.jazbeniru\\.com/job_posting/reputable_company/آگهی-های-استخدام-شرکت-های-معتبر\\.aspx\\?page=[0-9]*"); // should visit

    private ArrayList<Feed> feeds = new ArrayList<Feed>();


    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return (filter1.matcher(decodedString).matches() ||
                    filter2.matcher(decodedString).matches());

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if (!page.getWebURL().getURL().equals("http://www.jazbeniru.com/") &&
                !page.getWebURL().getURL().equals("http://www.jazbeniru.com/job_posting/reputable_company/%D8%A2%DA%AF%D9%87%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D8%B1%DA%A9%D8%AA-%D9%87%D8%A7%DB%8C-%D9%85%D8%B9%D8%AA%D8%A8%D8%B1.aspx") &&
                !filter2.matcher(page.getWebURL().getURL()).matches() ){
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select("br+ p , .tr:nth-child(13) .width_99 , .display_none+ .tr .num_fixed , .td.width_80");

            String title = elements.get(0).text();
            if (title.contains("در"))
                title = title.split("در")[0];
            String date = elements.get(1).text();
            String city = elements.get(2).text().split(":")[1];
            String body = elements.get(3).text();

            try {
                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString(), "UTF8"),
                        date,
                        Params.DATE_FORMAT_YYYY_MM_DD
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_JAZBENIRO);
    }
}
