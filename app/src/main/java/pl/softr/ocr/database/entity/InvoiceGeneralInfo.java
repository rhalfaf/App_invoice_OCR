package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class InvoiceGeneralInfo {
    @PrimaryKey(autoGenerate = true)
    public long generalInfoId;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "symbol")
    public String symbol;
    @ColumnInfo(name = "create_date")
    public String create_date;
    @ColumnInfo(name = "create_place")
    public String create_place;
    @ColumnInfo(name = "sell_date")
    public String sell_date;

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
}
