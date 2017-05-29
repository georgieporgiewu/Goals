package com.georgeqwu.goals.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by glookogeorge on 5/25/17.
 */

public class Task extends RealmObject {

    // if a task can be defined as PERCENTAGE, then user can input
    // a percentage to describe progress
    // otherwise, some tasks are more abstract and progress can
    // be tracked by time spent
    public enum TrackingCriteria {
        PERCENTAGE, TIME
    }

    public enum TrackingCriteriaTimeUnit {
        MINUTE, HOUR, DAY, WEEK, MONTH, QUARTER
    }

    @Required
    private String mName;
    private TrackingCriteria mTrackingCriteria;
    // if mTrackingCriteria is percentage, mProgress is an integer 0:100
    // if is time, mProgress is integer timeUnits
    private int mProgressPercentage;
    private int mProgressTime;
    private TrackingCriteria mProgressTimeUnit;
    private Date mProjectedStartDate;
    private Date mProjectedEndDate;


}
