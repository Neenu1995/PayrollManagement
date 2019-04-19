package com.example.admin.payrollapplication;

import java.util.Map;

public class Schedule {
    Map<String, String> start;
    Map<String, String> end;

    public Schedule(Map<String, String> start, Map<String, String> end) {
        this.start = start;
        this.end = end;
    }

    public Map<String, String> getStart() {
        return start;
    }

    public void setStart(Map<String, String> start) {
        this.start = start;
    }

    public Map<String, String> getEnd() {
        return end;
    }

    public void setEnd(Map<String, String> end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
