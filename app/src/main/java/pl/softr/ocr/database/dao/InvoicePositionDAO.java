package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.InvoicePosition;

@Dao
public interface InvoicePositionDAO {

    @Insert
    void addInvoicePosition(InvoicePosition position);

    @Query("select * from invoiceposition where position_id=:id")
    InvoicePosition getPositionById(long id);
}
