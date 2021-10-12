package pl.softr.ocr.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SavedBuyer {
    @ColumnInfo(name = "buyer_name")
    private String name;
    @ColumnInfo(name = "NIP")
    @PrimaryKey
    @NonNull
    private String NIP;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "postal_code")
    private String postalCode;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "corresponding_address")
    private String correspondingAddress;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo(name = "FAX")
    private String fax;
    @ColumnInfo(name = "additional_description")
    private String additionalDescription;

    public SavedBuyer(String name, String NIP, String address, String postalCode, String city, String country, String correspondingAddress, String email, String phoneNumber, String fax, String additionalDescription) {
        this.name = name;
        this.NIP = NIP;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.correspondingAddress = correspondingAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.additionalDescription = additionalDescription;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCorrespondingAddress() {
        return correspondingAddress;
    }

    public void setCorrespondingAddress(String correspondingAddress) {
        this.correspondingAddress = correspondingAddress;
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

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public static SavedBuyer mapToSavedBuyer(Buyer readBuyer) {
        return new SavedBuyer(readBuyer.getName(),
                readBuyer.getNIP(),
                readBuyer.getAddress(),
                readBuyer.getPostalCode(),
                readBuyer.getCity(),
                readBuyer.getCountry(),
                readBuyer.getCorrespondingAddress(),
                readBuyer.getEmail(),
                readBuyer.getPhoneNumber(),
                readBuyer.getFax(),
                readBuyer.getAdditionalDescription());
    }
}
