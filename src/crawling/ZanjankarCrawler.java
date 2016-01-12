package crawling;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
 * crawl zanjankar's business feed
 * url is: http://www.zanjankar.ir/Pages/Joblist.aspx
 */
public class ZanjankarCrawler extends WebCrawler {

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return false;
    }

    @Override
    public void visit(Page page) {
        super.visit(page);
    }

    public static ArrayList<String> getSeedPages(){
        // config the selenium robot
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", false);
        /*caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "C:\\Users\\SAEED\\Desktop\\BusinessSearchEngine\\library\\phantomjs.exe"
        );*/
        caps.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        caps.setVersion(DesiredCapabilities.firefox().getVersion());
        WebDriver driver = new PhantomJSDriver(caps);
        String baseUrl = "http://www.zanjankar.ir/Pages/Joblist.aspx";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);

        // find page number buttons
        WebElement ul = driver.findElement(By.xpath("./[@id='BodyContentPlaceHolder']/table/tbody/tr/td[1]/div/ul"));
        List<WebElement> lis = ul.findElements(By.tagName("li"));

        System.out.println(lis.size());/*
        List<WebElement> elements = new ArrayList<WebElement>();
        for (WebElement button : lis){
            Actions action = new Actions(driver);
            action.moveToElement(button).click();
            elements.addAll(driver.findElementsByCssSelector(".adsTitle+ p span+ span , .adsDes , .adsTitle"));

        }

        for (WebElement e : elements){
            System.out.println(e.getText());
            System.out.println("----------");
        }*/
        return new ArrayList<String>();
    }

    private static String getPageUrlXPath(int pageNumber){
        return "//*[@id=\"BodyContentPlaceHolder\"]/table/tbody/tr/td[1]/div/ul/li[" +
                String.valueOf(pageNumber) +
                "]/a";
    }
}
