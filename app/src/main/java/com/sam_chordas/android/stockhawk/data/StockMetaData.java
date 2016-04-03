package com.sam_chordas.android.stockhawk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saurabh on 4/3/2016.
 */
public class StockMetaData {
    String uri;
    String ticker;
    @SerializedName("Company-Name")
    String CompanyName;
    @SerializedName("Exchange-Name")
    String ExchangeName;
    String unit;
    String timezone;
    String currency;
    String gmtoffset;
    String previous_close;

    public String getUri() {
        return uri;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getExchangeName() {
        return ExchangeName;
    }

    public String getUnit() {
        return unit;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public String getGmtoffset() {
        return gmtoffset;
    }

    public String getPrevious_close() {
        return previous_close;
    }

    public StockMetaData(String uri, String ticker, String companyName, String exchangeName, String unit, String timezone, String currency, String gmtoffset, String previous_close) {

        this.uri = uri;
        this.ticker = ticker;
        CompanyName = companyName;
        ExchangeName = exchangeName;
        this.unit = unit;
        this.timezone = timezone;
        this.currency = currency;
        this.gmtoffset = gmtoffset;
        this.previous_close = previous_close;
    }

    @Override
    public String toString() {
        return "StockMetaData{" +
                "uri='" + uri + '\'' +
                ", ticker='" + ticker + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", ExchangeName='" + ExchangeName + '\'' +
                ", unit='" + unit + '\'' +
                ", timezone='" + timezone + '\'' +
                ", currency='" + currency + '\'' +
                ", gmtoffset='" + gmtoffset + '\'' +
                ", previous_close='" + previous_close + '\'' +
                '}';
    }
}
