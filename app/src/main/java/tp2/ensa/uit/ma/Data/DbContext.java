package tp2.ensa.uit.ma.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

import tp2.ensa.uit.ma.Daos.ITransactionDao;
import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.R;

@Database(entities = {Transaction.class}, version = 1)
public abstract class DbContext extends RoomDatabase {
    public static DbContext instance;

    public abstract ITransactionDao transactionDao();

    public static DbContext getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DbContext.class, "cloud_bank")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static DbContext.Callback roomCallback = new DbContext.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ITransactionDao transactionDao;

        private PopulateDbAsyncTask(DbContext dbContext) {
            transactionDao = dbContext.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDao.insert(new Transaction("Facture Internet", 499.0, new Date(), R.drawable.icons8_call_50, "x0abc", "this is the transaction description"));
            transactionDao.insert(new Transaction("Facture Appeles", 199.0, new Date(), R.drawable.icons8_call_50, "x0abd", "this is the transaction description"));
            transactionDao.insert(new Transaction("Facture Fax", 99.0, new Date(), R.drawable.icons8_call_50, "x0abe", "this is the transaction description"));
            return null;
        }
    }
}
