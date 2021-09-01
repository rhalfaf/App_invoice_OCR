package pl.softr.ocr.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pl.softr.ocr.database.dao.BuyerDAO;
import pl.softr.ocr.database.dao.InvoiceDAO;
import pl.softr.ocr.database.dao.InvoiceGeneralInfoDAO;
import pl.softr.ocr.database.dao.InvoicePositionDAO;
import pl.softr.ocr.database.dao.SellerDAO;
import pl.softr.ocr.database.entity.Buyer;
import pl.softr.ocr.database.entity.Invoice;
import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.database.entity.Seller;

@Database(entities = {Seller.class, Buyer.class, InvoiceGeneralInfo.class, Invoice.class, InvoicePosition.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SellerDAO sellerDAO();

    public abstract BuyerDAO buyerDAO();

    public abstract InvoiceGeneralInfoDAO infoDAO();

    public abstract InvoiceDAO invoiceDAO();

    public abstract InvoicePositionDAO invoicePositionDAO();

    public static volatile AppDatabase INSTANCE;
    private static final int THREADS_NUMBER = 4;
    public static final ExecutorService writeDatabaseExecutor = Executors.newFixedThreadPool(THREADS_NUMBER);

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "App-database").build();
        }
        return INSTANCE;
    }

}
