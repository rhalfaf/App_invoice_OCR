package pl.softr.ocr.database.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import pl.softr.ocr.database.entity.SavedBuyer;

@Dao
public interface SavedBuyerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SavedBuyer buyer);

    @Delete
    void deleteBuyer(SavedBuyer buyer);

    @Query("select * from savedbuyer")
    LiveData<List<SavedBuyer>> getAll();

    @Query("select * from SavedBuyer where NIP = :NIP")
    Single<SavedBuyer> findByNIP(String NIP);

    @Update
    void updateBuyer(SavedBuyer actual);
}
