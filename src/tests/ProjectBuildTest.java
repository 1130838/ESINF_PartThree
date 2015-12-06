package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by bruno.devesa on 04/12/2015.
 */
public class ProjectBuildTest {

    TreeFinishedProject projTree = new TreeFinishedProject();
    TreeFinishedActivity actTree = new TreeFinishedActivity();


    @Before
    public void setUp() throws Exception {


        ArrayList<FinishedActivity> actList = new ArrayList<>();
        actList.add(new FinishedActivity("P1", "A", "Analise", 2, 4));
        actList.add(new FinishedActivity("P1", "B", "Escavacao", 2, 0));
        actList.add(new FinishedActivity("P1", "C", "Fundacoes", 5, 3)); //
        actList.add(new FinishedActivity("P1", "D", "Estrutura", 1, 0));
        actList.add(new FinishedActivity("P1", "E", "Alvenarias", 2, 3));
        actList.add(new FinishedActivity("P1", "F", "Carpintarias", 3, 8));
        actList.add(new FinishedActivity("P1", "G", "Acabamentos", 1, 0));
        actList.add(new FinishedActivity("P1", "G", "Documentacao", 1, 0));

        actList.add(new FinishedActivity("P2", "F", "Analise", 10, 1));
        actList.add(new FinishedActivity("P2", "H", "Escavacao", 120, 12));
        actList.add(new FinishedActivity("P2", "I", "Fundacoes", 10, 1));
        actList.add(new FinishedActivity("P2", "J", "Estrutura", 3, 1));
        actList.add(new FinishedActivity("P2", "L", "Alvenarias", 2, 1));
        actList.add(new FinishedActivity("P2", "M", "Carpintarias", 1, 1));
        actList.add(new FinishedActivity("P2", "N", "Acabamentos", 15, 2));
        actList.add(new FinishedActivity("P2", "G", "Documentacao", 1, 0));

        actList.add(new FinishedActivity("P3", "F", "Analise", 10, 1));
        actList.add(new FinishedActivity("P3", "H", "Ensecadeira", 120, 12));
        actList.add(new FinishedActivity("P3", "I", "Fundacoes", 10, 1));
        actList.add(new FinishedActivity("P3", "J", "Estrutura", 3, 1));
        actList.add(new FinishedActivity("P3", "L", "Estrada", 2, 1));
        actList.add(new FinishedActivity("P3", "M", "Sinalizacao", 1, 1));
        actList.add(new FinishedActivity("P3", "N", "Acabamentos", 15, 2));
        actList.add(new FinishedActivity("P3", "G", "Documentacao", 1, 0));

        actList.add(new FinishedActivity("P4", "F", "Analise", 10, 1));
        actList.add(new FinishedActivity("P4", "H", "Escavacao", 120, 12));
        actList.add(new FinishedActivity("P4", "I", "Fundacoes", 10, 1));
        actList.add(new FinishedActivity("P4", "J", "Estrutura", 3, 1));
        actList.add(new FinishedActivity("P4", "L", "Alvenarias", 2, 1));
        actList.add(new FinishedActivity("P4", "M", "Carpintarias", 1, 1));
        actList.add(new FinishedActivity("P4", "N", "Acabamentos", 15, 2));
        actList.add(new FinishedActivity("P4", "G", "Documentacao", 1, 0));


        for (FinishedActivity act : actList)
            actTree.insert(act);

        ArrayList<FinishedProject> projList = new ArrayList<>();
        projList.add(new FinishedProject("P1", "Edificio", 250, 2));
        projList.add(new FinishedProject("P2", "Habitacao", 100, 10));
        projList.add(new FinishedProject("P3", "Ponte", 80, 30));
        projList.add(new FinishedProject("P4", "Armazem", 90, 7));

        for (FinishedProject proj : projList)
            projTree.insert(proj);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    /**
     * This test pretends to validate the importation of the project and its activities data from the texts files.
     * The test is performed asserting the results from the expected ones (hardcoded).
     */
    public void testImportData() throws Exception {


        System.out.println("## importData Test ##");
        String projectFileName = "finishedProj";
        String activityFileName = "finishedActivities";
        ProjectBuild instance = new ProjectBuild();
        instance.importData(projectFileName, activityFileName);

        //Compare projects tree
        assertTrue(this.projTree.equals(instance.getProjectTree()));

        //Compare activity tree
        assertTrue(this.actTree.equals(instance.getActivityTree()));
    }


    @Test
    /**
     * This teste pretends to validade the return of the method wich is suposed to return
     * the actvity with the biggest dealy from 2 projects with that activity in common.
     * The test is performed asserting the result values with the expected ones.
     */
    public void testLateAtivitiesOfTwoProjects() throws Exception {

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
        // List<Map<String, List<FinishedActivity>>> finalList = new ArrayList<>();

        // creates a list of Projects
        List<FinishedProject> finishedProjectList = new ArrayList<>();

        // iterate the created list to create each project of the tree
        for (int i = 0; i < copy.size(); i++) {

            FinishedProject finishedProjectTemp =
                    new FinishedProject(copy.get(i).getProjectReference(), copy.get(i).getProjectType(),
                            copy.get(i).getCompletionTime(), copy.get(i).getDelay());

            // adds project to the list
            finishedProjectList.add(finishedProjectTemp);
        }

        // choose 2 finished projects with delay from the list
        Map<String, List<FinishedActivity>> resultMap = projectBuild.lateActivitiesOfTwoProjects(finishedProjectList.get(0), finishedProjectList.get(1));


        // print results
        System.out.println("## lateActivitiesOfTwoProjects Test ##");

        System.out.println("Project Ref : " + finishedProjectList.get(0).getProjectReference() + " Project Delay = " + finishedProjectList.get(0).getDelay());
        System.out.println("Project Ref : " + finishedProjectList.get(1).getProjectReference() + " Project Delay = " + finishedProjectList.get(1).getDelay());

        System.out.println("results:");

        List<Integer> results = new ArrayList<>();

        // iterate trhough resultMap map
        for (Map.Entry<String, List<FinishedActivity>> entry : resultMap.entrySet()) {

            int delay = entry.getValue().get(0).getDelay();

            System.out.println("   prj.Ref = " + entry.getValue().get(0).getProjectReference() +
                    " / " + entry.getKey() + "/" + " Activity Delay = " + delay);

            //store results for Analise for test checking
            results.add(delay);
        }


        // check Project P1 and P4 (both with common activity Analise)

        for (int i = 0; i < results.size(); i++) {
            assertEquals(results.get(0).intValue(), 4);
            assertEquals(results.get(1).intValue(), 3);
            assertEquals(results.get(2).intValue(), 3);
            assertEquals(results.get(3).intValue(), 1);
            assertEquals(results.get(4).intValue(), 8);
            assertEquals(results.get(5).intValue(), 2);
            assertEquals(results.get(6).intValue(), 12);
        }
    }

}



