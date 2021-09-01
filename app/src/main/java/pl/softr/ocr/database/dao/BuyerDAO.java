package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pl.softr.ocr.database.entity.Buyer;

@Dao
public interface BuyerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Buyer buyer);

    @Query("select * from Buyer where buyerId=:id")
    Buyer getBuyerById(long id);

    @Delete
    void deleteBuyer(Buyer buyer);

    @Query("select * from buyer")
    List<Buyer> getAll();


}
