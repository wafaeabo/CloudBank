package tp2.ensa.uit.ma.Repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tp2.ensa.uit.ma.Daos.ITransactionDao;
import tp2.ensa.uit.ma.Data.DbContext;
import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.R;
import tp2.ensa.uit.ma.Services.ITransactionService;
import tp2.ensa.uit.ma.Utils.HttpClient;

public class TransactionRepository implements ITransactionRepository {
    private ITransactionDao transactionDao;
    private LiveData<List<Transaction>> transactions;
    private ITransactionService service;

    public TransactionRepository(Application application) {
        DbContext dbContext = DbContext.getInstance(application);
        transactionDao = dbContext.transactionDao();
        Retrofit httpClient = HttpClient.getInstance();
        service = httpClient.create(ITransactionService.class);

        transactions = new MutableLiveData<>();
        Call<List<Transaction>> call = service.getAll();
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.code() != 200) {
                    Log.d("CONSOLE LOG", "status code is " + response.code());
                    Toast.makeText(application, "Check your internet connection!", Toast.LENGTH_LONG);
                } else {
                    List<Transaction> tl = new ArrayList<>();
                    for (Transaction t : response.body()) {
                        tl.add( new Transaction(
                                t.getId(),
                                t.getType(),
                                t.getPrice(),
                                t.getDate(),
                                R.drawable.icons8_call_50,
                                t.getReference(),
                                t.getDescription()
                        ) );
                    }
                    Log.d("CONSOLE LOG", tl.toString());
                    ((MutableLiveData<List<Transaction>>) transactions).setValue(tl);
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                transactions = transactionDao.getAll();
            }
        });
    }

    @Override
    public LiveData<List<Transaction>> fetchAll() {
        return transactions;
    }
}
