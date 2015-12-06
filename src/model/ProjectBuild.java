package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bruno.devesa on 03/12/2015.
 */
public class ProjectBuild {

    public TreeFinishedProject projectTree;
    public TreeFinishedActivity activityTree;


    /**
     *
     * @return project Tree
     */
    public TreeFinishedProject getProjectTree() {
        return projectTree;
    }

    /**
     *
     * @return activityTree
     */
    public TreeFinishedActivity getActivityTree() {
        return activityTree;
    }


    /**
     * Given a specific file for finished activities and for finished projects
     * this method will extract the data and create a BST for projects and another for its activities.
     *
     * @param projectFileName
     * @param activityFileName
     * @throws FileNotFoundException
     */
    public void importData(String projectFileName, String activityFileName) throws FileNotFoundException {

        projectTree = new TreeFinishedProject(projectFileName);
        activityTree = new TreeFinishedActivity(activityFileName);

    }

    /**
     * Test to return the late activity of 2 projects sharing the same activity
     * @param p1
     * @param p2
     * @return finalMap
     */
    public Map<String, List<FinishedActivity>> lateActivitiesOfTwoProjects(FinishedProject p1, FinishedProject p2) {

        if (p1.getDelay() == 0 || p2.getDelay() == 0) { /*both projects must have delay*/

            return null;
        }

            /* Map with : Key = the activity  type;  List = activities of that type*/
        Map<String, List<FinishedActivity>> finalMap = new HashMap<>();

        Map<String, List<FinishedActivity>> p1Table = activityTree.allActivitesFromAproject(p1);

        Map<String, List<FinishedActivity>> p2Table = activityTree.allActivitesFromAproject(p2);

        // iterate through Map of 1st activity
        for (Map.Entry<String, List<FinishedActivity>> entry1 : p1Table.entrySet()) {
            String acType = entry1.getKey();
            List<FinishedActivity> listact = entry1.getValue();

            /* if the project 2 has any activity with the same type then add to the final table the content of both projects*/
            //  String teste =  p1Table.get(0).get(0).getActivityType();

            // iterate through Map of 2nd activity
            for (Map.Entry<String, List<FinishedActivity>> entry2 : p2Table.entrySet()) {
                String acType2 = entry2.getKey();
                List<FinishedActivity> listact2 = entry2.getValue();

                // check if Keys are the same
                if (acType.equalsIgnoreCase(acType2)) {

                    int delay1 = listact.get(0).getDelay();
                    int delay2 = listact2.get(0).getDelay();

                    // check delays. the bigger activity delay is filled in the final Map
                    if (delay1 >= delay2) {
                        finalMap.put(acType, listact);/*put the list in the final table*/
                        //finalList.addAll(listact); /*add p1 list*/
                    } else {
                        finalMap.put(acType, listact2);/*put the list in the final table*/

                    }
                }
            }
        }
        return finalMap;
    }


}
