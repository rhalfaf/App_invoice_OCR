package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class InvoiceGeneralInfo {
    @PrimaryKey(autoGenerate = true)
    private long generalInfoId;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "symbol")
    private String symbol;
    @ColumnInfo(name = "create_date")
    private String createDate;
    @ColumnInfo(name = "create_place")
    private String createPlace;
    @ColumnInfo(name = "sell_date")
    private String sellDate;

    public long getGeneralInfoId() {
        return generalInfoId;
    }

    public InvoiceGeneralInfo(String type, String symbol, String createDate, String createPlace, String sellDate) {
        this.type = type;
        this.symbol = symbol;
        this.createDate = createDate;
        this.createPlace = createPlace;
        this.sellDate = sellDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreatePlace() {
        return createPlace;
    }

    public void setCreatePlace(String createPlace) {
        this.createPlace = createPlace;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
}
