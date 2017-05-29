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
    private String mTrackingCriteria;
    // if mTrackingCriteria is percentage, mProgress is an integer 0:100
    // if is time, mProgress is integer timeUnits
    private int mProgressPercentage;
    private int mProgressTime;
    private String mTrackingCriteriaTimeUnit;
    private Date mProjectedStartDate;
    private Date mProjectedEndDate;

    public void saveTrackingCriteria(TrackingCriteria val) {
        this.mTrackingCriteria = val.name();
    }

    public TrackingCriteria getTrackingCriteria() {
        return (mTrackingCriteria != null) ? TrackingCriteria.valueOf(mTrackingCriteria) : null;
    }

    public void saveTrackingCriteriaTimeUnit(TrackingCriteriaTimeUnit val) {
        this.mTrackingCriteriaTimeUnit = val.name();
    }

    public TrackingCriteriaTimeUnit getTrackingCriteriaTimeUnit() {
        return (mTrackingCriteriaTimeUnit != null) ? TrackingCriteriaTimeUnit.valueOf(mTrackingCriteriaTimeUnit) : null;
    }


}
