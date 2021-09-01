package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import pl.softr.ocr.database.entity.InvoiceGeneralInfo;

@Dao
public interface InvoiceGeneralInfoDAO {

    @Insert
    long insert(InvoiceGeneralInfo info);

    @Query("select * from invoicegeneralinfo where generalInfoId=:id")
    InvoiceGeneralInfo getInfoById(long id);
}
