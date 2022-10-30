package tp2.ensa.uit.ma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import tp2.ensa.uit.ma.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static final String SHARED_PREFS = "sharedPrefs";
    private String username;
    private String password;
    private boolean remmeberMe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        clearData();
        loadData();
        if(remmeberMe){
            Intent intent = new Intent(LoginActivity.this, TransactionsActivity.class);
            startActivity(intent);

        }
        else {
            setContentView(view);
        }
    }

    public void logIn(View v) {
        if (binding.username.getText().toString().equals("othmanewafae") && binding.password.getText().toString().equals("12345678")) {
            if (binding.checkRememberMe.isChecked())
            {
                saveData();
            }
            Intent intent = new Intent(LoginActivity.this, TransactionsActivity.class);
            startActivity(intent);
        } else if (binding.username.getText().equals("") || binding.password.getText().equals("")) {
            Toast.makeText(LoginActivity.this, "Username and Password are required!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "Username or Password are wrong!", Toast.LENGTH_LONG).show();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", binding.username.getText().toString());
        editor.putString("password", binding.password.getText().toString());
        editor.putBoolean("remmeberMe", binding.checkRememberMe.isChecked());
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
        remmeberMe = sharedPreferences.getBoolean("remmeberMe",false);
    }

    public void clearData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}