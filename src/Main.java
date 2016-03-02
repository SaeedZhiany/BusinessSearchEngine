import indexing.Indexer;
import indexing.Searcher;
import shared.Feed;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SAEED on 2016-01-08
 * for project BusinessSearchEngine .
 */
public class Main {
    public static void main(String[] argv){
        try {
            Searcher searcher = Searcher.getInstance();
            //searcher.searchTitle("مهندسی کامپیوتر",100);
            ArrayList<Feed> list = searcher.searchCombine(
                    "مهندسی کامپیوتر",
                    Arrays.asList(
                            "مهندس"
                    ),
                    Arrays.asList(
                            "مشهد",
                            "تهران"
                    ),
                    "1393/09/17",
                    "1394/09/24",
                    100
            );
            for (Feed feed : list){
                System.out.println(feed.getTitle());
                System.out.println(feed.getBody());
                System.out.println(feed.getCity());
                System.out.println(feed.getDate());
                System.out.println(feed.getUrl());
                System.out.println("=========================");
            }
            /*Indexer indexer = new Indexer();
            indexer.createIndex();
            indexer.close();*/
            //CrawlController controller = BusinessCrawlController.setup();
            /*
            controller.addSeed("http://lastjob.ir/category/%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B9%D9%85%D9%88%D9%85%DB%8C/");
            controller.start(LastjobCrawler.class, 1);*/

            /*
            controller.addSeed("http://donyayekar.ir/%D9%81%D9%87%D8%B1%D8%B3%D8%AA-%D8%A2%DA%AF%D9%87%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/");
            controller.start(DonyayekarCrawler.class, 1);*/

            /*
            controller.addSeed("http://www.aftabir.com/advertising/job.php");
            controller.start(AftabirCrawler.class, 1);*/

            /*controller.addSeed("http://niazma.ir/listings/category/استخدام-و-کاریابی/");
            controller.start(NiazmaCrawler.class, 1);*/

            /*controller.addSeed("http://job.pnuna.com/");
            controller.start(PnunaCrawler.class, 1);*/

            /*controller.addSeed("http://www.baroot.com/result/index.php?page=1");
            controller.start(BarootCrawler.class, 1);*/

            /*controller.addSeed("http://estekhdam.pardad.ir/GA_13.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_4.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_2.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_3.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_18.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_17.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_22.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/GA_72.aspx");
            controller.addSeed("http://estekhdam.pardad.ir/news_group.aspx?id=22&a=1&Page=12");
            controller.start(PardadCrawler.class, 1);*/

            /*controller.addSeed("http://mazandkar.ir");
            controller.start(MazandkarCrawler.class, 1);*/

            /*controller.addSeed("http://ahwniaz.ir/category/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/");
            controller.start(AhwniazCrawler.class, 1);*/

            /*Ecity24Crawler crawler = new Ecity24Crawler();
            crawler.start("http://www.ecity24.ir/RJobs.aspx");*/

            /*JobcityCrawler crawler = new JobcityCrawler();
            crawler.start("http://www.jobcity.ir/");*/

            /*SabainfoCrawler crawler = new SabainfoCrawler();
            crawler.start("http://www.sabainfo.ir/fa/ssp/employnotic");*/

            /*controller.addSeed("http://ahwniaz.ir/category/%D8%A2%DA%AF%D9%87%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85/");
            controller.start(AhwniazCrawler.class, 1);*/
            /*SabainfoCrawler crawler = new SabainfoCrawler();
            crawler.start("http://www.sabainfo.ir/fa/ssp/employnotic");*/

            /*controller.addSeed("http://ekaar.ir/joblist.aspx");
            controller.start(EkaarCrawler.class, 1);

            /*controller.addSeed("http://www.unp.ir/news/job");
            controller.start(UnpCrawler.class, 1);*/

            /*controller.addSeed("http://goodco.ir/opportunities/alphabet");
            controller.start(GoodCoCrawler.class, 1);*/

            /*controller.addSeed("http://estekhdamia.ir/category/استخدام/");
            controller.start(EstekhdamiaCrawler.class, 1);*/

            /*controller.addSeed("http://estekhdam24.com/");
            controller.start(Estekhdam24Crawler.class, 1);*/
            
            /*controller.addSeed("http://www.golestan118.com/Hire");
            controller.start(Golestan118Crawler.class, 1);*/

            /*controller.addSeed("http://www.istgah.com/fireview/kid_318/");
            controller.start(IstgahCrawler.class, 1);*/

            /*controller.addSeed("http://www.bazarekariran.ir/employer-adver.php");
            controller.start(BazareKarIranCrawler.class, 1);*/

            /*controller.addSeed("http://www.jazbeniru.com/job_posting/reputable_company/%D8%A2%DA%AF%D9%87%DB%8C-%D9%87%D8%A7%DB%8C-%D8%A7%D8%B3%D8%AA%D8%AE%D8%AF%D8%A7%D9%85-%D8%B4%D8%B1%DA%A9%D8%AA-%D9%87%D8%A7%DB%8C-%D9%85%D8%B9%D8%AA%D8%A8%D8%B1.aspx");
            controller.start(JazbeNiruCrawler.class, 1);*/

            /*controller.addSeed("http://www.mehrjob.com/");
            controller.start(MehrjobCrawler.class, 1);*/

            /*controller.addSeed("http://www.eshetab.com/");
            controller.start(EshetabCrawler.class, 1);*/

            /*controller.addSeed("http://www.irankar.biz/");
            controller.start(IrankarCrawler.class, 1);*/
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

}
