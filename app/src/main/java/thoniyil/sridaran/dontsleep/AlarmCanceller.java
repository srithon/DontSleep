package thoniyil.sridaran.dontsleep;

import android.media.MediaRecorder;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class AlarmCanceller {
    private static final double DELAY = 50;
    private static final int VOLUME_DIFFERENCE = 7000;
    private static int lastLevel;
    private static boolean sleeping = false;
    private static Thread thread;

    public static boolean listen(final TextView view) {
        final BooleanChecker bool = new BooleanChecker(false);

        thread = new Thread(() -> {
            if (sleeping)
                try {
                    Log.d("tag", "passed recorder setAudioSource");
                    Thread.sleep(2000);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }

            final MediaRecorder recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            if (sleeping)
                try {
                    Log.d("tag", "passed recorder setAudioSource");
                    Thread.sleep(2000);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }

            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            recorder.setOutputFile("/dev/null");

            Log.d("tag", "passed recorder set...");

            if (sleeping)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }

            try {
                recorder.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("tag", "passed recorder prepare");

            if (sleeping)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }

            recorder.start();

            Log.d("tag", "passed recorder start");

            if (sleeping)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }

            all:
            {
                bool.set(false);

                lastLevel = recorder.getMaxAmplitude();

                Log.d("tag", "recorder get Max Amp");

                if (sleeping)
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException j) {
                        j.printStackTrace();
                    }

                for (int i = 0; i < 10.0 / (DELAY / 1000.0); i++) {
                    Log.d("tag", "Right before get max");
                    int currentVolume = recorder.getMaxAmplitude();
                    Log.d("tag", "right after get max amplitude");
                    int change = currentVolume - lastLevel;
                    lastLevel = currentVolume;
                    Log.d("tag", "change = " + change);

                    if (change >= VOLUME_DIFFERENCE) {
                        Log.d("tag", "in that one if statemen t");
                        bool.set(true);
                        break all;
                    }

                    try {
                        Thread.sleep((long) DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            recorder.stop();
            recorder.release();

            //} catch (Exception l) {
            //    Log.d("tag", "rn im in catch statement");

            if (sleeping)
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException j) {
                    j.printStackTrace();
                }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return bool.get();
        //return bool.get();
        //return false;
    }
}

class BooleanChecker {
    private boolean check;

    public BooleanChecker(boolean check) {
        this.check = check;
    }

    public void set(boolean check) {
        this.check = check;
    }

    public boolean get() {
        return check;
    }
}