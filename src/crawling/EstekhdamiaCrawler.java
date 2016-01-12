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
 * Created by SAEED on 2016-01-13
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Estekhdamia's business feed
 * url is: http://goodco.ir/
 * seed url : estekhdamia.ir/category/استخدام/
 */

public class EstekhdamiaCrawler extends WebCrawler {

    private Pattern filter1 = Pattern.compile("estekhdamia\\.ir/.*استخدام.*"); // feeds is here
    private Pattern filter2 = Pattern.compile("estekhdamia\\.ir/category/استخدام/page/[0-9]*"); // next page Button
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
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
        if (!page.getWebURL().getURL().equals("estekhdamia.ir/category/استخدام/") &&
                !filter2.matcher(page.getWebURL().getURL()).matches()){
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select(".single .code:nth-child(3) , p:nth-child(3) , .entry-content p , #content p:nth-child(2)");
            for (Element e:elements){
                System.out.println(e.text());
                System.out.println("-------------");
            }
            System.out.println();

            /*String title = elements.remove(0).text(); // title in index 0, body in index 1
            String body = elements.remove(0).text(); // now, body in index 0
            String date = elements.remove(elements.size()-1).text();
            System.out.println(date); // TODO

            date = date.split(" ")[2] + "/" +
                    CalendarUtility.getNumericMonth(date.split(" ")[1]) + "/" +
                    date.split(" ")[0];
            System.out.println(date);

            date = CalendarUtility.getEnglishDate(date);

            *//**
             * 3 states exist for remain elements
             * element.size() == 0 : city does not exist
             * element.size() == 1 : city exist in index 0
             * element.size() == 3 : city exist in index 2, index 0 and 1 is empty
             *//*
            String city = "";
            switch (elements.size()){
                case 0:
                    break;
                case 1:
                    city = elements.remove(0).text();
                    break;
                case 3:
                    city = elements.remove(2).text();
                    break;
            }
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
            }*/
        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ESTEKHDAMIA);
    }
}
