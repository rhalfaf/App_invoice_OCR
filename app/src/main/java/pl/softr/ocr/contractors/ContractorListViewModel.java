package pl.softr.ocr.contractors;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pl.softr.ocr.database.entity.SavedBuyer;
import pl.softr.ocr.database.repositories.Repository;

public class ContractorListViewModel extends AndroidViewModel {

    private Repository repository = new Repository(getApplication());

    public ContractorListViewModel(@NonNull Application application) {
        super(application);
    }

    private LiveData<List<SavedBuyer>> savedBuyers;

    public LiveData<List<SavedBuyer>> getAllSavedBuyers() {
        if(savedBuyers == null){
            savedBuyers = new MutableLiveData<>();
            loadAllSavedBuyers();
        }
        return savedBuyers;
    }

    private void loadAllSavedBuyers() {
        savedBuyers = repository.getAllSavedBuyers();
    }
}
