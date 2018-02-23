package cr.ac.tec.ec.cldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import domain.Main;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Intent newIntent = getIntent();
        final String currUser = newIntent.getStringExtra("User");




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText newPass = findViewById(R.id.settings_txtNewPass);
                TextInputEditText newUser = findViewById(R.id.settings_txtNewUser);
                if (!newPass.getText().toString().equals("")){
                    LoginActivity.mainObject.getUserByUsername(currUser)
                            .setPassword(newPass.getText().toString());

                }

                if (!newUser.getText().toString().equals("")){
                    LoginActivity.mainObject.getUserByUsername(currUser)
                            .setUsername(newUser.getText().toString());
                }

                data.File f = new data.File(LoginActivity.mainObject.getUserList());
                f.saveData();

                Toast.makeText(getApplicationContext(), "Saved changes", Toast.LENGTH_LONG);
                Intent newIntent = new Intent(SettingsActivity.this,
                        LoginActivity.class);
                SettingsActivity.this.startActivity(newIntent);

            }
        });



    }

}
