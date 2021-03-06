package com.tutorialspoint;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public class Event {
    private int id = new Random().nextInt(1000);
    private String message;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    static public boolean isDay(int start, int end){
        LocalTime localTime  = LocalTime.now();
        return localTime.getHour() > start && localTime.getHour() < end;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                "}\n";
    }
}
