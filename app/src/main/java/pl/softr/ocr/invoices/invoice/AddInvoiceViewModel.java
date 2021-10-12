package pl.softr.ocr.invoices.invoice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.database.entity.SavedBuyer;
import pl.softr.ocr.database.entity.SavedSeller;
import pl.softr.ocr.database.repositories.Repository;

public class AddInvoiceViewModel extends AndroidViewModel {

    private Repository repository;
    private List<InvoicePosition> positions = new ArrayList<>();
    private MutableLiveData<List<InvoicePosition>> positionsLiveData;

    public AddInvoiceViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(getApplication());
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

    public int deleteInvoicePosition(InvoicePosition p) {
        int r = positions.indexOf(p);
        positions.remove(p);
        positionsLiveData.postValue(positions);
        return r;
    }

    public void saveMySeller(SavedSeller mySeller) {
        repository.addMySeller(mySeller);
    }

    public Maybe<SavedSeller> isSellerSaved(SavedSeller mySeller) {
        return repository.findSavedSeller(mySeller);
    }

    public void updateSavedSeller(SavedSeller mySeller) {
        repository.updateSavedSeller(mySeller);
    }

    public Single<SavedBuyer> isBuyerSaved(SavedBuyer actual) {
        return repository.findSavedBuyer(actual);
    }

    public void saveMyBuyer(SavedBuyer buyer) {
        repository.saveMyBuyer(buyer);
    }

    public void updateSavedBuyer(SavedBuyer actual) {
        repository.updateSavedBuyer(actual);
    }
}
