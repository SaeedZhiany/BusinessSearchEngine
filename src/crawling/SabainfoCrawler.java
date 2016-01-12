package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import shared.Feed;

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
 * crawl sabainfo's business feed
 * url is: http://www.sabainfo.ir/
 */
public class SabainfoCrawler {

    private HtmlUnitDriver driver;

    private final ArrayList<Feed> feeds = new ArrayList<Feed>();

    public void start(String baseURL){
        driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        visit(baseURL);
        onBeforeExit();
    }

    private void onBeforeExit() {
        //ExcelUtility.writeToExcel(feeds, Params.SHEET_Jobcity);
    }

    private void visit(String baseURL) {

        ArrayList<String> shouldVisitURLs = getSeedPages(baseURL);
        for (String url : shouldVisitURLs){
            driver.get(url);
            List<WebElement> elements = driver.findElementsByCssSelector
                    ("#aspnetForm span span:nth-child(1) , #ctl00_ContentPlaceHolder1_txtcontent p:nth-child(1) , #ctl00_ContentPlaceHolder1_txtTitle");

            for (WebElement e : elements)
                System.out.println(e.getText());
/*
            String title = elements.get(0).getText();
            String date = elements.get(1).getText();
            String city = elements.get(2).getText();
            String body = elements.get(3).getText();
*/

            System.out.println("-------------");

            /*try {
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
            }*/
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
                        "//*[@id=\"ctl00_cphMiddle_SAMPARL_Web_View_NewsUI_NewsBoxCustomized20cphMiddle_2759_pgItems_dlPager_ctl02_lblPage\"]")
                        .getText();
        final int count = Integer.parseInt(info.split(" ")[3]);

        ArrayList<String> seedPageURLs = new ArrayList<String>();
        do {
            List<WebElement> trs =
                    driver.findElementsByXPath(".//*[@id='ctl00_cphMiddle_SAMPARL_Web_View_NewsUI_NewsBoxCustomized20cphMiddle_2759_dlItems']/tbody/tr");

            for (WebElement tr:trs){
                String url = tr.findElement(By.tagName("td")).findElement(By.tagName("div")).findElement(By.tagName("a")).getAttribute("href");
                seedPageURLs.add(url);
                System.out.println(url);
            }
            WebElement nextButton = driver.findElementByXPath("//*[@id=\"ctl00_cphMiddle_SAMPARL_Web_View_NewsUI_NewsBoxCustomized20cphMiddle_2759_pgItems_btnNext\"]");
            if(nextButton == null)
                break;
            nextButton.click();
        }while (seedPageURLs.size() < count);

        return seedPageURLs;
    }
}
