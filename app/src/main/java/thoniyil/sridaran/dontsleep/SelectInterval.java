package thoniyil.sridaran.dontsleep;

import androidx.appcompat.app.AppCompatActivity;
import thoniyil.sridaran.dontsleep.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SelectInterval extends AppCompatActivity {
    public static final String INTERVAL_SELECTED = "thoniyil.sridaran.dontsleep.INTERVAL_SELECTED";
    public static final String TIME_UNIT_TOGGLE = "thoniyil.sridaran.dontsleep.TIME_UNIT_TOGGLE";

    private final Bool timeUnitToggle;

    {
        timeUnitToggle = new Bool(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interval);

        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleTimeUnitButton);

        toggle.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) ->
        {
                if (isChecked)
                {
                    toggle.setText("Minutes");
                    timeUnitToggle.set(true);
                }
                else
                {

                    timeUnitToggle.set(false);
                }
        });
    }

    public void onSubmit(View view)
    {
        Intent intent = new Intent(this, AlarmListActivity.class);

        EditText interval = findViewById(R.id.interval_prompt);
        String message = interval.getText().toString();
        TextView mess = findViewById(R.id.select_interval_message);
        int intervalVal = -1;

        try
        {
            intervalVal = Integer.parseInt(message);
            if (intervalVal < 0)
                throw new NumberFormatException();
            mess.clearComposingText();
        }
        catch (NumberFormatException e)
        {
            mess.setText("Please enter a number!");
            return;
        }

        intent.putExtra(INTERVAL_SELECTED, intervalVal);
        intent.putExtra(TIME_UNIT_TOGGLE, (timeUnitToggle.getState()));

        startActivity(intent);
    }

    public void onToggleTimeUnit(View view)
    {
        ToggleButton b = (ToggleButton) view;

        if (timeUnitToggle.getState())
            b.setText("Seconds");
        else
            b.setText("Minutes");
    }
}
