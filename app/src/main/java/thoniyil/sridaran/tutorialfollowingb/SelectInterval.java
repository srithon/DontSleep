package thoniyil.sridaran.tutorialfollowingb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class SelectInterval extends AppCompatActivity {
    public static final String INTERVAL_SELECTED = "thoniyil.sridaran.tutorialfollowingb.INTERVAL_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_interval);
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
        startActivity(intent);
    }
}
