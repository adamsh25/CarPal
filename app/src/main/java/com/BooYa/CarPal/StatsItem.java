package com.BooYa.CarPal;

/**
 * Created by Barry.Z on 11/21/2014.
 */
public class StatsItem {

    private String statsText;
    private int statID;
    private int statsPicResourceID;

    public StatsItem(String statsText, int statID, int statsPicResourceID) {
        this.statsText = statsText;
        this.statID = statID;
        this.statsPicResourceID = statsPicResourceID;
    }

    public String getStatsText() {
        return statsText;
    }

    public void setStatsText(String statsText) {
        this.statsText = statsText;
    }

    public int getStatID() {
        return statID;
    }

    public void setStatID(int statID) {
        this.statID = statID;
    }

    public int getStatsPicResourceID() {
        return statsPicResourceID;
    }

    public void setStatsPicResourceID(int statsPicResourceID) {
        this.statsPicResourceID = statsPicResourceID;
    }
}
