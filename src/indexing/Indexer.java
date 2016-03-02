package indexing;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import shared.ExcelUtility;
import shared.Feed;
import shared.Params;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by SAEED on 2016-01-08
 * for project BusinessSearchEngine .
 */

/**
 * create lucene Index using this class
 */
public class Indexer {
    private IndexWriter writer;

    public Indexer() throws IOException {
        //this directory will contain the indexes
        Directory indexDirectory = FSDirectory.open(Paths.get(Params.PATH_INDEX));

        //create the indexer
        IndexWriterConfig indexWriterConf = new IndexWriterConfig(new StandardAnalyzer());
        writer = new IndexWriter(indexDirectory, indexWriterConf);

    }

    public int createIndex() throws IOException{
        //get all feeds in the data directory
        for (int i=0; i < Params.SHEET_COUNT; i++) {
            ArrayList<Feed> feeds = ExcelUtility.readFromExcel(i); // get all feeds in #i th sheet

            for (Feed feed : feeds) {
                writer.addDocument(getDocument(feed));
            }
        }

        return writer.numDocs();
    }

    private Document getDocument(Feed feed) throws IOException{
        Document document = new Document();

        // index feed title
        TextField title = new TextField(Params.FIELD_TITLE, feed.getTitle(), Field.Store.YES);
        // index feed body
        TextField body = new TextField(Params.FIELD_BODY, feed.getBody(), Field.Store.YES);
        // index feed city
        TextField city = new TextField(Params.FIELD_CITY, feed.getCity(), Field.Store.YES);
        // index feed date
        StringField date = new StringField(Params.FIELD_DATE, feed.getDate(), Field.Store.YES);
        // index feed url
        StringField url = new StringField(Params.FIELD_URL, feed.getUrl(), Field.Store.YES);

        document.add(title);
        document.add(body);
        document.add(city);
        document.add(date);
        document.add(url);

        return document;
    }

    public void close() throws IOException{
        writer.close();
    }
}
