package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Invoice {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long generalInfoId;
    private long sellerId;
    private long buyerId;

    public Invoice() {
    }

    public Invoice(long generalInfoId, long sellerId, long buyerId) {
        this.generalInfoId = generalInfoId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGeneralInfoId() {
        return generalInfoId;
    }

    public void setGeneralInfoId(long generalInfoId) {
        this.generalInfoId = generalInfoId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }
}
