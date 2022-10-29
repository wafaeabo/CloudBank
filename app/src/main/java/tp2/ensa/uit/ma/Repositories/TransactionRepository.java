package tp2.ensa.uit.ma.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import tp2.ensa.uit.ma.Daos.ITransactionDao;
import tp2.ensa.uit.ma.Data.DbContext;
import tp2.ensa.uit.ma.Entities.Transaction;

public class TransactionRepository implements ITransactionRepository {
    private ITransactionDao transactionDao;
    private LiveData<List<Transaction>> transactions;

    public TransactionRepository(Application application) {
        DbContext dbContext = DbContext.getInstance(application);
        transactionDao = dbContext.transactionDao();
        transactions = transactionDao.getAll();
    }

    @Override
    public LiveData<List<Transaction>> fetchAll() {
        return transactions;
    }
}
