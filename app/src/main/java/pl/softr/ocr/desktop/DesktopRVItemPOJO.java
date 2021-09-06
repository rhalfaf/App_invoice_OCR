package pl.softr.ocr.desktop;

public class DesktopRVItemPOJO {
    private String invoiceSymbol;
    private String buyerName;
    private Double grossPrice;

    public DesktopRVItemPOJO(String invoiceSymbol, String buyerName, Double grossPrice) {
        this.invoiceSymbol = invoiceSymbol;
        this.buyerName = buyerName;
        this.grossPrice = grossPrice;
    }

    public String getInvoiceSymbol() {
        return invoiceSymbol;
    }

    public void setInvoiceSymbol(String invoiceSymbol) {
        this.invoiceSymbol = invoiceSymbol;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }
}
