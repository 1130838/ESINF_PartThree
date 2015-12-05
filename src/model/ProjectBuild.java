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


    public TreeFinishedProject getProjectTree() {
        return projectTree;
    }

    public TreeFinishedActivity getActivityTree() {
        return activityTree;
    }


    /**
     * Given a specific file for finished activities and for finished projects
     * this method will extract the data and create a BST for each.
     *
     * @param projectFileName
     * @param activityFileName
     * @throws FileNotFoundException
     */
    public void importData(String projectFileName, String activityFileName) throws FileNotFoundException {

        projectTree = new TreeFinishedProject(projectFileName);
        activityTree = new TreeFinishedActivity(activityFileName);

    }

        public Map<String, List<FinishedActivity>> lateAtivitiesOf2Projects (FinishedProject p1, FinishedProject p2){

            if (p1.getDelay() == 0 || p2.getDelay() == 0) {/*both projects must have delay*/

                return null;
            }

            /* Map with : Key = the activity  type;  List = activities of that type*/
            Map<String, List<FinishedActivity>> finalTable = new HashMap<>();

            Map<String, List<FinishedActivity>> p1Table = activityTree.allActivitiesByTypeFromAProject(p1);

            Map<String, List<FinishedActivity>> p2Table = activityTree.allActivitiesByTypeFromAProject(p2);

            for (Map.Entry<String, List<FinishedActivity>> entry : p1Table.entrySet()) {
                String acType = entry.getKey();
                List<FinishedActivity> listact = entry.getValue();

            /* if the project 2 has any activity with the same type then add to the final table the content of both projects*/
                if (p2Table.containsKey(acType)) {
                    List<FinishedActivity> finalList = new ArrayList<>(); /*new List for the finalTable*/

                    finalList.addAll(entry.getValue()); /*add p1 list*/

                    finalList.addAll(p2Table.get(acType));/*add p2 list*/

                    finalTable.put(acType, finalList);/*put the list in the final table*/

                }

            }
            return finalTable;
        }


    }
