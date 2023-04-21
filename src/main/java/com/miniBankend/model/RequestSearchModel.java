package com.miniBankend.model;

import lombok.Data;

@Data
public class RequestSearchModel {
    private String id;
    private String name;
    private int min_price;
    private int max_price;
    private String start_date ;
    private String end_date;
}
