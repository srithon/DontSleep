package thoniyil.sridaran.dontsleep;

import androidx.appcompat.app.AppCompatActivity;
import thoniyil.sridaran.dontsleep.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlarmListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        Intent receivedIntent = getIntent();
        int interval = receivedIntent.getIntExtra(SelectInterval.INTERVAL_SELECTED, -1);

        /*if (interval == -1) {
            //return to main screen
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return;
        }*/

        TextView interval_text = findViewById(R.id.interval_text);
        interval_text.setText("Repeating alarm every " + interval + " minutes.");

        AlarmHandler.countDown(interval, findViewById(R.id.interval_countdown_text), this);
    }

    public void cancelAlarm(View view)
    {
        AlarmHandler.cancel(findViewById(R.id.interval_countdown_text), findViewById(R.id.interval_text));
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        return;
    }
}
