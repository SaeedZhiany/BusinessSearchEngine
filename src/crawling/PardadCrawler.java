package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl pardad's business feed
 * url is: http://estekhdam.pardad.ir/
 */
public class PardadCrawler extends WebCrawler {

    private ArrayList<String> Seed = new ArrayList<String>(Arrays.asList(
            "http://estekhdam.pardad.ir/GA_13.aspx",
            "http://estekhdam.pardad.ir/GA_4.aspx",
            "http://estekhdam.pardad.ir/GA_2.aspx",
            "http://estekhdam.pardad.ir/GA_3.aspx",
            "http://estekhdam.pardad.ir/GA_18.aspx",
            "http://estekhdam.pardad.ir/GA_17.aspx",
            "http://estekhdam.pardad.ir/GA_22.aspx",
            "http://estekhdam.pardad.ir/GA_72.aspx",
            "http://estekhdam.pardad.ir/news_group.aspx?Page=12&a=1&id=22"
    ));
    private ArrayList<String> ForbidenURLs = new ArrayList<String>(Arrays.asList(               //The URLs should not be crawled
            "http://estekhdam.pardad.ir/N_10414",
            "http://estekhdam.pardad.ir/N_14536",
            "http://estekhdam.pardad.ir/N_5432",
            "http://estekhdam.pardad.ir/N_327",
            "http://estekhdam.pardad.ir/N_17469"
    ));
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        try {
            String href = url.getURL().toLowerCase();
            String decodedString = URLDecoder.decode(href, "UTF8");
            /*ArrayList<String> Forbid = new ArrayList<String>();

            for (String URL : Seed)
                Forbid.add(URL);
            for (String URL : ForbidenURLs)
                Forbid.add(URL);*/
            boolean b = true;

            int i =0;
            for (String st : ForbidenURLs){System.out.println((i++) + " st: " + st);
                if (decodedString.contains(st)){System.out.println((i++) + " str: " + st);
                    b = false;}}
            return decodedString.startsWith("http://estekhdam.pardad.ir/N_") && b;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void visit(Page page) {
                for (String seed : Seed)
                    try {System.out.println("Start... " + URLDecoder.decode(page.getWebURL().getURL(), "UTF8").toLowerCase());
                        if (!(URLDecoder.decode(page.getWebURL().getURL(), "UTF8").toLowerCase().equals(seed.toString().toLowerCase()))) {
                            Document doc = Jsoup.parse(((HtmlParseData) page.getParseData()).getHtml());
                            Elements elements = doc.select("#ctl00_cphData_lblTitle");
                            for (Element element : elements) {
                                System.out.println(element.text());
                                System.out.println("+++++");
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
    }
}
