package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Buyer {
    @PrimaryKey(autoGenerate = true)
    private long buyerId;
    @ColumnInfo(name = "buyer_name")
    private String name;
    @ColumnInfo(name = "NIP")
    private String NIP;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "postal_code")
    private String postal_code;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "corresponding_address")
    private String corresponding_address;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone_number")
    private String phone_number;
    @ColumnInfo(name = "FAX")
    private String fax;
    @ColumnInfo(name = "additional_description")
    private String additional_description;

    public Buyer() {
    }

    public Buyer(String name, String NIP, String address, String postal_code, String city, String country, String corresponding_address, String email, String phone_number, String fax, String additional_description) {
        this.name = name;
        this.NIP = NIP;
        this.address = address;
        this.postal_code = postal_code;
        this.city = city;
        this.country = country;
        this.corresponding_address = corresponding_address;
        this.email = email;
        this.phone_number = phone_number;
        this.fax = fax;
        this.additional_description = additional_description;
    }

    public Buyer(String name, String NIP, String address, String postal_code, String city) {
        this(name, NIP, address, postal_code, city, null, null, null, null, null, null);
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
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

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCorresponding_address() {
        return corresponding_address;
    }

    public void setCorresponding_address(String corresponding_address) {
        this.corresponding_address = corresponding_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAdditional_description() {
        return additional_description;
    }

    public void setAdditional_description(String additional_description) {
        this.additional_description = additional_description;
    }
}
