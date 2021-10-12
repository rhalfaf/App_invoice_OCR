package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import pl.softr.ocr.database.entity.SavedBuyer;
import pl.softr.ocr.database.entity.SavedSeller;

@Dao
public interface SavedSellerDAO {
    @Query("select * from SavedSeller")
    List<SavedSeller> getAll();

    @Query("select * from SavedSeller where my_seller_name=:name")
    Maybe<SavedSeller> findByName(String name);

    @Query("select * from SavedSeller where mySellerId=:id")
    SavedSeller findById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SavedSeller mySeller);

    @Delete
    void deleteMySeller(SavedSeller savedSeller);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSavedSeller(SavedSeller mySeller);

}
