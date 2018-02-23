package cr.ac.tec.ec.cldr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import domain.Event;

public class MainActivity extends AppCompatActivity {
    public static int selectedYear;
    public static int selectedMonth;
    public static int selectedDayOfMonth;
    private float initialX;
    private SimpleDateFormat dateFormatMonth =
            new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

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


        setListListener();


        /*
         CompactCalendarView calendarView= findViewById(R.id.main_cldCalendar);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            final ActionBar actionBar = getSupportActionBar();
            //actionBar.setDisplayHomeAsUpEnabled(false);
            //actionBar.setTitle(null);

            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Fri Oct 21 00:00:00 AST 2016") == 0) {
                    Toast.makeText(context, "Teachers' Professional Day", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });*/

    }


    /**
     * Returns the selected date
     */
    public static String getSelectedDate(){
        return selectedYear + " " + selectedMonth + " " + selectedDayOfMonth;
               // "EEE MMM dd hh:mm:ss 'GMT'Z yyyy"

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
        addListenerOnSpinner();

    }

    /**
     * Add a listener on the spinner to detect when an option is selected o changed.
     */
    public void addListenerOnSpinner() {

        final Spinner main_spnSorter;
        main_spnSorter = (Spinner)findViewById(R.id.main_spnSorter);


        main_spnSorter.setOnItemSelectedListener(new Spinner.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {


                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem == "Sort by name"){

                    ViewFlipper main_flvOrderByViews = findViewById(R.id.main_vflOrderByViews);
                    main_flvOrderByViews.setDisplayedChild(0);
                    updateEventList();

                }
                else if (selectedItem == "Sort by date"){

                    ViewFlipper main_flvOrderByViews = findViewById(R.id.main_vflOrderByViews);
                    main_flvOrderByViews.setDisplayedChild(1);

                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    /**
     * Updates the list that contains all the events added by the user, ordered by name.
     */
    private void updateEventList(){
        data.File f = new data.File();

        ListView main_ltvEventList = findViewById(R.id.main_ltvEventList);

        ArrayAdapter eventsAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        List<Event> events = f.getEventsData();
        //LoginActivity.mainObject.getEventList();

        for (int i =0; i < LoginActivity.mainObject.getEventList().size(); i++){
            eventsAdapter.add(events.get(i).getId() + " - " + events.get(i).getName());
            //eventsAdapter.add(events.get(i));
        }


        main_ltvEventList.setAdapter(eventsAdapter);
    }

    /**
     * Sets a listener on the items of the ListView.
     */
    private void setListListener(){
        ListView main_liyByName = findViewById(R.id.main_ltvEventList);
        main_liyByName.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StringTokenizer st =
                        new StringTokenizer(adapterView.getItemAtPosition(i).toString());

                String id = st.nextToken();
                Event clickedEvent = LoginActivity.mainObject.getEventById(Integer.parseInt(id));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Event information");
                builder.setMessage(clickedEvent.getName() +
                "\nPlace: " + clickedEvent.getPlace() +
                "\nDatetime: " + clickedEvent.getDate().toString());

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        dialog.dismiss();
                    }
                });


                AlertDialog alert = builder.create();
                alert.show();;

            }
        });

        main_liyByName.setOnItemLongClickListener(new ListView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final StringTokenizer st =
                        new StringTokenizer(adapterView.getItemAtPosition(i).toString());

                // Piece of code taken from
                // https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
                // Submitted by user nikki on StackOverflow.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Do you want to delete the event?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        String id = st.nextToken();
                        Event clickedEvent =
                                LoginActivity.mainObject.getEventById(Integer.parseInt(id));
                        LoginActivity.mainObject.deleteEventById(Integer.parseInt(id));

                        updateEventList();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });
    }
}
