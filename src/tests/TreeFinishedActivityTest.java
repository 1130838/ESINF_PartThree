package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertTrue;

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
    /**
     * This test pretends to validate the return of the method wich is suposed to return all the activities of a project
     * ordered by the project delay value.
     * The test is performed storing the project delays values and comparing all the values in wich
     * the value of the next is always bigger then the value of the previous project.
     */
    public void testPrintProjectsAndActivitiesByOrderOfDelayTime() throws Exception {

        // import project from file

        String projectFileName = "finishedProj";
        String activityFileName = "finishedActivities";

        ProjectBuild projectBuild = new ProjectBuild();
        projectBuild.importData(projectFileName, activityFileName);

        // get the trees
        TreeFinishedProject treeFinishedProject = projectBuild.getProjectTree();
        TreeFinishedActivity treeFinishedActivity = projectBuild.getActivityTree();

        // get the finishedProjectsTree in order - IMPORTANT: inOrder() gives already the project ordered by its delay time
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

        // creates a list of Projects
        List<FinishedProject> finishedProjectList = new ArrayList<>();

        // iterate the created list to create each project of the tree
        for (int i = 0; i < copy.size(); i++) {

            FinishedProject finishedProjectTemp =
                    new FinishedProject(copy.get(i).getProjectReference(), copy.get(i).getProjectType(),
                            copy.get(i).getCompletionTime(), copy.get(i).getDelay());

            // adds project to the list
            finishedProjectList.add(finishedProjectTemp);

            // System.out.println("Project " + finishedProjectTemp.getProjectReference() + "Project Delay = " + finishedProjectTemp.getDelay());

            // fill the allActivitiesFromProject with the method
            allActivitiesFromProject = treeFinishedActivity.PrintProjectsAndActivitiesByOrderOfDelayTime(finishedProjectTemp);


            // add to the List of Maps
            finalList.add(allActivitiesFromProject);

        }

        // print the results
        System.out.println("## testAllActivitiesByTypeOfProject Test ##");


        System.out.println("-- Print all projects and its activities by order of project delay time --");
        System.out.println("results:");

        // list of results to check in AssertTrue
        List<Integer> results = new ArrayList<>();

        for (int i = 0; i < finalList.size(); i++) {

            results.add(finishedProjectList.get(i).getDelay());

            System.out.println("Project Ref : " + finishedProjectList.get(i).getProjectReference() + " Project Delay = " + finishedProjectList.get(i).getDelay());

            for (Map.Entry<String, List<FinishedActivity>> entry : finalList.get(i).entrySet()) {
                System.out.println("   prj.Ref = " + entry.getValue().get(0).getProjectReference() +
                        "/" + entry.getKey() + "/" + " delay = " + entry.getValue().get(0).getDelay());
            }
            System.out.println("---------------------------------------------------");

        }

        System.out.println("delay check:");
        for (int i =1; i <  results.size(); i++) {
            System.out.println(results.get(i) + " >  " + results.get(i-1));
            assertTrue(results.get(i) > results.get(i - 1 ));

        }
    }


}