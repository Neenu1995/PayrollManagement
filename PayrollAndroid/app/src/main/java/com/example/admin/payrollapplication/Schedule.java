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

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @param date
     * @param time
     */
    public Schedule(String date, String time) {
        this.date = date;
        this.time = time;
    }
}
