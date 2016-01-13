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
 * crawl Estekhdam24's business feed
 * url is: http://estekhdam24.com/
 * seed url : http://estekhdam24.com/
 */

public class Estekhdam24Crawler extends WebCrawler {

    private Pattern filter1 =
            Pattern.compile("http://estekhdam24\\.com/(tabriz|orumie|ardabil|esfahan|alborz|elam|bushehr|shahrkord" +
                    "|birjand|mashhad|bojnurd|ahvaz|zanjan|semnan|zahedan|shiraz|ghazvin|qom|kurdestan|kerman" +
                    "|kermanshah|yasuj|gorgan|rasht|khoramabad|sari|arak|bandarabbas|hamadan|yazd)/.*.html"); // feeds is here
    private Pattern filter2 =
            Pattern.compile("http://estekhdam24\\.com/category/(tabriz|orumie|ardabil|esfahan|alborz|elam|bushehr|shahrkord" +
                    "|birjand|mashhad|bojnurd|ahvaz|zanjan|semnan|zahedan|shiraz|ghazvin|qom|kurdestan|kerman" +
                    "|kermanshah|yasuj|gorgan|rasht|khoramabad|sari|arak|bandarabbas|hamadan|yazd)/"); // should visit
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
        if (!page.getWebURL().getURL().equals("http://estekhdam24.com/") &&
                !filter2.matcher(page.getWebURL().getURL()).matches()){
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select("#dle-content p:nth-child(1) , .roshitepa , .singlegavi");

            String date = elements.remove(0).text().split(",")[0];
            date = date.split("-")[2] + "/" +
                    date.split("-")[1] + "/" +
                    (date.split("-")[0].length() == 1?"0":"")+
                    date.split("-")[0];

            String title = elements.remove(0).text(); // title and city is here, in index 0, because previous remove
            String city = "نامعلوم";
            if (title.contains("در")){
                city = title.split("در")[1];
                title = title.split("در")[0];
            }
            else if (title.contains("استان")){
                city = title.split("استان")[1];
                title = title.split("استان")[0];
            }
            StringBuilder builder = new StringBuilder("");
            for (Element element:elements){
                builder.append(element.text()).append(" ");
            }
            String body = builder.toString();

            /**
             * 3 states exist for remain elements
             * element.size() == 0 : city does not exist
             * element.size() == 1 : city exist in index 0
             * element.size() == 3 : city exist in index 2, index 0 and 1 is empty
             */
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
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ESTEKHDAM24);
    }
}
