package pl.softr.ocr.database.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CompleteInvoice {

    @Embedded
    public Invoice invoice;

    @Relation(
            parentColumn = "generalInfoId",
            entityColumn = "generalInfoId"
    )
    public InvoiceGeneralInfo generalInfo;

    @Relation(
            parentColumn = "sellerId",
            entityColumn = "sellerId"
    )
    public Seller seller;

    @Relation(
            parentColumn = "buyerId",
            entityColumn = "buyerId"
    )
    public Buyer buyer;

    @Relation(
            parentColumn = "id",
            entityColumn = "invoice_id"
    )
    public List<InvoicePosition> positions;

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceGeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public Seller getSeller() {
        return seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<InvoicePosition> getPositions() {
        return positions;
    }

    public CompleteInvoice() {
    }

    public CompleteInvoice(InvoiceGeneralInfo generalInfo, Seller seller, Buyer buyer, List<InvoicePosition> positions) {
        this.generalInfo = generalInfo;
        this.seller = seller;
        this.buyer = buyer;
        this.positions=positions;
    }
}
