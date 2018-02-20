package cr.ac.tec.ec.cldr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int selectedYear;
    int selectedMonth;
    int selectedDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSpinner();

        CalendarView calendarView=(CalendarView) findViewById(R.id.main_cldCalendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month,
                                            int dayOfMonth) {
                if (year == selectedYear && month == selectedMonth
                        && dayOfMonth == selectedDayOfMonth){


                    //The Calendar object is created to take advantage of a function that returns
                    //the day of the week given a certain date.
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);



                    Intent myIntent = new Intent(MainActivity.this,
                            SetEventActivity.class);
                    myIntent.putExtra("Year", year);
                    myIntent.putExtra("Month", month);
                    myIntent.putExtra("Day", dayOfMonth);
                    myIntent.putExtra("DayOfWeek", dayOfWeek);
                    MainActivity.this.startActivity(myIntent);
                }

                selectedYear = year;
                selectedMonth = month;
                selectedDayOfMonth = dayOfMonth;


            }
        });
    }


    /**
     * Sets the options in the spinner widget.
     * The widget is used to select the sorting method of the events on the calendar.
     */
    private void setSpinner(){
        final String[] orderByOptions =
                new String[]{"Sort by date", "Sort by name", "Sort by..."};

        ArrayAdapter<String> orederByAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, orderByOptions);

        Spinner main_spnSorter;

        main_spnSorter = (Spinner)findViewById(R.id.main_spnSorter);

        orederByAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        main_spnSorter.setAdapter(orederByAdapter);

    }
}
