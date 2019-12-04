package edu.iastate.room8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import edu.iastate.room8.Schedule.DayActivity;
import edu.iastate.room8.utils.DateParser;
import edu.iastate.room8.utils.SessionManager;
/**
 * This class is used for the activity Schedule. There is a calender and you can select a day on the calender
 * and see what events exist between you and your roommates and add more by clicking the button for adding new events.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class ScheduleActivity extends AppCompatActivity {
    /**
     * String holding the date selected
     */
    private String date;
    /**
     * String holding the day selected
     */
    private String day;
    /**
     * String holding the month selected
     */
    private String month;
    /**
     * String holding the year selected
     */
    private String year;
    /**
     * DateParser that will parse the date based on the onClickListener of the calender
     */
    private DateParser dateParser;
    /**
     * Boolean with whether or not the user has selected anything yet
     */
    boolean clicked;
    /**
     * Session Manager
     */
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        sessionManager = new SessionManager(this);

        Button goToScheduleDay = findViewById(R.id.goToScheduleDay);
        CalendarView calender = findViewById(R.id.calendar);

        clicked = false;
        dateParser = new DateParser(21, 10, 2019);

        goToScheduleDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked){
                    Toast.makeText(ScheduleActivity.this, "Please select a date first", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(ScheduleActivity.this, DayActivity.class);
                    i.putExtra("EXTRA_INFORMATION", date);
                    i.putExtra("Day", day);
                    i.putExtra("Month", month);
                    i.putExtra("Year", year);
                    startActivity(i);
                }

            }
        });
        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                dateParser.setDay(i2);
                dateParser.setMonth(i1);
                dateParser.setYear(i);
                day = dateParser.parseDay();
                month = dateParser.parseMonth();
                year = dateParser.parseYear();
                date = callDateParser();
                clicked = true;
            }
        });
    }

    /**
     * Calls the date parser to parse the date that is to be used for the schedule.
     * @return returns the parsed date
     */
    public String callDateParser(){
        return dateParser.parseDate();
    }

}
