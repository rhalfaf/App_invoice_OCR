package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import pl.softr.ocr.database.entity.Invoice;

@Dao
public interface InvoiceDAO {
    @Query("select * from invoice where id=:id")
    Invoice getById(long id);
}
