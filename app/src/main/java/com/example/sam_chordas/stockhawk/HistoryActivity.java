package com.example.sam_chordas.stockhawk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.LinearEase;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.StockData;
import com.sam_chordas.android.stockhawk.data.StockDayData;
import com.sam_chordas.android.stockhawk.rest.StockService;

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
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final LineChartView lineChartView = (LineChartView) findViewById(R.id.linechart);
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
                    try{
                        StockDayData stockDayData = response.body();
                        if (getSupportActionBar() != null)
                            getSupportActionBar().setTitle(stockDayData.getMeta().getCompanyName());
                        LineSet lineSet = new LineSet();
                        lineSet.setColor(getResources().getColor(R.color.material_green_700));
                        lineSet.setThickness(2);
                        lineSet.setFill(getResources().getColor(R.color.material_green_500));
                        int i = 0;
                        float min = Float.MAX_VALUE;
                        float max = 0;
                        for (StockData stockData : stockDayData.getSeries()) {
                            if (i % 10 == 0) {
                                Float current= Float.valueOf(stockData.getOpen());
                                max=current>max?current:max;
                                min=current<min?current:min;
                                lineSet.addPoint("",current);
                            }
                            i++;
                        }
                        lineChartView.setAxisBorderValues((int) min,(int) max);
                        lineChartView.addData(lineSet);
                        lineChartView.show();
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        e.printStackTrace();
                        Toast.makeText(HistoryActivity.this,R.string.error_msg, Toast.LENGTH_SHORT).show();
                    }


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
