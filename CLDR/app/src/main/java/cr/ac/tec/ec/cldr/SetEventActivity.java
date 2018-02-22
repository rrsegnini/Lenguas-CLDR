package cr.ac.tec.ec.cldr;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SetEventActivity extends AppCompatActivity {

    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_event);


        Intent intent = getIntent();
        int year = intent.getIntExtra("Year", 0);
        int month = intent.getIntExtra("Month", 0);
        int day = intent.getIntExtra("Day", 0);
        int dayOfWeek = intent.getIntExtra("DayOfWeek", 0);
        month++;

        String dayWeek = getDayOfTheWeek(dayOfWeek);




        TextView setevent_txvSelDate = findViewById(R.id.setevent_txvSelDate);
        setevent_txvSelDate.setText(dayWeek + ", " + day + "/" + month + "/" + year);
        setEventActivity();
        //try{setEventActivity();}catch(Exception e){System.out.println("NOP");}

    }

    /**
     * Indicates a day of the week. Starts on Sunday.
     * @param idDay An integer between 1 and 7.
     * @return The name of the day of the week
     */
    private String getDayOfTheWeek(int idDay){
        switch (idDay ) {
            case Calendar.SUNDAY:
                return "Sunday";

            case Calendar.MONDAY:
                return "Monday";

            case Calendar.TUESDAY:
                return "Tuesday";

            case Calendar.WEDNESDAY:
                return "Wednesday";

            case Calendar.THURSDAY:
                return "Thursday";

            case Calendar.FRIDAY:
                return "Friday";

            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "Error";

        }

    }


    /**
     * Add the event to the user database. "Activity" refers to the event the user desires to save,
     * not to the Android Studio reserved word.
     */
    private void setEventActivity(){//} throws Exception{
        final TimePicker setevent_tmeTimePicker = findViewById(R.id.setevent_tmeTimePicker);



        final FloatingActionButton
                setevent_fbtAddActivity =
                findViewById(R.id.setevent_fbtAddActivity);

        final TextInputEditText
                setevent_etxActivityName =
                findViewById(R.id.setevent_etxActivityName);

        final TextInputEditText
                setevent_etxActivityPlace =
                findViewById(R.id.setevent_etxActivityPlace);

        setevent_fbtAddActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT < 23){
                    int getHour = setevent_tmeTimePicker.getCurrentHour();
                    int getMinute = setevent_tmeTimePicker.getCurrentMinute();
                    Toast.makeText(getApplicationContext(),"Hh"+setevent_etxActivityName.getText().toString(),
                            Toast.LENGTH_SHORT);
                    try {
                        Toast.makeText(getApplicationContext(),"Hh"+setevent_etxActivityName.getText(),
                                Toast.LENGTH_SHORT);
                        /*
                        if (setevent_etxActivityName.getText().equals(null)
                                && setevent_etxActivityPlace.getText().equals(null)) {
                            throw new Exception("Missing values",
                                    new Throwable("Missing values"));
                        }*/
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);

                    }



                    /*Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getHour + ":" + getMinute, Toast.LENGTH_SHORT);

                    toast1.show();*/


                } else{
                    int getHour = setevent_tmeTimePicker.getHour();
                    int getMinute = setevent_tmeTimePicker.getMinute();

                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    getHour + ":" + getMinute, Toast.LENGTH_SHORT);

                    toast1.show();


                }


            }
        });
    }

}
