package pl.softr.ocr.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import pl.softr.ocr.database.AppDatabase;
import pl.softr.ocr.database.dao.BuyerDAO;
import pl.softr.ocr.database.dao.InvoiceDAO;
import pl.softr.ocr.database.dao.InvoiceGeneralInfoDAO;
import pl.softr.ocr.database.dao.InvoicePositionDAO;
import pl.softr.ocr.database.dao.SavedBuyerDAO;
import pl.softr.ocr.database.dao.SavedSellerDAO;
import pl.softr.ocr.database.dao.SellerDAO;
import pl.softr.ocr.database.entity.Buyer;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.Invoice;
import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.SavedBuyer;
import pl.softr.ocr.database.entity.SavedSeller;
import pl.softr.ocr.database.entity.Seller;

public class Repository {

    private SellerDAO sellerDAO;
    private BuyerDAO buyerDAO;
    private InvoiceGeneralInfoDAO generalInfoDAO;
    private InvoiceDAO invoiceDAO;
    private InvoicePositionDAO invoicePositionDAO;
    private SavedSellerDAO savedSellerDAO;
    private SavedBuyerDAO savedBuyerDAO;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        sellerDAO = db.sellerDAO();
        buyerDAO = db.buyerDAO();
        generalInfoDAO = db.infoDAO();
        invoiceDAO = db.invoiceDAO();
        invoicePositionDAO = db.invoicePositionDAO();
        savedSellerDAO = db.savedSellerDAO();
        savedBuyerDAO = db.savedBuyerDAO();
    }

    public void saveInvoice(CompleteInvoice invoice) {
        AppDatabase.writeDatabaseExecutor.execute(() -> {
            Seller seller = invoice.getSeller();
            long sellerId = sellerDAO.insert(seller);
            Buyer buyer = invoice.getBuyer();
            long buyerId = buyerDAO.insert(buyer);
            InvoiceGeneralInfo generalInfo = invoice.getGeneralInfo();
            long generalInfoId = generalInfoDAO.insert(generalInfo);
            long invoiceId = invoiceDAO.addCompeteInvoice(new Invoice(generalInfoId, sellerId, buyerId));
            invoice.getPositions().forEach(position -> position.setInvoiceId(invoiceId));
            invoicePositionDAO.saveAll(invoice.getPositions());
        });
    }

    public LiveData<CompleteInvoice> getInvoiceById(long id) {
        return invoiceDAO.getById(id);
    }

    public LiveData<List<CompleteInvoice>> getAll() {
        return invoiceDAO.getAll();
    }

    public LiveData<List<CompleteInvoice>> getLastInvoices(int numberOfInvoices) {
        return invoiceDAO.getLastInvoices(numberOfInvoices);
    }

    public void addMySeller(SavedSeller mySeller) {
        AppDatabase.writeDatabaseExecutor.execute(() -> savedSellerDAO.insert(mySeller));
    }

    public Maybe<SavedSeller> findSavedSeller(SavedSeller mySeller) {
        return savedSellerDAO.findByName(mySeller.getName());
    }

    public void updateSavedSeller(SavedSeller mySeller) {
        AppDatabase.writeDatabaseExecutor.execute(()->{
            savedSellerDAO.updateSavedSeller(mySeller);
        });
    }

    public Single<SavedBuyer> findSavedBuyer(SavedBuyer actual) {
        return savedBuyerDAO.findByNIP(actual.getNIP());
    }

    public void saveMyBuyer(SavedBuyer buyer) {
        AppDatabase.writeDatabaseExecutor.execute(() -> {
            savedBuyerDAO.insert(buyer);
        });
    }

    public void updateSavedBuyer(SavedBuyer actual) {
        AppDatabase.writeDatabaseExecutor.execute(()->{
            savedBuyerDAO.updateBuyer(actual);
        });
    }

    public LiveData<List<SavedBuyer>> getAllSavedBuyers() {
       return savedBuyerDAO.getAll();
    }
}
