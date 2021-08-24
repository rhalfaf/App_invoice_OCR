package pl.softr.ocr.invoices.addInvoice;

import android.widget.TextView;

import pl.softr.ocr.utils.Units;

public class PositionsItemModel {

    private String positionName;
    private Double positionQuantity;
    private Units unit;
    private Double priceNet;
    private Double priceGross;
    private Double vat;

    public PositionsItemModel(String positionName, Double positionQuantity, Units unit, Double priceNet, Double priceGross, Double vat) {
        this.positionName = positionName;
        this.positionQuantity = positionQuantity;
        this.unit = unit;
        this.priceNet = priceNet;
        this.priceGross = priceGross;
        this.vat = vat;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Double getPositionQuantity() {
        return positionQuantity;
    }

    public void setPositionQuantity(Double positionQuantity) {
        this.positionQuantity = positionQuantity;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public Double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(Double priceNet) {
        this.priceNet = priceNet;
    }

    public Double getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(Double priceGross) {
        this.priceGross = priceGross;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }
}
