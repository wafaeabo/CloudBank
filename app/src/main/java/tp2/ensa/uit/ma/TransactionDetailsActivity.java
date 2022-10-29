package tp2.ensa.uit.ma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.databinding.ActivityLoginBinding;
import tp2.ensa.uit.ma.databinding.ActivityTransactionBinding;

public class TransactionDetailsActivity extends AppCompatActivity {

    private ActivityTransactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        Transaction transaction = (Transaction) intent.getSerializableExtra("transaction");

        binding.tPrice.setText("Prix : " + String.valueOf(transaction.getPrice()));
        binding.tType.setText("Type : " + transaction.getType());
        binding.tDate.setText(("Date : " + (new SimpleDateFormat("dd/MM/yyyy")).format(transaction.getDate())));
    }
}