package crawling;

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
 * url is: http://www.bazarekariran.ir/
 * seed url : http://www.bazarekariran.ir/employer-adver.php
 */
public class BazareKarIranCrawler extends WebCrawler {

    private Pattern filter1 =
            Pattern.compile("http://www\\.bazarekariran\\.ir/employer-textshow\\.php\\?r=[0-9]*"); // feeds is here
    private Pattern filter2 =
            Pattern.compile("http://www\\.bazarekariran\\.ir/employer-adver\\.php\\?osta=aaa.cate=aaa.slim=[0-9]*.mohl=aaa.sort=aaa"); // should visit
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL();
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
        if (!page.getWebURL().getURL().equals("http://www.bazarekariran.ir/") &&
                !page.getWebURL().getURL().equals("http://www.bazarekariran.ir/employer-adver.php") &&
                !filter2.matcher(page.getWebURL().getURL()).matches()){
            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
            Elements elements = doc.select("#right span span , span div , h3");
            String title = elements.get(0).text().split(":")[1].split("        ")[0];
            String city = elements.get(0).text().split(":")[2].split("آخرین")[0];
            String date = elements.get(0).text().split(":")[3].split("                      ")[0].split("     ")[1];
            date = date.split(" / ")[2] + "/" +
                    (date.split(" / ")[1].length() == 1?"0":"")+
                    date.split(" / ")[1] + "/" +
                    (date.split(" / ")[0].length() == 1?"0":"")+
                    date.split(" / ")[0];

            List<String> list = Arrays.asList(elements.get(0).text().split(":"));
            list = list.subList(5, list.size()-1); // remove additional info for getting body

            StringBuilder builder = new StringBuilder("");
            for (String s:list){
                builder.append(s).append(" ");
            }
            String body = builder.toString();

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
        ExcelUtility.writeToExcel(feeds, Params.SHEET_BAZAREKARIRAN);
    }
}
