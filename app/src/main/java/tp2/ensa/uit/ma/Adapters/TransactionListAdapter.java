package tp2.ensa.uit.ma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tp2.ensa.uit.ma.Entities.Transaction;
import tp2.ensa.uit.ma.R;

public class TransactionListAdapter extends ArrayAdapter<Transaction> {
    private List<Transaction> transactions;

    public TransactionListAdapter(@NonNull Context context, int resource, @NonNull List<Transaction> transactions) {
        super(context, resource, transactions);
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.transaction_card, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.transaction_icon);
        imageView.setBackgroundResource(transactions.get(position).getImageId());
        TextView type = (TextView) convertView.findViewById(R.id.transaction_type);
        type.setText(transactions.get(position).getType());
        TextView price = (TextView) convertView.findViewById(R.id.transaction_price);
        price.setText(String.valueOf(transactions.get(position).getPrice()));
        TextView date = (TextView) convertView.findViewById(R.id.transaction_date);
        date.setText((new SimpleDateFormat("dd/MM/yyyy")).format(transactions.get(position).getDate()));

        return convertView;
    }

}
