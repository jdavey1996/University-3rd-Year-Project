package com.josh_davey.university_3rd_year_project;

/**
 * Created by Josh on 30/12/2016.
 */

public class Detection {
    String detectionNum;
    String date;
    String time;

    public Detection(String detectionNum,String date,String time)
    {
        this.detectionNum=detectionNum;
        this.date=date;
        this.time=time;
    }

    public String getDate() {
        return date;
    }

    public String getDetectionNum() {
        return detectionNum;
    }

    public String getTime() {
        return time;
    }
}
