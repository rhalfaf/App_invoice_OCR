package pl.softr.ocr.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.Invoice;

@Dao
public interface InvoiceDAO {

    @Transaction
    @Query("select * from invoice where id=:id")
    LiveData<CompleteInvoice> getById(long id);

    @Transaction
    @Query("select * from invoice")
    LiveData<List<CompleteInvoice>> getAll();

    @Transaction
    @Insert
    long addCompeteInvoice(Invoice invoice);

    @Query("select * from invoice order by id desc limit :numberOfInvoices")
    LiveData<List<CompleteInvoice>> getLastInvoices(int numberOfInvoices);
}
