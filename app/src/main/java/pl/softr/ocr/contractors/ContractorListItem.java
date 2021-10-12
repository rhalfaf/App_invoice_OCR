package pl.softr.ocr.contractors;

public class ContractorListItem {
    private String buyerName;
    private String buyerAddress;
    private String buyerNIP;

    public ContractorListItem(String buyerName, String buyerAddress, String buyerNIP) {
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
        this.buyerNIP = buyerNIP;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerNIP() {
        return buyerNIP;
    }

    public void setBuyerNIP(String buyerNIP) {
        this.buyerNIP = buyerNIP;
    }
}
