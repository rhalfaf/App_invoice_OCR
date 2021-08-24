package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Buyer {
    @PrimaryKey
    public long id;
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


}
