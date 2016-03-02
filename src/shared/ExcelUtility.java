package shared;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */
public class ExcelUtility {

    private static final int INDEX_TITLE = 0;
    private static final int INDEX_BODY = 1;
    private static final int INDEX_CITY = 2;
    private static final int INDEX_URL = 3;
    private static final int INDEX_DATE = 4;

    /**
     * write fetched data to appropriate sheet
     * @param feeds the fetch data
     * @param sheetNumber sheetNumber
     */
    public static void writeToExcel(ArrayList<Feed> feeds, int sheetNumber){
        try {
            FileInputStream inputStream = new FileInputStream(Params.PATH_EXCEL);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            int curIndex = sheet.getPhysicalNumberOfRows(); // write at this index, because index begin from zero

            for(Feed feed : feeds) {

                Row row = sheet.createRow(curIndex++); // create new row
                Cell cell; // create new cells for this row

                cell = row.createCell(INDEX_TITLE); // create title cell
                cell.setCellType(Cell.CELL_TYPE_STRING); // set type of cell
                cell.setCellValue(feed.getTitle());

                cell = row.createCell(INDEX_BODY); // create body cell
                cell.setCellType(Cell.CELL_TYPE_STRING); // set type of cell
                cell.setCellValue(feed.getBody());

                cell = row.createCell(INDEX_CITY); // create city cell
                cell.setCellType(Cell.CELL_TYPE_STRING); // set type of cell
                cell.setCellValue(feed.getCity());

                cell = row.createCell(INDEX_URL); // create url cell
                cell.setCellType(Cell.CELL_TYPE_STRING); // set type of cell
                cell.setCellValue(URLDecoder.decode(feed.getUrl(), "UTF8"));

                cell = row.createCell(INDEX_DATE); // create date cell
                cell.setCellType(Cell.CELL_TYPE_STRING); // set type of cell
                cell.setCellValue(feed.getDate());

            }

            // write results into excel file
            FileOutputStream outputStream = new FileOutputStream(Params.PATH_EXCEL);
            workbook.write(outputStream);
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all Feeds within given sheetNumber
     * @param sheetNumber the sheetNumber that want to extract information from it
     * @return ArrayList
     */
    public static ArrayList<Feed> readFromExcel(int sheetNumber){
        final ArrayList<Feed> feeds = new ArrayList<Feed>();
        try {
            FileInputStream inputStream = new FileInputStream(Params.PATH_EXCEL);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

            for (Row row : sheet) {
                final Feed feed = new Feed(
                        row.getCell(INDEX_TITLE).getStringCellValue(),
                        row.getCell(INDEX_BODY).getStringCellValue(),
                        row.getCell(INDEX_CITY).getStringCellValue(),
                        row.getCell(INDEX_URL).getStringCellValue(),
                        row.getCell(INDEX_DATE).getStringCellValue()
                );

                feeds.add(feed);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return feeds;
    }

}
