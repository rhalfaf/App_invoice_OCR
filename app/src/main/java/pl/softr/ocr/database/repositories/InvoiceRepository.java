package pl.softr.ocr.database.repositories;

import android.app.Application;

import pl.softr.ocr.database.AppDatabase;
import pl.softr.ocr.database.dao.BuyerDAO;
import pl.softr.ocr.database.dao.InvoiceDAO;
import pl.softr.ocr.database.dao.InvoiceGeneralInfoDAO;
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

    public InvoiceRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        sellerDAO = db.sellerDAO();
        buyerDAO = db.buyerDAO();
        generalInfoDAO = db.infoDAO();
        invoiceDAO = db.invoiceDAO();
    }

    public void addInvoice(CompleteInvoice invoice) {
        AppDatabase.writeDatabaseExecutor.execute( () -> {
            Seller seller = invoice.getSeller();
            sellerDAO.addSeller(seller);
            Buyer buyer = invoice.getBuyer();
            buyerDAO.addBuyer(buyer);
            InvoiceGeneralInfo generalInfo = invoice.getGeneralInfo();
            generalInfoDAO.addInvoiceGeneralInfo(generalInfo);
            invoiceDAO.addCompeteInvoice(new Invoice(generalInfo.getGeneralInfoId(),seller.getSellerId(),buyer.getBuyerId()));
        });
    }
}
