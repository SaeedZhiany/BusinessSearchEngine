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
 * crawl Estekhdamkhabar's business feed
 * url is: http://estekhdamkhabar.com/
 */
public class EstekhdamkhabarCrawler extends WebCrawler {

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();


    Pattern filter = Pattern.compile
            ("http://estekhdamkhabar\\.com/[\\d]+/[\\d]+/[\\d]+/.*استخدام.*");

    @Override
    public boolean shouldVisit(Page page, WebURL url)  {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            return filter.matcher(decodedString).matches() ||
                    decodedString.matches("http://estekhdamkhabar\\.com/page/[\\d]+/");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public void visit(Page page) {

        if (page.getWebURL().getURL().equals("http://estekhdamkhabar.com/"))
            return;

        else if (page.getWebURL().getURL().matches("http://estekhdamkhabar\\.com/page/[\\d]+/")) {
            return;
        }

        else {
            try {
                System.out.println(URLDecoder.decode(page.getWebURL().getURL(), "UTF8"));
                Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                Elements elements = doc.select
                        (".raquo+ a , .single-content p , .date , .title a");

                String title = elements.get(0).text();

                System.out.println(title);

                String[] rawDate = elements.get(1).text().split(" ");
                rawDate[1] = CalendarUtility.getNumericMonth(rawDate[1]).toString();
                rawDate[0] = CalendarUtility.getEnglishDate(rawDate[0]);
                rawDate[3] = CalendarUtility.getEnglishDate(rawDate[3]);


                String date = rawDate[3] + "/" + rawDate[1] + "/" + rawDate[0];

                String city = elements.get(2).text();

                String[] splitedtoFetchCity = city.split(" ");
                if (splitedtoFetchCity.length > 3) {
                    city = splitedtoFetchCity[splitedtoFetchCity.length - 2] + " " +
                            splitedtoFetchCity[splitedtoFetchCity.length - 1];
                } else {
                    city = splitedtoFetchCity[splitedtoFetchCity.length - 1];
                }


                elements.remove(elements.get(0));
                elements.remove(elements.get(1));
                elements.remove(elements.get(2));

                StringBuilder builder = new StringBuilder("");
                for (Element element: elements) {
                    builder.append(element.text());
                }

                String body = builder.toString();

                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        URLDecoder.decode(page.getWebURL().toString(), "UTF8"),
                        date,
                        Params.DATE_FORMAT_YYYY_MM_DD
                ));

            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ESTEKHDAM_KABAR);
    }

}
