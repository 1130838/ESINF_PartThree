package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        ProjectBuild projectBuild = new ProjectBuild();
        projectBuild.importData("finishedProj", "finishedActivities");


        TreeFinishedProject treeFinishedProject =  projectBuild.getProjectTree();
        //Iterable<FinishedProject> it = treeFinishedProject.inOrder();
      //  FinishedProject finishedProject = it.iterator().next();


        TreeFinishedActivity treeFinishedActivity = projectBuild.getActivityTree();


        String projectReference = treeFinishedProject.inOrder().iterator().next().getProjectReference();

        Map<String, List<FinishedActivity>> wantedMap =
                treeFinishedActivity.allActivitiesByTypeFromAProject(projectReference);

        System.out.println("activities of same type in project : ");

        for (Map.Entry<String, List<FinishedActivity>> entry : wantedMap.entrySet()) {
            System.out.println(" prj.Ref = " + entry.getValue().get(0).getProjectReference() + "/" + entry.getKey() + "/" + " delay = " + entry.getValue().get(0).getDelay()  );
        }










        System.out.println("## testAllActivitiesByTypeOfProject Test ##");
        String projectFileName = "finishedProj";
        String activityFileName = "finishedActivities";





    }

    @Test
    public void testEquals() throws Exception {

    }
}