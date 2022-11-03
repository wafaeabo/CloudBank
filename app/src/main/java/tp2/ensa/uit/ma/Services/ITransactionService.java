package tp2.ensa.uit.ma.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tp2.ensa.uit.ma.Entities.Transaction;

public interface ITransactionService {
    @GET("api/v1/transaction")
    Call<List<Transaction>> getAll();
}
