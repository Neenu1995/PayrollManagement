package com.example.admin.payrollapplication;

public class Schedule {
    String date;
    String time;

    @Override
    public String toString() {
        return "Schedule{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Schedule() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Schedule(String date, String time) {
        this.date = date;
        this.time = time;
    }
}
