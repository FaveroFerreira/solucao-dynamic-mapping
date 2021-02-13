package org.faveroferreira.dynamiccsvreader.util;

import org.faveroferreira.dynamiccsvreader.config.CsvConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class Reader {

    @Autowired
    private CsvConfig csvConfig;

    public void readAndGenerate() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("/mnt/cloud/workspace/dynamic-csv-reader/src/main/resources/BASE_LEADS_POLO_20210205_V6.csv"), StandardCharsets.UTF_8))) {

            long headerIndex = 1;
            String line;
            while ((line = in.readLine()) != null) {
                if (headerIndex == 1) {
                    headerIndex++;
                    continue;
                }

                String[] values = line.split(csvConfig.getRegex(), -1);
                Map<String, Object> generate = generate(values);

                if (headerIndex == 2) {
                    System.out.println(generate);
                    headerIndex++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> generate(String[] values) {
        Map<String, Object> obj = new HashMap<>();
        csvConfig.getPropertyOrder().forEach((property, order) -> obj.put(property, values[order]));

        return obj;
    }

    @PostConstruct
    public void changeOrder() throws IOException {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("/mnt/cloud/workspace/dynamic-csv-reader/src/main/resources/routes.csv"), StandardCharsets.UTF_8))) {

            File file = File.createTempFile("teste", ".csv");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            int lineNumber = 1;

            String line;
            while ((line = in.readLine()) != null) {
                // 0,     1      ,     2      ,             3               ,    4     ,5,6,7,      8       ,       9      ,10
                // 2,"2020-02-13","2020-02-13","salesforce-route-activities",47,1160408,47,,1,"-23,53676162","-46,64067683",2
                String[] values = line.split(csvConfig.getRegex(), -1);

                String newLine = String.join(",",
                        values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[11],values[9], values[10]);

                bw.write(newLine);
                bw.newLine();

                // 2,"2020-02-13","2020-02-13","salesforce-route-activities",47,1160408,47,,1,2,"-23,53676162","-46,64067683"
            }

            System.out.println(file.getAbsolutePath());
        }
    }

}
