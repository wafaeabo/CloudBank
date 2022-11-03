package tp2.ensa.uit.ma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import tp2.ensa.uit.ma.Adapters.TransactionListAdapter;
import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.ViewModels.TransactionViewModel;

public class TransactionsActivity extends AppCompatActivity {

    ListView transactionListView;
    TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        transactionListView = findViewById(R.id.transaction_list);

        transactionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
                Transaction transaction = new Transaction();
                TextView priceView = view.findViewById(R.id.transaction_price);
                transaction.setPrice(Double.parseDouble(priceView.getText().toString()));
                TextView typeView = view.findViewById(R.id.transaction_type);
                transaction.setType(typeView.getText().toString());
                try {
                    TextView dateView = view.findViewById(R.id.transaction_date);
                    transaction.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateView.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                intent.putExtra("transaction", transaction);
                startActivity(intent);
            }
        });

        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionViewModel.fetchAll().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                TransactionListAdapter adapter = new TransactionListAdapter(getApplicationContext(), R.layout.transaction_card, transactions);
                transactionListView.setAdapter(adapter);
            }
        });
    }
}