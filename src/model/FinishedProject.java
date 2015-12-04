package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by bruno.devesa on 02/12/2015.
 */
public class FinishedProject implements Comparable<FinishedProject>{

    private String projectReference;
    private String projectType;
    private int completionTime;
    private int delay;

    /**
     *
     * @param projectReference
     * @param projectType
     * @param completionTime
     * @param delay
     */
    public FinishedProject(String projectReference, String projectType, int completionTime, int delay) {
        this.projectReference = projectReference;
        this.projectType = projectType;
        this.completionTime = completionTime;
        this.delay = delay;
    }

    FinishedProject(ArrayList<String> fProj) {
        this.projectReference = fProj.get(0);
        this.projectType = fProj.get(1);
        this.completionTime = Integer.parseInt(fProj.get(2));
        this.delay = Integer.parseInt(fProj.get(3));
    }

    /**
     * Get the reference
     *
     * @return
     */
    public String getProjectReference() {
        return projectReference;
    }

    /**
     * Get the type
     *
     * @return
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * Get the time that was needed to complete the project
     *
     * @return
     */
    public int getCompletionTime() {
        return completionTime;
    }

    /**
     * Get the delay that occur in the project
     *
     * @return
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Project is equal if the projectReference is the same
     *
     * @param obj - object to compare
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinishedProject other = (FinishedProject) obj;
        if (!Objects.equals(this.projectReference, other.projectReference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finishedProject{" + "projectReference=" + projectReference + ", projectType=" + projectType + ", completionTime=" + completionTime + ", delay=" + delay + '}';
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(FinishedProject t) {
        if (this.delay == t.getDelay()) {
            String thisProj = this.projectReference;
            String otherProj = t.getProjectReference();
            return thisProj.compareTo(otherProj);
        }
        return this.delay - t.getDelay();
    }
}
