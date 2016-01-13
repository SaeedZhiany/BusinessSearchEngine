package indexing;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import shared.Feed;
import shared.Params;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SAEED on 2016-01-13
 * for project BusinessSearchEngine .
 */
public class Searcher {

    private IndexSearcher indexSearcher;
    private Searcher searcher;

    private Searcher() throws IOException {
        IndexReader rdr = DirectoryReader.open(FSDirectory.open(Paths.get(Params.PATH_INDEX)));
        indexSearcher = new IndexSearcher(rdr);
    }

    public Searcher getInnstance() throws IOException {
        if (searcher == null)
            searcher = new Searcher();
        return searcher;
    }

    public ArrayList<Feed> searchTitle(String title, int resultCount)
            throws IOException, ParseException, java.text.ParseException {
        ArrayList<Feed> results = new ArrayList<Feed>();

        QueryParser parser = new QueryParser(Params.FIELD_TITLE, new StandardAnalyzer());
        Query query = parser.parse(title);

        TopDocs hits = indexSearcher.search(query, resultCount);

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Feed feed = new Feed();
            feed.setTitle(doc.get(Params.FIELD_TITLE));
            feed.setBody(doc.get(Params.FIELD_BODY));
            feed.setCity(doc.get(Params.FIELD_CITY));
            feed.setDate(doc.get(Params.FIELD_DATE), Params.DATE_FORMAT_YYYY_MM_DD);
            feed.setUrl(doc.get(Params.FIELD_URL));

            results.add(feed);
        }

        return results;
    }

    public ArrayList<Feed> searchBody(List<String> bodies, int resultCount)
            throws IOException, ParseException, java.text.ParseException {
        ArrayList<Feed> results = new ArrayList<Feed>();

        StringBuilder queryBuilder = new StringBuilder("");
        for (String word : bodies)
            queryBuilder.append(word).append(" OR ");
        queryBuilder.delete(queryBuilder.lastIndexOf(" OR "), queryBuilder.length() - 1); // delete last OR

        QueryParser parser = new QueryParser(Params.FIELD_BODY, new StandardAnalyzer());
        Query query = parser.parse(queryBuilder.toString());

        TopDocs hits = indexSearcher.search(query, resultCount);

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Feed feed = new Feed();
            feed.setTitle(doc.get(Params.FIELD_TITLE));
            feed.setBody(doc.get(Params.FIELD_BODY));
            feed.setCity(doc.get(Params.FIELD_CITY));
            feed.setDate(doc.get(Params.FIELD_DATE), Params.DATE_FORMAT_YYYY_MM_DD);
            feed.setUrl(doc.get(Params.FIELD_URL));

            results.add(feed);
        }

        return results;
    }

    public ArrayList<Feed> searchCity(List<String> cities, int resultCount)
            throws IOException, ParseException, java.text.ParseException {
        ArrayList<Feed> results = new ArrayList<Feed>();

        StringBuilder queryBuilder = new StringBuilder("");
        for (String word : cities)
            queryBuilder.append(word).append(" OR ");
        queryBuilder.delete(queryBuilder.lastIndexOf(" OR "),queryBuilder.length()-1); // delete last OR

        QueryParser parser = new QueryParser(Params.FIELD_CITY, new StandardAnalyzer());
        Query query = parser.parse(queryBuilder.toString());

        TopDocs hits = indexSearcher.search(query, resultCount);

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Feed feed = new Feed();
            feed.setTitle(doc.get(Params.FIELD_TITLE));
            feed.setBody(doc.get(Params.FIELD_BODY));
            feed.setCity(doc.get(Params.FIELD_CITY));
            feed.setDate(doc.get(Params.FIELD_DATE), Params.DATE_FORMAT_YYYY_MM_DD);
            feed.setUrl(doc.get(Params.FIELD_URL));

            results.add(feed);
        }

        return results;
    }

    public ArrayList<Feed> searchDate(String fromDate,String toDate, int resultCount)
            throws IOException, ParseException, java.text.ParseException {
        ArrayList<Feed> results = new ArrayList<Feed>();

        String queryString = Params.FIELD_DATE + ":[" + fromDate + " TO " + toDate + "]";

        QueryParser parser = new QueryParser(Params.FIELD_DATE, new StandardAnalyzer());
        Query query = parser.parse(queryString);

        TopDocs hits = indexSearcher.search(query, resultCount);

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            Feed feed = new Feed();
            feed.setTitle(doc.get(Params.FIELD_TITLE));
            feed.setBody(doc.get(Params.FIELD_BODY));
            feed.setCity(doc.get(Params.FIELD_CITY));
            feed.setDate(doc.get(Params.FIELD_DATE), Params.DATE_FORMAT_YYYY_MM_DD);
            feed.setUrl(doc.get(Params.FIELD_URL));

            results.add(feed);
        }

        return results;
    }

    public static void main(String[] arg){

    }
}
