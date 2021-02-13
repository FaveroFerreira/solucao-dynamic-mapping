package org.faveroferreira.dynamiccsvreader.util;

import org.faveroferreira.dynamiccsvreader.config.CsvConfig;
import org.faveroferreira.dynamiccsvreader.model.RouteActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Reader {

    @Autowired
    private CsvConfig csvConfig;

    @PostConstruct
    public void readAndGenerate() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("/mnt/cloud/workspace/dynamic-csv-reader/src/main/resources/BASE_LEADS_POLO_20210205_V6.csv"), StandardCharsets.UTF_8))) {

            List<RouteActivity> routeActivities = new ArrayList<>();

            long headerIndex = 1;
            String line;
            while ((line = in.readLine()) != null) {
                if (headerIndex == 1) {
                    headerIndex++;
                    continue;
                }

                String[] values = line.split(",");
                Object generate = generate(values);

                System.out.println(generate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private RouteActivity generate(String[] values) {
//        return RouteActivity.builder()
//                .addressName(values[csvConfig.getPropertyOrder().get("addressName")])
//                .addressNumber(values[csvConfig.getPropertyOrder().get("addressNumber")])
//                .addressComplement(values[csvConfig.getPropertyOrder().get("addressComplement")])
//                .cep(values[csvConfig.getPropertyOrder().get("cep")])
//                .city(values[csvConfig.getPropertyOrder().get("city")])
//                .district(values[csvConfig.getPropertyOrder().get("district")])
//                .latitude(values[csvConfig.getPropertyOrder().get("latitude")])
//                .longitude(values[csvConfig.getPropertyOrder().get("longitude")])
//                .mcc(values[csvConfig.getPropertyOrder().get("mcc")])
//                .cnae(values[csvConfig.getPropertyOrder().get("cnae")])
//                .nameContact(values[csvConfig.getPropertyOrder().get("nameContact")])
//                .presumedRenevue(values[csvConfig.getPropertyOrder().get("presumedRevenue")])
//                .responsibleName(values[csvConfig.getPropertyOrder().get("responsibleName")])
//                .segment(values[csvConfig.getPropertyOrder().get("segment")])
//                .phone(values[csvConfig.getPropertyOrder().get("phone")])
//                .trademark(values[csvConfig.getPropertyOrder().get("trademark")])
//                .portfolioCode(values[csvConfig.getPropertyOrder().get("portfolioCode")])
//                .routerNumber(values[csvConfig.getPropertyOrder().get("routeNumber")])
//                .routerActivityNumber(values[csvConfig.getPropertyOrder().get("routeActivityNumber")])
//                .cnpj(values[csvConfig.getPropertyOrder().get("cnpj")])
//                .personType(values[csvConfig.getPropertyOrder().get("personType")])
//                .customerOrigin(values[csvConfig.getPropertyOrder().get("customerOrigin")])
//                .clientSituation(values[csvConfig.getPropertyOrder().get("clientSituation")])
//                .alertType(values[csvConfig.getPropertyOrder().get("alertType")])
//                .isPagsClient(values[csvConfig.getPropertyOrder().get("isPagsClient")])
//                .biggestRevenue(values[csvConfig.getPropertyOrder().get("biggestRevenue")])
//                .biggestRevenueDate(values[csvConfig.getPropertyOrder().get("biggestRevenueDate")])
//                .build();
//    }

    private Map<String, Object> generate(String[] values) {
        Map<String, Object> obj = new HashMap<>();
        csvConfig.getPropertyOrder().forEach((property, order) -> {
            obj.put(property, values[order]);
        });

        return obj;
    }


}
