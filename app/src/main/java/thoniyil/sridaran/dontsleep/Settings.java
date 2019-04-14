package thoniyil.sridaran.dontsleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.HashMap;

public class Settings extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {
    public static final String BEEP_WAIT_TIME = "thoniyil.sridaran.dontsleep.BEEP_WAIT_TIME";
    public static final String BEEP_VOLUME = "thoniyil.sridaran.dontsleep.BEEP_VOLUME";
    public static final String TRUCK_VOLUME = "thoniyil.sridaran.dontsleep.TRUCK_VOLUME";

    private static final HashMap<String, Float> settings;

    static
    {
        settings = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        float waitTime = prefs.getFloat(BEEP_WAIT_TIME, 10.0f);
        float beepVolume = prefs.getFloat(BEEP_VOLUME, 0.60f);
        float truckVolume = prefs.getFloat(TRUCK_VOLUME, 1.00f);

        settings.put(BEEP_WAIT_TIME, waitTime);
        settings.put(BEEP_VOLUME, beepVolume);
        settings.put(TRUCK_VOLUME, truckVolume);

        setContentView(R.layout.activity_settings);
        loadPreferences();
    }

    protected void onStart()
    {
        loadPreferences();

        /*float waitTime = savedInstanceState.getFloat(BEEP_WAIT_TIME, 10.0f);
        float beepVolume = savedInstanceState.getFloat(BEEP_VOLUME, 0.60f);
        float truckVolume = savedInstanceState.getFloat(TRUCK_VOLUME, 1.00f);

        settings.put(BEEP_WAIT_TIME, waitTime);
        settings.put(BEEP_VOLUME, beepVolume);
        settings.put(TRUCK_VOLUME, truckVolume);*/

        super.onStart();
    }

    @Override
    protected void onPause()
    {
        /*SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();

        editor.putFloat(BEEP_WAIT_TIME, settings.get(BEEP_WAIT_TIME));
        editor.putFloat(BEEP_VOLUME, settings.get(BEEP_VOLUME));
        editor.putFloat(TRUCK_VOLUME, settings.get(TRUCK_VOLUME));

        editor.apply();*/

        settings.put(BEEP_WAIT_TIME, getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat(BEEP_WAIT_TIME, -1.0f));

        super.onPause();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() > 0)
            getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putFloat(BEEP_WAIT_TIME, Float.parseFloat(s.toString())).apply();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after)
    {

    }



    //https://stackoverflow.com/questions/8719632/multiple-seekbars-listener/13468578
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /*if (fromUser)
            if (seekBar.getId() == R.id.beep_volume_seek_bar)
            {
                Log.d("tag", "Beep Volume Seek Bar Changed - " + progress);
                settings.replace(BEEP_VOLUME, progress / 100.0f);
            }
            else
            {
                Log.d("tag", "Truck Volume Seek Bar Changed - " + progress);
                settings.replace(TRUCK_VOLUME, progress / 100.0f);
            }
        else
            Log.d("tag", "Progress of Seek Bar Programmatically Changed - " + progress);*/
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId())
        {
            case R.id.beep_volume_seek_bar:
                getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putFloat(BEEP_VOLUME, seekBar.getProgress() / 100.0f).apply();
                settings.replace(BEEP_VOLUME, seekBar.getProgress() / 100.0f);
                break;
            case R.id.truck_volume_seek_bar:
                getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putFloat(TRUCK_VOLUME, seekBar.getProgress() / 100.0f).apply();
                settings.replace(TRUCK_VOLUME, seekBar.getProgress() / 100.0f);
                break;
        }
    }

    //https://developer.android.com/guide/topics/ui/controls/spinner
    public void loadPreferences()
    {
        EditText promptField = (findViewById(R.id.prompt_time_field));
        promptField.setText(String.valueOf(settings.get(BEEP_WAIT_TIME)));
        promptField.addTextChangedListener(this);

        SeekBar beepVolumeBar = findViewById(R.id.beep_volume_seek_bar);
        beepVolumeBar.setOnSeekBarChangeListener(this);
        beepVolumeBar.setProgress((int) (settings.get(BEEP_VOLUME) * 100));

        SeekBar truckVolumeBar = findViewById(R.id.truck_volume_seek_bar);
        truckVolumeBar.setOnSeekBarChangeListener(this);
        truckVolumeBar.setProgress((int) (settings.get(TRUCK_VOLUME) * 100));
    }

    public static float getSetting(String key)
    {
        return settings.get(key);
    }
}
