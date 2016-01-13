package crawling;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl niazma's business feed
 * url is: http://niazma.ir/
 * seed : http://niazma.ir/listings/category/استخدام-و-کاریابی/
 */
public class NiazmaCrawler extends WebCrawler {

    private final Pattern filter1 = Pattern.compile("http://niazma\\.ir/listings/.*/"); // content is here
    private final Pattern filter2 = Pattern.compile("http://niazma\\.ir/listings/category/.*/"); // should visit
    private final Pattern filter3 = Pattern.compile("http://niazma\\.ir/listings/category/.*/page/[0-9]+"); // should visit
    private final Pattern filter4 = Pattern.compile("http://niazma\\.ir/listings/tag/.*/"); // should not visit
    private final Pattern filter5 = Pattern.compile("http://niazma\\.ir/listings/.*/feed/"); // should not visit
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return (filter1.matcher(decodedString).matches() ||
                    filter2.matcher(decodedString).matches() ||
                    filter3.matcher(decodedString).matches()) &&
                    !(filter4.matcher(decodedString).matches() ||
                        filter5.matcher(decodedString).matches());
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
        if(filter2.matcher(page.getWebURL().toString()).matches() ||
                filter3.matcher(page.getWebURL().toString()).matches() ||
                page.getWebURL().getURL().toLowerCase().equals("http://niazma.ir/categories/")){
        }
        else {
            Document document = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = document.select("#overview p:nth-child(1) , #main h1 a , .address");
            /*for (Element e : elements)
                System.out.println(e.text());*/
            String title = elements.get(0).text();
            String city = elements.get(1).text();
            if(city.contains("-")){
                city = city.split("-")[0]; // به احتمال زیاد اینجا آدرس فارسی است
            } else if(city.contains(",")){
                city = city.split(",")[0]; // به احتمال زیاد اینجا آدرس انگلیسی است
                try {
                    // convert name of city to farsi
                    city = Params.citiesList.get(Params.citiesListEnglish.indexOf(city.toLowerCase()));
                }catch (IndexOutOfBoundsException e){
                    city = "نامعلوم";
                }
            }
            String body = elements.get(2).text();
            PersianCalendar calendar = new PersianCalendar(new Date());
            String date = String.valueOf(calendar.get(Calendar.YEAR)) + "/" +
                    (calendar.get(Calendar.MONTH) > 10? "" : "0") +
                    String.valueOf(calendar.get(Calendar.MONTH)) + "/" +
                    (calendar.get(Calendar.DAY_OF_MONTH) > 10? "" : "0") +
                    String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
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
        ExcelUtility.writeToExcel(feeds, Params.SHEET_NIAZMA);
    }
}
