package com.sam_chordas.android.stockhawk.data;

import android.util.Log;

/**
 * Created by Saurabh on 4/3/2016.
 */
public class StockData {
    Long Timestamp;
    String close;
    String high;
    String low;
    String open;
    Long volume;

    public Long getTimestamp() {
        return Timestamp;
    }

    public String getClose() {
        return close;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getOpen() {
        return open;
    }

    public Long getVolume() {
        return volume;
    }

    public StockData(Long timestamp, String close, String high, String low, String open, Long volume) {

        Timestamp = timestamp;
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "Timestamp=" + Timestamp +
                ", close='" + close + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", open='" + open + '\'' +
                ", volume=" + volume +
                '}';
    }
}
