import crawling.*;
import edu.uci.ics.crawler4j.crawler.CrawlController;

/**
 * Created by SAEED on 2016-01-08
 * for project BusinessSearchEngine .
 */
public class Main {
    public static void main(String[] argv){
        try {
            CrawlController controller = BusinessCrawlController.setup();

            /*
            controller.addSeed("http://lastjob.ir/category/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B9%D9%85%D9%88%D9%85%DB%8C/");
            controller.start(LastjobCrawler.class, 1);*/

            /*
            controller.addSeed("http://donyayekar.ir/%D9%81%D9%87%D8%B1%D8%B3%D8%AA-%D8%A2%DA%AF%D9%87%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/");
            controller.start(DonyayekarCrawler.class, 1);*/

            /*
            controller.addSeed("http://www.aftabir.com/advertising/job.php");
            controller.start(AftabirCrawler.class, 1);*/

            /*
            controller.addSeed("http://job.pnuna.com");
            controller.start(PnunaCrawler.class, 1);*/

            /*controller.addSeed("http://www.baroot.com/result/index.php?page=1");
            controller.start(BarootCrawler.class, 1);*/
            controller.addSeed("http://www.eshetab.com/");
            controller.start(EshetabCrawler.class, 1);

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

}
