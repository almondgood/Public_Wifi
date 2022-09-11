package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class History {

    private int id;
    private double lat;
    private double lnt;
    private String historyDateTime;

    private History(double lat, double lnt, String historyDateTime) {
        this.lat = lat;
        this.lnt = lnt;
        this.historyDateTime = historyDateTime;
    }

    public static History of(double lat, double lnt, String historyDateTime) {
        return new History(lat, lnt, historyDateTime);
    }
}
