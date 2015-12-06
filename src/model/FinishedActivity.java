package model;

import java.util.Objects;

/**
 * Created by bruno.devesa on 02/12/2015.
 */
public class FinishedActivity implements Comparable<FinishedActivity> {

    private String projectReference;
    private String activityCode;
    private String activityType;
    private int duration;
    private int delay;

    /**
     * FinishedActivity Constructor
     * @param projReference
     * @param activityCode
     * @param activityType
     * @param duration
     * @param delay
     */
    public FinishedActivity(String projReference, String activityCode, String activityType, int duration, int delay) {
        this.projectReference = projReference;
        this.activityCode = activityCode;
        this.activityType = activityType;
        this.duration = duration;
        this.delay = delay;
    }

    /**
     * @return the projectReference
     */
    public String getProjectReference() {
        return projectReference;
    }

    /**
     * @return the activityCode
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * @return the activityType
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinishedActivity other = (FinishedActivity) obj;
        if (!Objects.equals(this.projectReference, other.projectReference)) {
            return false;
        }
        if (!Objects.equals(this.activityCode, other.activityCode)) {
            return false;
        }
        return true;
    }

    @Override
    /**
     * compareTo method to comapre delay values
     */
    public int compareTo(FinishedActivity other) {
        String thisStr = this.getDelay() + this.getProjectReference() + this.getActivityCode();
        String otherString = other.getDelay() + other.getProjectReference() + other.getActivityCode();

        return thisStr.compareTo(otherString);
    }


}
