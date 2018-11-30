package com.admathailand.microwave.objects;

import android.content.Context;
import android.widget.TextView;

import com.admathailand.microwave.activities.MainActivity;
import com.admathailand.microwave.adapters.MicrowaveLogAdapter;
import com.admathailand.microwave.states.ClearState;
import com.admathailand.microwave.states.MicrowaveState;
import com.admathailand.microwave.states.OpenedState;
import com.admathailand.microwave.states.RunningState;
import com.admathailand.microwave.states.StoppedState;

import java.util.Timer;
import java.util.TimerTask;

public class Microwave {
    //region microwaveStates
    private MicrowaveState clearState;
    private MicrowaveState runningState;
    private MicrowaveState stoppedState;
    private MicrowaveState openedState;
    //endregion
    private MicrowaveState currentState;

    private Context context;
    private MicrowaveLogAdapter microwaveLogAdapter;
    private TextView tvSec, tvMin;
    private Timer timer;
    private TimerTask timerTask;
    private MicrowaveSoundManager microwaveSoundManager;

    //boolean to check if the door has been opened
    private boolean isOpened;

    public Microwave(Context context, MicrowaveLogAdapter microwaveLogAdapter, TextView tvSec, TextView tvMin) {
        clearState = new ClearState();
        runningState = new RunningState();
        stoppedState = new StoppedState();
        openedState = new OpenedState();

        this.context = context;
        this.microwaveLogAdapter = microwaveLogAdapter;
        this.tvSec = tvSec;
        this.tvMin = tvMin;
        this.microwaveSoundManager = new MicrowaveSoundManager(context);
        setCurrentState(clearState);
    }

    //Assigning block
    {
        timer = new Timer();
        timerTask = timerTaskCreator();
    }

    //region getters
    public MicrowaveSoundManager getMicrowaveSoundManager() {
        return microwaveSoundManager;
    }

    public MicrowaveLogAdapter getMicrowaveLogAdapter() {
        return microwaveLogAdapter;
    }

    public TextView getTvSec() {
        return tvSec;
    }

