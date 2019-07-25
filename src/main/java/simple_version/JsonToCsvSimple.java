package simple_version;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonToCsvSimple {

    private static final String filePath = "src\\main\\resources\\statuses.json";
    final static String[] CSV_HEADER = {"kontakt_id", "klient_id", "pracownik_id", "status", "kontakt_ts"};

    public static void main(String[] args) {

        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray records = (JSONArray) jsonObject.get("records");

            fileWriter = new FileWriter("csv_output.csv");
            csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(CSV_HEADER));
            Iterator i = records.iterator();

            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date_filtr = inputDateFormat.parse("2017-07-01 00:00:00");
            // Date date = inputDateFormat.parse(innerObj.get("kontakt_ts"));

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();

                Date date = inputDateFormat.parse(innerObj.get("kontakt_ts").toString());
                if (date.after(date_filtr)) {
                    csvPrinter.printRecord(innerObj.get("kontakt_id") + " | " + innerObj.get("klient_id") + " | " + innerObj.get("pracownik_id") + " | " + innerObj.get("status") + " | " + innerObj.get("kontakt_ts"));
                }
            }
            // csvPrinter.println();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                System.out.println("Flushing/closing error!");
                e.printStackTrace();
            }
        }

    }

}
