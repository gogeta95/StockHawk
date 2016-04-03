package com.sam_chordas.android.stockhawk.data;

import java.util.List;

/**
 * Created by Saurabh on 4/3/2016.
 */
public class StockDayData {
    StockMetaData meta;
    List<StockData> series;
    StockRangeData ranges;

    public StockMetaData getMeta() {
        return meta;
    }

    public List<StockData> getSeries() {
        return series;
    }

    public StockRangeData getRanges() {
        return ranges;
    }

    public StockDayData(StockMetaData meta, List<StockData> series, StockRangeData ranges) {

        this.meta = meta;
        this.series = series;
        this.ranges = ranges;
    }

    @Override
    public String toString() {
        return "StockDayData{" +
                "meta=" + meta +
                ", series=" + series +
                ", ranges=" + ranges +
                '}';
    }
}
