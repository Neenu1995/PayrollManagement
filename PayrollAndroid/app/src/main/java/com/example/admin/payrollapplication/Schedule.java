package com.example.admin.payrollapplication;

import java.util.Map;

/**
 * Schedule Class stores the information of one Schedule information of the employee.
 */
public class Schedule {
    Map<String, String> start;
    Map<String, String> end;

    public Schedule(Map<String, String> start, Map<String, String> end) {
        this.start = start;
        this.end = end;
    }

    public Schedule() {
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
                "start=" + start.values() +
                ", end=" + end.values() +
                '}';
    }
}
