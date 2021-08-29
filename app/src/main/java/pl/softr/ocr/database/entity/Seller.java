package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    public String postal_code;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "bank_account")
    public String bank_account;
    @ColumnInfo(name = "bank_name")
    public String bank_name;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone_number")
    public String phone_number;
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
        this.postal_code = postal_code;
        this.city = city;
        this.bank_account = bank_account;
        this.bank_name = bank_name;
        this.country = country;
        this.email = email;
        this.phone_number = phone_number;
        this.fax = fax;
    }
    public Seller(String name, String NIP, String address, String postal_code, String city, String bank_account) {
        this(name,NIP, address,postal_code,city,bank_account, null,null,null,null,null);

    }
}
