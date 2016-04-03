package com.sam_chordas.android.stockhawk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sam_chordas.android.stockhawk.data.StockData;
import com.sam_chordas.android.stockhawk.data.StockDayData;
import com.sam_chordas.android.stockhawk.data.StockMinMax;
import com.sam_chordas.android.stockhawk.rest.StockService;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryActivity extends AppCompatActivity {
    public static final String KEY_TICKER = "ticker";
    public static final String TAG = HistoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_TICKER));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        final ValueLineChart lineChart = (ValueLineChart) findViewById(R.id.linechart);
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor myInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed(chain.request());
                //Modifying response  because of  Invalid JSON.
                String s = response.body().string();
                s = s.substring(s.indexOf("{"), s.length() - 1);
                return response.newBuilder().body(ResponseBody.create(response.body().contentType(), s)).build();
            }
        };
        builder.addInterceptor(myInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        StockService stockService = retrofit.create(StockService.class);
        Call<StockDayData> call = stockService.getStockHistoryDay(getIntent().getStringExtra(KEY_TICKER));
        call.enqueue(new Callback<StockDayData>() {
            @Override
            public void onResponse(Call<StockDayData> call, Response<StockDayData> response) {
                if (response != null) {
                    StockDayData stockDayData = response.body();
                    if (getSupportActionBar() != null)
                        getSupportActionBar().setTitle(stockDayData.getMeta().getCompanyName());
                    ValueLineSeries lineSeries = new ValueLineSeries();
                    lineSeries.setColor(getResources().getColor(R.color.material_green_500));
                    int i = 0;
                    for (StockData stockData : stockDayData.getSeries()) {
                        if (i % 10 == 0) {
//                            Float current = Float.valueOf(stockData.getOpen());
//                                lineSet.addPoint("", current);
                            lineSeries.addPoint(new ValueLinePoint("",Float.valueOf(stockData.getClose())));
                        }
                        i++;
                    }
                    lineChart.addSeries(lineSeries);
                    lineChart.startAnimation();
                }
            }

            @Override
            public void onFailure(Call<StockDayData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
