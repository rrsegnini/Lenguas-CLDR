package cr.ac.tec.ec.cldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    final static domain.Main mainObject = new domain.Main();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setButtonActive();


        Button login_btnRegister = findViewById(R.id.login_btnRegister);

        login_btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText login_etxEmail = findViewById(R.id.login_etxEmail);
                EditText login_etxPss = findViewById(R.id.login_etxPss);
                if (!login_etxEmail.getText().toString().equals("") &&
                        !login_etxPss.getText().toString().equals("") &&
                        mainObject.validUsername(login_etxEmail.getText().toString())){

                    domain.User newUser = new domain.User(login_etxEmail.getText().toString(),
                            login_etxPss.getText().toString());

                    mainObject.registerUser(newUser);
                    Toast.makeText(getApplicationContext(), "User registered successfully",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /**
     * Sets the action listener on the login button.
     */
    private void setButtonActive(){
        FloatingActionButton login_fabLogin = findViewById(R.id.login_fabLogin);

        login_fabLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                EditText login_etxEmail = findViewById(R.id.login_etxEmail);
                EditText login_etxPss = findViewById(R.id.login_etxPss);


               /* Intent newIntent = new Intent(LoginActivity.this,
                        MainActivity.class);
                LoginActivity.this.startActivity(newIntent);*/

                if (mainObject.userSignIn(login_etxEmail.getText().toString(),
                        login_etxPss.getText().toString())){
                    Intent newIntent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    LoginActivity.this.startActivity(newIntent);

                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect username or password",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

}
