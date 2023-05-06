package az.turbo.main;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {

    public boolean export(List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/file.csv"))) {
            writer.writeAll(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write into csv file");
        }

        return true;
    }

}
