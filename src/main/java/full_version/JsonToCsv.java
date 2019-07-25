package full_version;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonToCsv {

    private static final String filePath = "src\\main\\resources\\statuses.json";

    public static void main(String args[]) {

        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray records = (JSONArray) jsonObject.get("records");

            List<Record> rec = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            int j = 0;
            Iterator i = records.iterator();

            while (i.hasNext()) {
                j++;
                JSONObject innerObj = (JSONObject) i.next();

                Record r = new Record(
                        Integer.parseInt(innerObj.get("kontakt_id").toString()),
                        Integer.parseInt(innerObj.get("klient_id").toString()),
                        Integer.parseInt(innerObj.get("pracownik_id").toString()),
                        innerObj.get("status").toString(),
                        dateFormat.parse(innerObj.get("kontakt_ts").toString()));
                rec.add(r);
            }

            ComparatorDate kompData = new ComparatorDate();
            rec.sort(kompData);

            ComparatorKlient kompKlient = new ComparatorKlient();
            rec.sort(kompKlient);

            fileWriter = new FileWriter("csv_output.csv");
            csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
            csvPrinter.printRecord("kontakt_id | klient_id | pracownik_id | status | kontakt_ts");
            Date date_filtr = dateFormat.parse("2017-07-01 00:00:00");

            j = 0;
            i = rec.iterator();
            while (i.hasNext()) {
                if (rec.get(j).getKontakt_ts().after(date_filtr)) {
                    csvPrinter.printRecord(rec.get(j).getKontakt_id() + " | "
                            + rec.get(j).getKlient_id()
                            + " | " + rec.get(j).getPracownik_id()
                            + " | " + rec.get(j).getStatus()
                            + " | " + rec.get(j).getKontakt_ts());
                }
                j++;
            }
            System.out.println(rec);
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
