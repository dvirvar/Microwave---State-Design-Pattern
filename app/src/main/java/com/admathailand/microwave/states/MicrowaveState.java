package com.admathailand.microwave.states;

import com.admathailand.microwave.objects.Microwave;

public interface MicrowaveState {

    void plus10Sec(Microwave microwave);
    void plus1Min(Microwave microwave);
    void plus10Min(Microwave microwave);
    void start(Microwave microwave);
    void clear(Microwave microwave);
    void open(Microwave microwave);

}
