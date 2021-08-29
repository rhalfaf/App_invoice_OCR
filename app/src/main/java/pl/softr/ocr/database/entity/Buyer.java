package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Buyer {
    @PrimaryKey(autoGenerate = true)
    public long buyerId;
    @ColumnInfo(name = "buyer_name")
    public String name;
    @ColumnInfo(name = "NIP")
    public String NIP;
    @ColumnInfo(name = "address")
    public String address;
    @ColumnInfo(name = "postal_code")
    public String postal_code;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "corresponding_address")
    public String corresponding_address;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "phone_number")
    public String phone_number;
    @ColumnInfo(name = "FAX")
    public String fax;
    @ColumnInfo(name = "additional_description")
    public String additional_description;

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
}
