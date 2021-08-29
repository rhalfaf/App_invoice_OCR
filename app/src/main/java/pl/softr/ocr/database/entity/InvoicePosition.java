package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import pl.softr.ocr.utils.Units;

@Entity
public class InvoicePosition {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "position_id")
    private long positionId;
    @ColumnInfo(name = "position_name")
    private String positionName;
    @ColumnInfo(name = "position_quantity")
    private Double positionQuantity;
    @ColumnInfo(name = "position_unit")
    private Units positionUnit;
    @ColumnInfo(name = "gross_price")
    private Double grossPrice;
    @ColumnInfo(name = "net_price")
    private Double netPrice;
    @ColumnInfo(name = "VAT")
    private Double VAT;

    public long getPositionId() {
        return positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public Double getPositionQuantity() {
        return positionQuantity;
    }

    public Units getPositionUnit() {
        return positionUnit;
    }

    public Double getGrossPrice() {
        return grossPrice;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public Double getVAT() {
        return VAT;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setPositionQuantity(Double positionQuantity) {
        this.positionQuantity = positionQuantity;
    }

    public void setPositionUnit(Units positionUnit) {
        this.positionUnit = positionUnit;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public void setVAT(Double VAT) {
        this.VAT = VAT;
    }
}
