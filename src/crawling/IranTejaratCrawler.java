package crawling;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * this class has been write to
 * crawl iran-tejarat's business feed
 * url is: http://iran-tejarat.com/Ads1302/501546.html
 */

/**
 * این سایت یک صحفه ایستا داره که دستی درست شده
 * شماره صفحه هایی هم که پایین صفحه س همشون redirect میشن
 * به همین صفحه
 * کلا 10 تا خبر بیشتر نیست
 * که البته هر کاری هم می کنیم، کرالر نمی تونه url ها رو بگیره
 * در واقع shouldvisit اصلا اجرا نمیشه!!!
 */
public class IranTejaratCrawler extends WebCrawler{
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        return true;
    }

    @Override
    public void visit(Page page) {

    }
}
