package com.example.quake;


public class EarthQuake {

    private String Name;
    private double magnitude;
    private long timeinmillisecond;
    private String url;


    public EarthQuake(double magnitude,String name,long time,String url){
        this.magnitude=magnitude;
        this.url=url;
        this.Name=name;
        this.timeinmillisecond=time;
    }

    public String getName() {
        return Name;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getTimeinmillisecond() {
        return timeinmillisecond;
    }

    public String getUrl() {
        return url;
    }

}
