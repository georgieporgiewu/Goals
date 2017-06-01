package com.georgeqwu.goals.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by glookogeorge on 5/25/17.
 */

public class Goal extends RealmObject {

    public enum PriorityValue {
        LOW(1), MEDIUM(2), HIGH(3), URGENT(4), NONE(0);
        private final int value;
        PriorityValue(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public PriorityValue getPriorityValueForString(String s) {
            switch (s.toLowerCase()) {
                case ("low"):
                    return LOW;
                case ("medium"):
                    return MEDIUM;
                case ("high"):
                    return HIGH;
                case ("urgent"):
                    return URGENT;
                default:
                    return NONE;
            }
        }
    }

    @Required
    private String mGoalName;
    private int mPriorityValue;
    @Required
    private Date mStartDate;
    private Date mEndDate;
    private RealmList<Task> mTasks;

    public void savePriorityValue(PriorityValue val) {
        this.mPriorityValue = val.getValue();
    }

    public PriorityValue getPriorityValue(int i) {
        switch (i) {
            case 1:
                return PriorityValue.LOW;
            case 2:
                return PriorityValue.MEDIUM;
            case 3:
                return PriorityValue.HIGH;
            case 4:
                return PriorityValue.URGENT;
            default:
                return PriorityValue.NONE;
        }
    }


}
