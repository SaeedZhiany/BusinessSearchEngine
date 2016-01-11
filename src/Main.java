import crawling.BusinessCrawlController;
import crawling.IrankarCrawler;
import crawling.PnunaCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by SAEED on 2016-01-08
 * for project BusinessSearchEngine .
 */
public class Main {
    public static void main(String[] argv){
        try {
            CrawlController controller = BusinessCrawlController.setup();
            controller.addSeed("http://job.pnuna.com");
            controller.start(PnunaCrawler.class, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
