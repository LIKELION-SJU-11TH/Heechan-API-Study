package com.example.demo.dto;

import lombok.Getter;

@Getter
public class BikeInfo {
    private Long id;
    private int shared;
    private int parkingBikeToCnt;
    private String stationName;
    private double stationLatitude;
    private double stationLongitude;
    private int rackTotCnt;
    private String stationId;

    public BikeInfo(Long id, int shared, int parkingBikeToCnt, String stationName, double stationLatitude, double stationLongitude, int rackTotCnt, String stationId) {
        this.id = id;
        this.shared = shared;
        this.parkingBikeToCnt = parkingBikeToCnt;
        this.stationName = stationName;
        this.stationLatitude = stationLatitude;
        this.stationLongitude = stationLongitude;
        this.rackTotCnt = rackTotCnt;
        this.stationId = stationId;
    }
}
