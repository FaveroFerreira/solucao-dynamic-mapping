package org.faveroferreira.dynamiccsvreader.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteActivity {

    private String addressName;
    private String addressNumber;
    private String addressComplement;
    private String cep;
    private String city;
    private String district;
    private String latitude;
    private String longitude;
    private String mcc;
    private String cnae;
    private String nameContact;
    private String presumedRenevue;
    private String responsibleName;
    private String segment;
    private String phone;
    private String trademark;
    private String portfolioCode;
    private String routerNumber;
    private String routerActivityNumber;
    private String cnpj;
    private String personType;
    private String customerOrigin;
    private String clientSituation;
    private String alertType;
    private String isPagsClient;
    private String biggestRevenue;
    private String biggestRevenueDate;
    private String presumedMonthlyTpv;

}
