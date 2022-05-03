package com.example.MyFlight;

public class FlightInfo {
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name + "\n" + startTime + "-" + endTime + "\n" + startCity + "-" + endCity;
    }

    private String startTime;
    private String endTime;
    private String startCity;
    private String endCity;
    private String name;

    public FlightInfo(String startTime, String endTime, String startCity, String endCity, String name){
        this.startTime = startTime;
        this.endTime = endTime;
        this.startCity = startCity;
        this.endCity = endCity;
        this.name = name;
    }

}
