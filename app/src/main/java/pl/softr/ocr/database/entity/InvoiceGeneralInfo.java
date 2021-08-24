package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class InvoiceGeneralInfo {
    @PrimaryKey
    public long id;
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
}
