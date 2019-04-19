package com.example.admin.payrollapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Schedule Class stores the information of one Schedule information of the employee.
 *  * @author  Team6 COMP 313-001
 *  * @version 1.0
 *  * @since   10/4/2019
 */
public class Schedule {
    String start;
    String end;

       public Schedule() {
    }

    public Schedule(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "start='" + start + '\'' +
                ", end='" + end + '\'' ;

    }
}
