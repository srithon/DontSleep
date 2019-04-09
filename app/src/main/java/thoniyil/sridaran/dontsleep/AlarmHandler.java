package thoniyil.sridaran.dontsleep;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;

import androidx.fragment.app.Fragment;
import thoniyil.sridaran.dontsleep.R;

public class AlarmHandler extends Fragment {
    private static CountDownTimer timer;
    private static boolean cancelled = false;
    private static MediaPlayer on = null;
    /*private static AlarmHandler instance;

    public static AlarmHandler get()
    {
        if (instance == null)
        {
            instance = new AlarmHandler();
        }

        return instance;
    }*/

    public static void countDown(final int interval, View view, final Context context) {
        final TextView textBox = (TextView) view;

        timer = new CountDownTimer((interval * 1000), 1000) {
            //private long millisUntilFinished;

            public void onTick(long millisUntilFinished) {
                //this.millisUntilFinished = millisUntilFinished;
                textBox.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textBox.setText("Prompting!");

                //Intent play_sound = new Intent();

                try
                {
                    final MediaPlayer mp = MediaPlayer.create(context, R.raw.beep);
                    mp.setVolume(50, 50);
                    mp.setLooping(false);

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer j) {
                            j.stop();
                            j.release();

                            if (!AlarmCanceller.listen(textBox))
                            {
                                try
                                {
                                    Thread.sleep(1000);
                                }
                                catch (InterruptedException h)
                                {

                                }

                                try
                                {
                                    on = MediaPlayer.create(context, R.raw.fire_truck_sound);
                                    on.setVolume(100, 100);
                                    on.setLooping(true);
                                    on.start();
                                    cancelled = true;
                                }
                                catch(Exception e)
                                {
                                    Log.e("ERROR", "Could not find resource!!");
                                }
                            }
                            //try {
                                if (!cancelled)
                                    AlarmHandler.countDown(interval, ((Activity) context).findViewById(R.id.interval_countdown_text), context);
                           // } catch (Exception e) {
                                //textBox.setText("Ugh thta shit dint work");
                            //}
                        }
                    });

                    //try {
                        //mp.prepare();
                        mp.start();
                    /*} catch (Exception e) { //(IOException e) {
                        textBox.setText("ERROR: Failed prepare");
                    }*/

                /*SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
                int soundId = soundPool.load(context, R.raw.ringtone, 1);*/
                }
                catch (Exception f) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    f.printStackTrace(pw);
                    String sStackTrace = sw.toString();
                    //if (textBox.)
                    textBox.setText(sStackTrace);
                }
            }
            /*try
            {

            }*/
        }.start();
    }

    public static void cancel(View view, View view2) {
        try {
            timer.cancel();
            cancelled = true;
            final TextView textBox = (TextView) view;
            textBox.setText("Timer cancelled.");
            final TextView textBox2 = (TextView) view2;
            textBox2.setText("Timer cancelled.");
            if (on != null) {
                on.stop();
                on.release();
            }
        } catch (NullPointerException e) {

        }
    }
}
