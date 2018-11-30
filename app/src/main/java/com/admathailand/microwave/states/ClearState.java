package com.admathailand.microwave.states;

import com.admathailand.microwave.objects.Microwave;
import com.admathailand.microwave.objects.MicrowaveLog;

public class ClearState implements MicrowaveState {

    @Override
    public void plus10Sec(Microwave microwave) {
        microwave.add10Sec();
    }

    @Override
    public void plus1Min(Microwave microwave) {
        microwave.add1Min();
    }

    @Override
    public void plus10Min(Microwave microwave) {
        microwave.add10Min();
    }

    @Override
    public void start(Microwave microwave) {
        final int sec = Integer.parseInt(microwave.getTvSec().getText().toString());
        final int min = Integer.parseInt(microwave.getTvMin().getText().toString());

        if (sec == 0 && min == 0) {
            microwave.add30Sec();
        }
        microwave.startTimer();
        microwave.setCurrentState(microwave.getRunningState());
    }

    @Override
    public void clear(Microwave microwave) {
        microwave.resetTimer();
    }

    @Override
    public void open(Microwave microwave) {
        microwave.setOpened(true);
        microwave.setCurrentState(microwave.getOpenedState());
    }
}
