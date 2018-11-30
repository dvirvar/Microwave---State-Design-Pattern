package com.admathailand.microwave.states;

import com.admathailand.microwave.objects.Microwave;
import com.admathailand.microwave.objects.MicrowaveLog;

public class OpenedState implements MicrowaveState {

    //Boolean to check if the timer has been cleared so it will know what state to choose(Clear or Stopped)
    private boolean isCleared;

    @Override
    public void plus10Sec(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 seconds while the door is opened"));
    }

    @Override
    public void plus1Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 1 minute while the door is opened"));
    }

    @Override
    public void plus10Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 minutes the while door is opened"));
    }

    @Override
    public void start(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't start while the door is opened"));
    }

    @Override
    public void clear(Microwave microwave) {
        microwave.resetTimer();
        isCleared = true;
    }

    //Becomes close because we don't have a visual microwave that we can close
    //Or a button of close(it would be silly):P
    @Override
    public void open(Microwave microwave) {
        String sSec = microwave.getTvSec().getText().toString();
        String sMin = microwave.getTvMin().getText().toString();

        microwave.setOpened(false);
        if (isCleared || sSec.equals("00") && sMin.equals("00")){
            microwave.setCurrentState(microwave.getClearState());
            isCleared = false;
        }else {
            microwave.setCurrentState(microwave.getStoppedState());
        }
    }
}
