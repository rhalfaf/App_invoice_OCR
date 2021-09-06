package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class InvoiceGeneralInfo {
    @PrimaryKey(autoGenerate = true)
    private long generalInfoId;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "symbol")
    private String symbol;
    @ColumnInfo(name = "create_date")
    private String create_date;
    @ColumnInfo(name = "create_place")
    private String create_place;
    @ColumnInfo(name = "sell_date")
    private String sell_date;

    public long getGeneralInfoId() {
        return generalInfoId;
    }

    public InvoiceGeneralInfo(String type, String symbol, String create_date, String create_place, String sell_date) {
        this.type = type;
        this.symbol = symbol;
        this.create_date = create_date;
        this.create_place = create_place;
        this.sell_date = sell_date;
    }

    public void setGeneralInfoId(long generalInfoId) {
        this.generalInfoId = generalInfoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_place() {
        return create_place;
    }

    public void setCreate_place(String create_place) {
        this.create_place = create_place;
    }

    public String getSell_date() {
        return sell_date;
    }

    public void setSell_date(String sell_date) {
        this.sell_date = sell_date;
    }
}
