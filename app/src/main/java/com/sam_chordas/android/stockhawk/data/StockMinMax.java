package com.sam_chordas.android.stockhawk.data;

/**
 * Created by Saurabh on 4/3/2016.
 */
public class StockMinMax {
    String min;
    String max;

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public StockMinMax(String min, String max) {

        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "StockMinMax{" +
                "min='" + min + '\'' +
                ", max='" + max + '\'' +
                '}';
    }
}
