package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl Ecity24's business feed
 * url is: http://www.ecity24.ir/RJobs.aspx
 * seed : http://www.ecity24.ir/RJobs.aspx
 */
public class Ecity24Crawler {
    Pattern filter = Pattern.compile
            ("http://www\\.ecity24\\.ir/RJobDetails\\.aspx\\?JobID=.*");

    private HtmlUnitDriver driver;
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

    public void start(String baseURL){
        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        visit(baseURL);
        onBeforeExit();
    }

    private void onBeforeExit() {
        ExcelUtility.writeToExcel(feeds, Params.SHEET_ECITY24);
    }

    private void visit(String baseURL) {

        ArrayList<String> shouldVisitURLs = getSeedPages(baseURL);
        for (String url : shouldVisitURLs){
            driver.get(url);
            List<WebElement> elements = driver.findElementsByCssSelector
                    ("#ctl00_ContentPlaceHolder1_Fieldset4 , #ctl00_ContentPlaceHolder1_lblPlaceJob , #ctl00_ContentPlaceHolder1_date , #ctl00_ContentPlaceHolder1_title");

            String title = elements.get(0).getText();
            String date = elements.get(1).getText();
            String city = elements.get(2).getText();
            String body = elements.get(3).getText();

            try {
                feeds.add(new Feed(
                        title,
                        body,
                        city,
                        baseURL,
                        date,
                        Params.DATE_FORMAT_YYYY_MM_DD
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> getSeedPages(String baseURL){
        driver.get(baseURL);

        /**
        * تعداد خبرها دراینجا نوشته شده که میشه ازش واسه توقف کار تابع استفاده کرد
        * هفتمین توکن تعداد کل خبرهاست
        */
        String info =
                driver.findElementByXPath(
                        "//*[@id=\"ctl00_ContentPlaceHolder1_PagerControl2_Label_NowShowing\"]").getText();
        final int count = Integer.parseInt(info.split(" ")[6]);

        ArrayList<String> seedPageURLs = new ArrayList<String>();
        do {
            List<WebElement> trs =
                    driver.findElementsByXPath(".//*[@id='ctl00_ContentPlaceHolder1_UpdatePanel2']/table/tbody/tr");
            trs.remove(0); // عناوین جدول
            for (WebElement tr:trs){
                String url = tr.findElements(By.tagName("td")).get(0).findElement(By.tagName("a")).getAttribute("href");
                seedPageURLs.add(url);
            }
            WebElement nextButton = driver.findElementByXPath(".//*[@id='ctl00_ContentPlaceHolder1_PagerControl2_LinkButton_Next']");

            nextButton.click();
        }while (seedPageURLs.size() < count);

        return seedPageURLs;
    }
}
