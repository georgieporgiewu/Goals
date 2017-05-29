package com.georgeqwu.goals.models;

/**
 * Created by glookogeorge on 5/26/17.
 */

// need a class for an abstract description of task, then
    // another class for implementation of task?
public class RepeatedTask extends Task {
    public enum TimeLengthUnit {
        DAILY, WEEKLY, TWOWEEKLY, MONTHLY, QUARTERLY
    }

    private TimeLengthUnit mRepeatEveryUnit;
    private int mNumTimesPerUnit;

}
