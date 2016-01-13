package crawling;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import shared.Feed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl jobcity's business feed
 * url is: http://www.jobcity.ir/
 */

//  این سایت Script است
public class JobcityCrawler {

    private HtmlUnitDriver driver;
    private ArrayList<Feed> feeds = new ArrayList<Feed>();

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

        ArrayList<String> seedPageURLs = new ArrayList<String>();
        WebElement lastPageButton;
        do {
            List<WebElement> nextButtons =
                    driver.findElementsByCssSelector("tr+ .gvpager td+ td a");
            for (WebElement e: nextButtons)
                System.out.println(e.getText());
            do{
                List<WebElement> trs =
                        driver.findElementsByCssSelector("h2 a");
                for (WebElement tr:trs){
                    seedPageURLs.add(tr.getAttribute("href"));
                }
                lastPageButton = nextButtons.remove(0);
                Actions action = new Actions(driver);
                action.moveToElement(lastPageButton).click();
            }while (!nextButtons.isEmpty());
        }while (lastPageButton.getText().equals("..."));
        // for last page
        List<WebElement> trs =
                driver.findElementsByCssSelector("h2 a");
        for (WebElement tr:trs){
            seedPageURLs.add(tr.getAttribute("href"));
        }
        return seedPageURLs;
    }
}
