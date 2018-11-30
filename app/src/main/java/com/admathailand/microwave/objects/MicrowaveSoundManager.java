package com.admathailand.microwave.objects;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import com.admathailand.microwave.R;

public class MicrowaveSoundManager {
    private SoundPool soundPool;
    private int microwave_beep,microwave_running,opening_microwave,closing_microwave;
    //Boolean to check if microwave has been running, if yes just resume sound, if no play it for the first time
    private boolean hasRun;

    public MicrowaveSoundManager(Context context){
        final AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();

        microwave_beep = soundPool.load(context, R.raw.microwave_beep,1);
        microwave_running = soundPool.load(context,R.raw.microwave_running,1);
        opening_microwave = soundPool.load(context,R.raw.opening_microwave,1);
        closing_microwave = soundPool.load(context,R.raw.closing_microwave,1);
    }

    public void playBeep(){
        soundPool.play(microwave_beep,1,1,0,0,1);
    }

    public void playFinished(){
        soundPool.play(microwave_beep,1,1,0,2,1);
    }

    public void playRunning(){
        if (hasRun){
            soundPool.resume(microwave_running);
        }else{
            soundPool.play(microwave_running,1,1,0,-1,1);
            hasRun = true;
        }
    }

    public void playOpening(){
        soundPool.play(opening_microwave,1,1,0,0,1);
    }

    public void playClosing(){
        soundPool.play(closing_microwave,1,1,0,0,1);
    }

    public void pauseRunning(){
        soundPool.pause(microwave_running);
    }

    public void releasePool(){
        soundPool.release();
        soundPool = null;
    }

}
