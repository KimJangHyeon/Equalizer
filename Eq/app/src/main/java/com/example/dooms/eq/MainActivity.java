package com.example.dooms.eq;

import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    static final String RECORDED_FILE = "sdcard/test.mp3";

    private MediaPlayer mPlayer = new MediaPlayer();
    int sessionId = mPlayer.getAudioSessionId();
    private Equalizer mEqualizer;
    int playbackPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlay = (Button) findViewById(R.id.play);
        Button buttonStop = (Button) findViewById(R.id.stop);

        Seekbar mSeekbar_1 = (SeekBar)findViewById(R.id.seekBar_1);
        mSeekbar_1.setOnSeekBarChangeListener(this);
        mSeekbar_1.setProgress(mEqualizer.getBandLevel((short) 0) + 1500);

        Seekbar mSeekbar_2 = (SeekBar)findViewById(R.id.seekBar_2);
        mSeekbar_2.setOnSeekBarChangeListener(this);
        mSeekbar_2.setProgress(mEqualizer.getBandLevel((short) 1) + 1500);

        Seekbar mSeekbar_3 = (SeekBar)findViewById(R.id.seekBar_3);
        mSeekbar_3.setOnSeekBarChangeListener(this);
        mSeekbar_3.setProgress(mEqualizer.getBandLevel((short) 2) + 1500);

        Seekbar mSeekbar_4 = (SeekBar)findViewById(R.id.seekBar_4);
        mSeekbar_4.setOnSeekBarChangeListener(this);
        mSeekbar_4.setProgress(mEqualizer.getBandLevel((short) 3) + 1500);

        Seekbar mSeekbar_5 = (SeekBar)findViewById(R.id.seekBar_5);
        mSeekbar_5.setOnSeekBarChangeListener(this);
        mSeekbar_5.setProgress(mEqualizer.getBandLevel((short) 4) + 1500);

        TextView mBandInfo_1 = (TextView)findViewById(R.id.bandInfo_1);
        TextView mBandInfo_2 = (TextView)findViewById(R.id.bandInfo_2);
        TextView mBandInfo_3 = (TextView)findViewById(R.id.bandInfo_3);
        TextView mBandInfo_4 = (TextView)findViewById(R.id.bandInfo_4);
        TextView mBandInfo_5 = (TextView)findViewById(R.id.bandInfo_5);

        TextView mBandVal_1 = (TextView)findViewById(R.id.bandVal_1);
        TextView mBandVal_2 = (TextView)findViewById(R.id.bandVal_2);
        TextView mBandVal_3 = (TextView)findViewById(R.id.bandVal_3);
        TextView mBandVal_4 = (TextView)findViewById(R.id.bandVal_4);
        TextView mBandVal_5 = (TextView)findViewById(R.id.bandVal_5);

        try {
            mEqualizer = null;
            mEqualizer = new Equalizer(0, sessionId );
            mEqualizer.setEnabled(true);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        short bands = mEqualizer.getNumberOfBands();
        Log.i("number of Band", String.valueOf(bands));
        short cuPr = mEqualizer.getCurrentPreset();
        Log.i("CurrentPreset", mEqualizer.getPresetName(cuPr));

        for (short i = 0; i < bands; i++) {
            Log.i( String.valueOf(i) + "-th range is : ", String.valueOf(mEqualizer.getBandLevelRange()[0]) + " ~ " +   String.valueOf(mEqualizer.getBandLevelRange()[1]));
            Log.i( String.valueOf(i) + "-th Level is : ", String.valueOf(mEqualizer.getBandLevel(i)));
        }

        mBandInfo_1.setText(String.valueOf(String.valueOf(mEqualizer.getBandFreqRange((short) 0)[0]) + " ~ " +   String.valueOf(mEqualizer.getBandFreqRange((short) 0)[1])));
        mBandInfo_2.setText(String.valueOf(String.valueOf(mEqualizer.getBandFreqRange((short) 1)[0]) + " ~ " +   String.valueOf(mEqualizer.getBandFreqRange((short) 1)[1])));
        mBandInfo_3.setText(String.valueOf(String.valueOf(mEqualizer.getBandFreqRange((short) 2)[0]) + " ~ " +   String.valueOf(mEqualizer.getBandFreqRange((short) 2)[1])));
        mBandInfo_4.setText(String.valueOf(String.valueOf(mEqualizer.getBandFreqRange((short) 3)[0]) + " ~ " +   String.valueOf(mEqualizer.getBandFreqRange((short) 3)[1])));
        mBandInfo_5.setText(String.valueOf(String.valueOf(mEqualizer.getBandFreqRange((short) 4)[0]) + " ~ " +   String.valueOf(mEqualizer.getBandFreqRange((short) 4)[1])));

        mSeekbar_1.incrementProgressBy(1);
        mSeekbar_2.incrementProgressBy(1);
        mSeekbar_3.incrementProgressBy(1);
        mSeekbar_4.incrementProgressBy(1);
        mSeekbar_5.incrementProgressBy(1);
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
        switch(seekBar.getId()){
            case R.id.seekBar_1:
                mEqualizer.setBandLevel((short) 0, (short) (progress - 1500));
                mBandVal_1.setText(String.valueOf(mEqualizer.getBandLevel((short) 0)));
                break;
            case R.id.seekBar_2:
                mEqualizer.setBandLevel((short) 1, (short) (progress - 1500));
                mBandVal_2.setText(String.valueOf(mEqualizer.getBandLevel((short) 1)));
                break;
            case R.id.seekBar_3:
                mEqualizer.setBandLevel((short) 2, (short) (progress - 1500));
                mBandVal_3.setText(String.valueOf(mEqualizer.getBandLevel((short) 2)));
                break;
            case R.id.seekBar_4:
                mEqualizer.setBandLevel((short) 3, (short) (progress - 1500));
                mBandVal_4.setText(String.valueOf(mEqualizer.getBandLevel((short) 3)));
                break;
            case R.id.seekBar_5:
                mEqualizer.setBandLevel((short) 4, (short) (progress - 1500));
                mBandVal_5.setText(String.valueOf(mEqualizer.getBandLevel((short) 4)));
                break;


        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////

    private void playAudio(String url) throws Exception{
        killMediaPlayer();

        mPlayer = new MediaPlayer();
        try {
            mEqualizer = null;
            mEqualizer = new Equalizer(0, mPlayer.getAudioSessionId() );
            mEqualizer.setEnabled(true);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        mPlayer.setDataSource(url);
        mPlayer.prepare();
        mPlayer.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if(mPlayer != null){
            try {
                mPlayer.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void filePlay(View v) {
        try{
            playAudio(RECORDED_FILE);

            Toast.makeText(getApplicationContext(), "File Play", 2000).show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void filestop(View v) {
        if(mPlayer != null){
            playbackPosition = mPlayer.getCurrentPosition();
            mPlayer.pause();
            Toast.makeText(getApplicationContext(), "File Stop",2000).show();
        }
    }
}

