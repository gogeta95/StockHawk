package com.sam_chordas.android.stockhawk.data;

/**
 * Created by Saurabh on 4/3/2016.
 */
public class StockRangeData {
    StockMinMax close;
    StockMinMax high;
    StockMinMax low;
    StockMinMax open;
    StockMinMax volume;

    public StockRangeData(StockMinMax close, StockMinMax high, StockMinMax low, StockMinMax open, StockMinMax volume) {

        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
        this.volume = volume;
    }

    public StockMinMax getClose() {
        return close;
    }

    public StockMinMax getHigh() {
        return high;
    }

    public StockMinMax getLow() {
        return low;
    }

    public StockMinMax getOpen() {
        return open;
    }

    public StockMinMax getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "StockRangeData{" +
                "close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", volume=" + volume +
                '}';
    }
}
