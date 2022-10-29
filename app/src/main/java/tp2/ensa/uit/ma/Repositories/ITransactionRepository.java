package tp2.ensa.uit.ma.Repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import tp2.ensa.uit.ma.Daos.ITransactionDao;
import tp2.ensa.uit.ma.Entities.Transaction;

public interface ITransactionRepository {
    LiveData<List<Transaction>> fetchAll();
}
