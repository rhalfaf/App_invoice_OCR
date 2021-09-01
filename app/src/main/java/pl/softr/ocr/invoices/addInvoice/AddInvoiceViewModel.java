package pl.softr.ocr.invoices.addInvoice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.database.repositories.InvoiceRepository;

public class AddInvoiceViewModel extends AndroidViewModel {

    private InvoiceRepository repository;
    private List<InvoicePosition> positions = new ArrayList<>();
    private MutableLiveData<List<InvoicePosition>> positionsLiveData;

    public AddInvoiceViewModel(@NonNull Application application) {
        super(application);
        this.repository = new InvoiceRepository(getApplication());
    }

    public void saveInvoice(CompleteInvoice completeInvoice){
        repository.saveInvoice(completeInvoice);
    }

    public int addInvoicePosition(InvoicePosition position){
        positions.add(position);
        positionsLiveData.postValue(positions);
        return positions.indexOf(position);
    }

    public LiveData<List<InvoicePosition>> getInvoicePositions(){
        if(positionsLiveData == null){
            positionsLiveData = new MutableLiveData<>(positions);
        }
        return positionsLiveData;
    }

    public LiveData<CompleteInvoice> getInvoiceById(long id) {
        return repository.getInvoiceById(id);
    }
}
