package pl.softr.ocr.desktop;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.repositories.InvoiceRepository;

public class MainFragmentViewModel extends AndroidViewModel {

    private InvoiceRepository repository;

    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new InvoiceRepository(getApplication());
    }

    public LiveData<List<CompleteInvoice>> getAllInvoices() {
        return repository.getAll();
    }

    public LiveData<List<CompleteInvoice>> getLastInvoices(int numberOfInvoices) {
        return repository.getLastInvoices(numberOfInvoices);
    }
}
