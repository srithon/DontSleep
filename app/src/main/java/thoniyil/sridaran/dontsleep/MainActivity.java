package thoniyil.sridaran.dontsleep;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import thoniyil.sridaran.dontsleep.R;

import static android.Manifest.permission.RECORD_AUDIO;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "thoniyil.sridaran.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, 20);
        }
        //this.constraintLayout = findViewById(R.id.constraint_layout) // member variable
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, SelectInterval.class);
        /*Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }
}
