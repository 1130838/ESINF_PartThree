package model;

import java.io.FileNotFoundException;
import java.util.*;

import treebase.BST;
import static utils.FileImport.importDataFromFile;
/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class TreeFinishedActivity extends BST {


    /**
     * Empty constructor for testing purposes
     */
    public TreeFinishedActivity() {
    }

    /**
     * This constructor populates the newly created tree with FinishedActivities
     * imported from the text file which name is fileName
     * @param fileName Name of the file to import FinishedActivities
     * @throws java.io.FileNotFoundException
     */
    public TreeFinishedActivity(String fileName) throws FileNotFoundException {
        ArrayList<ArrayList<String>> activities = importDataFromFile(fileName);


        for(ArrayList<String> activ : activities){
            insert(new FinishedActivity(activ.get(0), activ.get(1), activ.get(2), Integer.parseInt(activ.get(3)), Integer.parseInt(activ.get(4))));
        }
    }

    /**
     * Method to return all the projectd activities ordered by its delay time wich is performed by inOrder() BST method
     * @param finishedProject
     * @return allActivitiesMap
     */
    public Map<String, List<FinishedActivity>> PrintProjectsAndActivitiesByOrderOfDelayTime(FinishedProject finishedProject) {

        if (finishedProject.getProjectReference() == null) {
            return null;
        }

        Iterable<FinishedActivity> allActivities = super.inOrder();

        Iterator<FinishedActivity> it = allActivities.iterator();

        Map<String, List<FinishedActivity>> allActivitiesMap = new HashMap<>();


        while (it.hasNext()) {
            FinishedActivity temp = it.next();
            if (temp.getProjectReference().equalsIgnoreCase(finishedProject.getProjectReference())) {
                /*if the activity Type is already in the List, just add*/
                String activityType = temp.getActivityType();
                if (allActivitiesMap.containsKey(activityType)) {
                    allActivitiesMap.get(activityType).add(temp);

                } else {
                    List<FinishedActivity> activityList = new ArrayList();
                    activityList.add(temp);               //add activity to new list
                    allActivitiesMap.put(activityType, activityList);    //add list to hashmap
                }

            }
        }

        return allActivitiesMap;
    }


    /**
     * Method to return all activities from a project in preOrder ( there will be no ordenation)
     * @param finishedProject
     * @return allActivitiesMap
     */
    public Map<String, List<FinishedActivity>> allActivitesFromAproject(FinishedProject finishedProject) {

        if (finishedProject.getProjectReference() == null) {
            return null;
        }

        Iterable<FinishedActivity> allActivities = super.preOrder();

        Iterator<FinishedActivity> it = allActivities.iterator();

        Map<String, List<FinishedActivity>> allActivitiesMap = new HashMap<>();


        while (it.hasNext()) {
            FinishedActivity temp = it.next();
            if (temp.getProjectReference().equalsIgnoreCase(finishedProject.getProjectReference())) {
                /*if the activity Type is already in the List, just add*/
                String activityType = temp.getActivityType();
                if (allActivitiesMap.containsKey(activityType)) {
                    allActivitiesMap.get(activityType).add(temp);

                } else {
                    List<FinishedActivity> activityList = new ArrayList();
                    activityList.add(temp);               //add activity to new list
                    allActivitiesMap.put(activityType, activityList);    //add list to hashmap
                }

            }
        }

        return allActivitiesMap;
    }


    @Override
    /**
     * Method to perform the equality of an tree objects
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreeFinishedActivity other = (TreeFinishedActivity) obj;

        Iterator otherIt = other.inOrder().iterator();
        Iterator instanceIt = this.inOrder().iterator();
        while (otherIt.hasNext()) {
            if (!otherIt.next().equals(instanceIt.next())) {
                return false;
            }
        }

        return true;
    }


}
