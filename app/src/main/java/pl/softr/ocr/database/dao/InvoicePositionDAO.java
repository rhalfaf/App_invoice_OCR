package pl.softr.ocr.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.InvoicePosition;

@Dao
public interface InvoicePositionDAO {

    @Insert
    long addInvoicePosition(InvoicePosition position);

    @Insert
    void saveAll(List<InvoicePosition> positions);

    @Query("select * from invoiceposition where position_id=:id")
    InvoicePosition getPositionById(long id);

}
