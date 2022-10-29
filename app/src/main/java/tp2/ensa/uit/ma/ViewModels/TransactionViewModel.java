package tp2.ensa.uit.ma.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.Repositories.TransactionRepository;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> transactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        transactions = repository.fetchAll();
    }

    public LiveData<List<Transaction>> fetchAllFromSQLite() {
        return transactions;
    }
}
