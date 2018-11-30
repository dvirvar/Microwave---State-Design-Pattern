package com.admathailand.microwave.states;

import com.admathailand.microwave.objects.Microwave;
import com.admathailand.microwave.objects.MicrowaveLog;

public class StoppedState implements MicrowaveState {

    @Override
    public void plus10Sec(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 seconds while the timer is stopped"));
    }

    @Override
    public void plus1Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 1 minute while the timer is stopped"));
    }

    @Override
    public void plus10Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 minutes while the timer is stopped"));
    }

    @Override
    public void start(Microwave microwave) {
        microwave.startTimer();
        microwave.setCurrentState(microwave.getRunningState());
    }

    @Override
    public void clear(Microwave microwave) {
        microwave.resetTimer();
        microwave.setCurrentState(microwave.getClearState());
    }

    @Override
    public void open(Microwave microwave) {
        microwave.setOpened(true);
        microwave.setCurrentState(microwave.getOpenedState());
    }
}
