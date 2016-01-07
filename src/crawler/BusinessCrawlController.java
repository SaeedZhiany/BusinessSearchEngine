package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import shared.Params;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

/**
 * use this class for get a initialized CrawlController instance
 */
public abstract class BusinessCrawlController {

    private BusinessCrawlController(){}

    public CrawlController setup() throws Exception {

        // config the crawler
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(Params.PATH_TEMP_STORAGE);
        config.setPolitenessDelay(Params.POLITENESS_DELAY);
        config.setMaxDepthOfCrawling(Params.MAX_DEPTH_OF_CRAWLING);
        config.setMaxPagesToFetch(Params.MAX_PAGES_TO_FETCH);
        config.setIncludeBinaryContentInCrawling(false);

        // initial Prerequisites
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        // initial Controller
        return new CrawlController(config, pageFetcher, robotstxtServer);
    }

}
