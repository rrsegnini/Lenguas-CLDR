package cr.ac.tec.ec.cldr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

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

        String dayWeek = getDayOfTheWeek(dayOfWeek);




        TextView setevent_txvSelDate = findViewById(R.id.setevent_txvSelDate);
        setevent_txvSelDate.setText(dayWeek + ", " + day + "/" + month + "/" + year);

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

}
