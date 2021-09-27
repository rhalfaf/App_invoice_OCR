package pl.softr.ocr.invoices.invoice;

import pl.softr.ocr.database.entity.InvoicePosition;

public interface OnInvoicePositionDelete {
    void delete(InvoicePosition p);
}
