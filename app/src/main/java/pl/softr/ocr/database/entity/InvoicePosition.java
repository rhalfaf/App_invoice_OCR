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
    private Integer VAT;
    @ColumnInfo(name = "invoice_id")
    private long invoiceId;

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

    public Integer getVAT() {
        return VAT;
    }

    public long getInvoiceId() {
        return invoiceId;
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

    public void setVAT(Integer VAT) {
        this.VAT = VAT;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