    public TextView getTvMin() {
        return tvMin;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public MicrowaveState getClearState() {
        return clearState;
    }

    public MicrowaveState getRunningState() {
        return runningState;
    }

    public MicrowaveState getStoppedState() {
        return stoppedState;
    }

    public MicrowaveState getOpenedState() {
        return openedState;
    }

    public MicrowaveState getCurrentState() {
        return currentState;
    }
    //endregion

    //region setters
    public void setCurrentState(MicrowaveState currentState) {
        this.currentState = currentState;
        if (currentState == clearState){
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave switched to clear state"));
        }else if (currentState == runningState){
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave switched to running state"));
        }else if (currentState == stoppedState){
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave switched to stopped state"));
        }else if (currentState == openedState){
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave switched to opened door state"));
        }
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
        if (opened){
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave's door has been opened"));
        }else {
            microwaveLogAdapter.addLog(new MicrowaveLog("Microwave's door has been closed"));
        }
    }

    //endregion

    //region timerFunctions
    public void startTimer(){
        timer.scheduleAtFixedRate(timerTask,0,1000);
        microwaveLogAdapter.addLog(new MicrowaveLog("Microwave timer has been started"));
        microwaveSoundManager.playBeep();
        microwaveSoundManager.playRunning();
    }

    public void stopTimer(){
        timerReassigning();
        microwaveLogAdapter.addLog(new MicrowaveLog("Microwave timer has been stopped"));
        microwaveSoundManager.playBeep();
        microwaveSoundManager.pauseRunning();
    }

    public void resetTimer(){
        tvSec.setText("00");
        tvMin.setText("00");
        microwaveLogAdapter.addLog(new MicrowaveLog("Microwave timer has been reset"));
        microwaveSoundManager.playBeep();
    }

    private void timerFinished(){
        timerReassigning();
        microwaveLogAdapter.addLog(new MicrowaveLog("Microwave timer has finished"));
        microwaveSoundManager.playFinished();
        microwaveSoundManager.pauseRunning();
    }

    private void timerReassigning(){
        timer.cancel();
        timer = new Timer();
        timerTask = timerTaskCreator();
    }
    //endregion

    //region timerManipulations
    public void add10Sec(){
        final StringBuilder sSec = new StringBuilder(2);
        final StringBuilder sMin = new StringBuilder(2);
        int sec = Integer.parseInt(tvSec.getText().toString());
        int min = Integer.parseInt(tvMin.getText().toString());

        sec += 10;

        if (sec>59){
            if (min==99){
                sec = 59;
            }else {
                sec -= 60;
                min += 1;
            }
        }

        sSec.append(sec);
        sMin.append(min);
        if (sec<10){
            sSec.insert(0,"0");
        }

        if (min<10){
            sMin.insert(0,"0");
        }

        tvSec.setText(sSec);
        tvMin.setText(sMin);
        microwaveLogAdapter.addLog(new MicrowaveLog("Added 10 seconds"));
        microwaveSoundManager.playBeep();
    }

    public void add30Sec(){
        final StringBuilder sSec = new StringBuilder(2);
        final StringBuilder sMin = new StringBuilder(2);
        int sec = Integer.parseInt(tvSec.getText().toString());
        int min = Integer.parseInt(tvMin.getText().toString());

        sec += 30;

        if (sec>59){
            if (min==99){
                sec = 59;
            }else {
                sec -= 60;
                min += 1;
            }
        }

        sSec.append(String.valueOf(sec));
        sMin.append(String.valueOf(min));
        if (sSec.length()==1){
            sSec.insert(0,"0");
        }

        if (sMin.length() == 1){
            sMin.insert(0,"0");
        }

        tvSec.setText(sSec);
        tvMin.setText(sMin);
        microwaveLogAdapter.addLog(new MicrowaveLog("Added 30 seconds"));
        microwaveSoundManager.playBeep();
    }

    public void add1Min(){
        final StringBuilder sMin = new StringBuilder(2);
        int min = Integer.parseInt(tvMin.getText().toString());

         min += 1;

        if (min>99){
            min = 99;
        }

        sMin.append(String.valueOf(min));

        if (sMin.length() == 1){
            sMin.insert(0,"0");
        }

        tvMin.setText(sMin);
        microwaveLogAdapter.addLog(new MicrowaveLog("Added 1 minute"));
        microwaveSoundManager.playBeep();
    }

    public void add10Min(){
        final StringBuilder sMin = new StringBuilder(2);
        int min = Integer.parseInt(tvMin.getText().toString());

        min += 10;

        if (min>99){
            min = 99;
        }

        sMin.append(String.valueOf(min));

        tvMin.setText(sMin);
        microwaveLogAdapter.addLog(new MicrowaveLog("Added 10 minutes"));
        microwaveSoundManager.playBeep();
    }
    //endregion

    private TimerTask timerTaskCreator(){
        return new TimerTask() {
            @Override
            public void run() {
                final StringBuilder sSec = new StringBuilder(2);
                final StringBuilder sMin = new StringBuilder(2);
                int sec = Integer.parseInt(tvSec.getText().toString());
                int min = Integer.parseInt(tvMin.getText().toString());
                sec -= 1;

                if (sec < 0){
                    sec += 60;
                    min -= 1;
                }

                sSec.append(sec);
                sMin.append(min);

                if (sec<10){
                    sSec.insert(0,"0");
                }
                if (min<10){
                    sMin.insert(0,"0");
                }

                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSec.setText(sSec);
                        tvMin.setText(sMin);
                        if (tvSec.getText().toString().equals("00") && tvMin.getText().toString().equals("00")){
                            timerFinished();
                            currentState = clearState;
                        }
                    }
                });

            }
        };
    }
}
