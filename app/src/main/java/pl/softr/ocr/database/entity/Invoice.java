package pl.softr.ocr.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Invoice {
    @PrimaryKey
    public long id;
    @ColumnInfo(name = "general_info")
    public long general_info;
    @ColumnInfo(name = "seller")
    public long seller_id;
    @ColumnInfo(name = "buyer")
    public long buyer_id;
}
