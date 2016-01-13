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
 * crawl Eshetap's business feed
 * url is: http://www.eshetab.com/
 */
public class EshetabCrawler extends WebCrawler {

    Pattern filter1 = Pattern.compile
            ("http://eshetab\\.com/state/.*");

    Pattern filter2 = Pattern.compile
            ("http://eshetab\\.com/ads/.*");
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


        if (page.getWebURL().getURL().equals("http://www.eshetab.com/"))
            return;
        else if (filter1.matcher(page.getWebURL().getURL()).matches()) {
            return;
        }

        else {
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select
                    (".addsTexts , .nameDiv:nth-child(3) , .nameDiv:nth-child(2) , .DivTitle span");
            String title = elements.get(0).text();
            String date = elements.get(2).text().split(":")[1].substring(1); // it has one space in zero index

            date = date.split("-")[2] + "/" +
                    date.split("-")[1] + "/" +
                    date.split("-")[0];

            date = CalendarUtility.getEnglishDate(date);
            String city;
            try{
                //System.out.println(elements.get(3).text());
                city = elements.get(3).text().split(":")[1].substring(1)
                        .split("-")[0];
                if(city.contains(",")){
                    city = city.substring(1,city.length()-1);
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("شهر موجود نیست");
                city = "";
            }
            String body = elements.get(4).text();
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
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ESHETAB);
    }
}
