package model;

/**
 * Created by bruno.devesa on 04/12/2015.
 */
public class ActivityPertCpm {

    Activity activity;
    float earlyStart;
    float latestStart;
    float earlyFinish;
    float latestFinish;
    float slack;
    public TimeUnit timeUnit;

    public ActivityPertCpm(Activity activity, float earlyStart, float earlyFinish, float latestStart, float latestFinish, float slack, TimeUnit timeUnit) {
        this.activity = activity;
        this.earlyStart = earlyStart;
        this.earlyFinish = earlyFinish;
        this.latestStart = latestStart;
        this.latestFinish = latestFinish;
        this.slack = slack;
        this.timeUnit = timeUnit;
    }

    public Activity getActivity() {
        return activity;
    }

    public float getEarlyStart() {
        return earlyStart;
    }

    public float getEarlyFinish() {
        return earlyFinish;
    }

    public float getLatestStart() {
        return latestStart;
    }

    public float getLatestFinish() {
        return latestFinish;
    }

    public float getSlack() {
        return slack;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityPertCpm other = (ActivityPertCpm) obj;
        if (!this.activity.equals(other.activity)) {
            return false;
        }
        if (Float.floatToIntBits(this.earlyStart) != Float.floatToIntBits(other.earlyStart)) {
            return false;
        }
        if (Float.floatToIntBits(this.earlyFinish) != Float.floatToIntBits(other.earlyFinish)) {
            return false;
        }
        if (Float.floatToIntBits(this.latestStart) != Float.floatToIntBits(other.latestStart)) {
            return false;
        }
        if (Float.floatToIntBits(this.latestFinish) != Float.floatToIntBits(other.latestFinish)) {
            return false;
        }
        if (Float.floatToIntBits(this.slack) != Float.floatToIntBits(other.slack)) {
            return false;
        }
        return true;
    }


}
