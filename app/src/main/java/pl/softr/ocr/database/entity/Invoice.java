package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity
public class Invoice {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long generalInfoId;
    public long sellerId;
    public long buyerId;
    public long invoicePositionId;

    public Invoice() {
    }

    public Invoice(long generalInfoId, long sellerId, long buyerId) {
        this.generalInfoId = generalInfoId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }
}
