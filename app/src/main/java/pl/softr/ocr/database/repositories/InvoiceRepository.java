package pl.softr.ocr.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import pl.softr.ocr.database.AppDatabase;
import pl.softr.ocr.database.dao.BuyerDAO;
import pl.softr.ocr.database.dao.InvoiceDAO;
import pl.softr.ocr.database.dao.InvoiceGeneralInfoDAO;
import pl.softr.ocr.database.dao.InvoicePositionDAO;
import pl.softr.ocr.database.dao.SellerDAO;
import pl.softr.ocr.database.entity.Buyer;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.Invoice;
import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.Seller;

public class InvoiceRepository {

    private SellerDAO sellerDAO;
    private BuyerDAO buyerDAO;
    private InvoiceGeneralInfoDAO generalInfoDAO;
    private InvoiceDAO invoiceDAO;
    private InvoicePositionDAO invoicePositionDAO;

    public InvoiceRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        sellerDAO = db.sellerDAO();
        buyerDAO = db.buyerDAO();
        generalInfoDAO = db.infoDAO();
        invoiceDAO = db.invoiceDAO();
        invoicePositionDAO = db.invoicePositionDAO();
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
}
