import crawling.BusinessCrawlController;
import crawling.DonyayekarCrawler;
import crawling.PnunaCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by SAEED on 2016-01-08
 * for project BusinessSearchEngine .
 */
public class Main {
    public static void main(String[] argv){
        try {
            /*CrawlController controller = BusinessCrawlController.setup();
            controller.addSeed("http://lastjob.ir/category/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B9%D9%85%D9%88%D9%85%DB%8C/");
            controller.start(LastjobCrawler.class, 1);*/

            CrawlController controller = BusinessCrawlController.setup();
            controller.addSeed("http://job.pnuna.com");
            controller.start(PnunaCrawler.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

}
