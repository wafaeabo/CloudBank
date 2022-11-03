package tp2.ensa.uit.ma.ViewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.R;
import tp2.ensa.uit.ma.Repositories.TransactionRepository;
import tp2.ensa.uit.ma.Services.ITransactionService;
import tp2.ensa.uit.ma.Utils.HttpClient;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> transactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);

        repository = new TransactionRepository(application);
        transactions = repository.fetchAll();
    }

    public LiveData<List<Transaction>> fetchAll() {
        return transactions;
    }
}
