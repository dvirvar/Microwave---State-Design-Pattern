package com.admathailand.microwave.objects;

import java.text.DateFormat;
import java.util.Date;

public class MicrowaveLog {
    private String time;
    private String description;

    public MicrowaveLog(String description) {
        final Date date = new Date(System.currentTimeMillis());
        this.time = DateFormat.getDateTimeInstance().format(date);
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
