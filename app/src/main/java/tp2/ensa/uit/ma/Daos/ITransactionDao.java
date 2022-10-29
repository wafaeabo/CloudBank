package tp2.ensa.uit.ma.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tp2.ensa.uit.ma.Entities.Transaction;

@Dao
public interface ITransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<Transaction>> getAll();
}
