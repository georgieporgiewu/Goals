package com.georgeqwu.goals.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by glookogeorge on 5/25/17.
 */

public class Goal extends RealmObject {

    public enum Priority {
        LOW(1), MEDIUM(2), HIGH(3), URGENT(4), NONE(0);
        private final int value;
        Priority(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    @Required
    private String mGoalName;
    @Required
    private Priority mPriority;
    @Required
    private Date mStartDate;
    private Date mEndDate;
    private RealmList<Task> mTasks;


}
