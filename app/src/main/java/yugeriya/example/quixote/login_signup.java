package yugeriya.example.quixote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class login_signup extends AppCompatActivity {

    EditText name,email,phone,password;
    TextView status;
    Button change,login;
    boolean signUp=true;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Toast.makeText(this, "Login Screen", Toast.LENGTH_SHORT).show();
        change=findViewById(R.id.change);
        status=findViewById(R.id.title_log);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login_sign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidMail(email.getText().toString())) {
                    MainActivity.sharedPreferences.edit().putString("Email", email.getText().toString()).apply();
                }
                else {
                    email.setText(null);
                    email.setHint("Invalid Email");
                }
                if(isValidMobile(phone.getText().toString()))
                    MainActivity.sharedPreferences.edit().putString("Phone",phone.getText().toString()).apply();
                else {
                    phone.setText(null);
                    phone.setHint("Invalid Mobile Nuber");
                }
                if(isValidPassword(password.getText().toString()))
                    MainActivity.sharedPreferences.edit().putString("Password",password.getText().toString()).apply();
                else {
                    password.setText(null);
                    password.setHint(" min 8 and max 15 characters, should not contain your name, the first\n" +
                            "character should be lowercase, must contain at least 2 uppercase characters, 2 digits and 1\n" +
                            "special character.");
                }
                MainActivity.sharedPreferences.edit().putString("Name",name.getText().toString()).apply();
                if(isValidMail(email.getText().toString())&&isValidMobile(phone.getText().toString())&&isValidPassword(password.getText().toString())) {
                    Intent back = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(back);
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signUp){
                    status.setText("LOGIN");
                    name.setVisibility(View.INVISIBLE);
                    signUp=false;
                }
                else {
                    status.setText("SIGN UP");
                    name.setVisibility(View.VISIBLE);
                    signUp=true;
                }
            }
        });

    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {

        String pass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";

        return Pattern.compile(pass).matcher(password).matches();

    }
}