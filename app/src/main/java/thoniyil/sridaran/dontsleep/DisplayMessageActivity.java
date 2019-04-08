package thoniyil.sridaran.dontsleep;

import androidx.appcompat.app.AppCompatActivity;
import thoniyil.sridaran.dontsleep.R;
//import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
//import android.transition.TransitionManager;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    //private ConstraintLayout constraintLayout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        textView = findViewById(R.id.textView2);
        textView.setText(message);
        //this.constraintLayout = findViewById(R.id.activity_display_message_keyframe2); // member variable
        //animateToKeyframeTwo();
    }

    /*@Override
    protected void onResume()
    {
        super.onResume();
        textView.setText("Resumed");
    }*/

    /*private void animateToKeyframeTwo() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.load(this, R.layout.activity_display_message_keyframe2);
        TransitionManager.beginDelayedTransition(constraintLayout); //change?
        constraintLayout.
    }*/

}
