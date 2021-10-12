package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Seller {
    @PrimaryKey(autoGenerate = true)
    public long sellerId;
    @ColumnInfo(name = "seller_name")
    public String name;
    @ColumnInfo(name = "NIP")
    public String NIP;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "postal_code")
    public String postalCode;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "bank_account")
    public String bankAccount;
    @ColumnInfo(name = "bank_name")
    public String bankName;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone_number")
    public String phoneNumber;
    @ColumnInfo(name = "FAX")
    public String fax;

    public long getSellerId() {
        return sellerId;
    }

    public Seller() {
    }

    public Seller(String name, String NIP, String address, String postal_code, String city, String bank_account, String bank_name, String country, String email, String phone_number, String fax) {
        this.name = name;
        this.NIP = NIP;
        this.address = address;
        this.postalCode = postal_code;
        this.city = city;
        this.bankAccount = bank_account;
        this.bankName = bank_name;
        this.country = country;
        this.email = email;
        this.phoneNumber = phone_number;
        this.fax = fax;
    }
    public Seller(String name, String NIP, String address, String postal_code, String city, String bank_account) {
        this(name,NIP, address,postal_code,city,bank_account, null,null,null,null,null);
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


}
