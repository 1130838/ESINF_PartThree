package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by bruno.devesa on 05/12/2015.
 */
public class TreeFinishedActivityTest {

    TreeFinishedActivity activityTree = new TreeFinishedActivity();


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAllActivitiesByTypeOfProject() throws Exception {

        // import project from file

        String projectFileName = "finishedProj";
        String activityFileName = "finishedActivities";

        ProjectBuild projectBuild = new ProjectBuild();
        projectBuild.importData(projectFileName, activityFileName);

        // get the trees
        TreeFinishedProject treeFinishedProject = projectBuild.getProjectTree();
        TreeFinishedActivity treeFinishedActivity = projectBuild.getActivityTree();

        // get the finishedProjectsTree in order
        Iterable<FinishedProject> it = treeFinishedProject.inOrder();

        // create the allActivitiesFromProject
        Map<String, List<FinishedActivity>> allActivitiesFromProject = null;

        // create an iterator for the iterable
        Iterator<FinishedProject> it2 = it.iterator();

        //convert iterator into a List
        List<FinishedProject> copy = new ArrayList<>();
        while (it2.hasNext())
            copy.add(it2.next());

        // create a list of Maps
        List<Map<String, List<FinishedActivity>>> finalList = new ArrayList<>();

        // iterate the created list to create each project of the tree
        for (int i = 0; i < copy.size(); i++) {

            FinishedProject finishedProjectTemp =
                    new FinishedProject(copy.get(i).getProjectReference(), copy.get(i).getProjectType(),
                            copy.get(i).getCompletionTime(), copy.get(i).getDelay());

            // fill the allActivitiesFromProject with the method
            allActivitiesFromProject = treeFinishedActivity.allActivitiesByTypeFromAProject(finishedProjectTemp);

            // add to the List of Maps
            finalList.add(allActivitiesFromProject);

        }

        // print the result
        System.out.println("## testAllActivitiesByTypeOfProject Test ##");


        System.out.println("-- activities of same type in project --");
        for (int i = 0; i < finalList.size(); i++) {

            for (Map.Entry<String, List<FinishedActivity>> entry : finalList.get(i).entrySet()) {
                System.out.println(" prj.Ref = " + entry.getValue().get(0).getProjectReference() + "/" + entry.getKey() + "/" + " delay = " + entry.getValue().get(0).getDelay());
            }
            System.out.println("---------------------------------------------------");
        }
    }

    @Test
    public void testEquals() throws Exception {

    }
}