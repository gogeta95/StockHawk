package com.sam_chordas.android.stockhawk.rest;

import com.sam_chordas.android.stockhawk.data.StockDayData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Saurabh on 4/3/2016.
 */
public interface StockService {
    @GET("/instrument/1.0/{ticker}/chartdata;type=quote;range=1d/json")
    Call<StockDayData> getStockHistoryDay(@Path("ticker") String ticker);
}
