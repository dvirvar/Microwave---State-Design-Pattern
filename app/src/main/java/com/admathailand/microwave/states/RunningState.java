package com.admathailand.microwave.states;

import com.admathailand.microwave.objects.Microwave;
import com.admathailand.microwave.objects.MicrowaveLog;

public class RunningState implements MicrowaveState {

    @Override
    public void plus10Sec(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 seconds while the microwave is running"));
    }

    @Override
    public void plus1Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 1 minute while the microwave is running"));
    }

    @Override
    public void plus10Min(Microwave microwave) {
        microwave.getMicrowaveLogAdapter().addLog(new MicrowaveLog("Can't add 10 minutes while the microwave is running"));
    }

    @Override
    public void start(Microwave microwave) {
        microwave.add30Sec();
    }

    @Override
    public void clear(Microwave microwave) {
        microwave.stopTimer();
        microwave.setCurrentState(microwave.getStoppedState());
    }

    @Override
    public void open(Microwave microwave) {
        microwave.setOpened(true);
        microwave.stopTimer();
        microwave.setCurrentState(microwave.getOpenedState());
    }
}
