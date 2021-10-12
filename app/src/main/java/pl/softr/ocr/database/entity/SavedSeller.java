package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavedSeller {
    @PrimaryKey(autoGenerate = true)
    private
    long mySellerId;
    @ColumnInfo(name = "my_seller_name")
    private
    String name;
    @ColumnInfo(name = "NIP")
    private
    String NIP;
    @ColumnInfo(name = "address")
    private
    String address;
    @ColumnInfo(name = "postal_code")
    private
    String postalCode;
    @ColumnInfo(name = "city")
    private
    String city;
    @ColumnInfo(name = "bank_account")
    private
    String bankAccount;
    @ColumnInfo(name = "bank_name")
    private
    String bankName;
    @ColumnInfo(name = "country")
    private
    String country;
    @ColumnInfo(name = "email")
    private
    String email;
    @ColumnInfo(name = "phone_number")
    private
    String phoneNumber;
    @ColumnInfo(name = "FAX")
    private
    String fax;

    public SavedSeller(String name, String NIP, String address, String postalCode, String city, String bankAccount, String bankName, String country, String email, String phoneNumber, String fax) {
        this.name = name;
        this.NIP = NIP;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
    }

    public void setMySellerId(long mySellerId) {
        this.mySellerId = mySellerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public long getMySellerId() {
        return mySellerId;
    }

    public String getName() {
        return name;
    }

    public String getNIP() {
        return NIP;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public static SavedSeller mapToMySeller(Seller seller){
        return new SavedSeller(
                seller.getName(),
                seller.getNIP(),
                seller.getAddress(),
                seller.getPostalCode(),
                seller.getCity(),
                seller.getBankAccount(),
                seller.getBankName(),
                seller.getCountry(),
                seller.getEmail(),
                seller.getPhoneNumber(),
                seller.getFax());
    }
}
