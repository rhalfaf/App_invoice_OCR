package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.Invoice;

@Dao
public interface InvoiceDAO {

    @Transaction
    @Query("select * from invoice where id=:id")
    CompleteInvoice getById(long id);

    @Transaction
    @Query("select * from invoice")
    CompleteInvoice getAll();

    @Transaction
    @Insert
    void addCompeteInvoice(Invoice invoice);

}
