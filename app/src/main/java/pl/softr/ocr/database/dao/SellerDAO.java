package pl.softr.ocr.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import pl.softr.ocr.database.entity.Seller;

@Dao
public interface SellerDAO {
    @Query("select * from seller")
    List<Seller> getAll();

    @Query("select * from seller where id=:id")
    Seller findById(int id);

    @Query("select * from seller where seller_name = :name")
    Seller findByName(String name);

    @Query("select * from seller where city = :city")
    Seller findByCity(String city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSeller(Seller seller);

    @Delete
    void deleteSellerById(Seller seller);
}
