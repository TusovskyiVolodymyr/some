package utils;

import com.opencsv.CSVReader;
import exceptions.NoSuchUserParametrException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvUtils {
      private static Map<String, String> getDataFromCsv(String path) {
        try (CSVReader reader = new CSVReader(new FileReader("users.csv"), ',')) {
            Map<String, String> map = new HashMap<>();
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                map.put(record[0], record[1]);
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getParam(String param) {
        Map<String, String> map = getDataFromCsv("");
        if (map != null && map.containsKey(param)) {
            return map.get(param);
        } else throw new NoSuchUserParametrException("No such param in .csv file " + param);
    }
}
