package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by bruno.devesa on 04/12/2015.
 */
public class ProjectBuildTest {

    TreeFinishedProject projTree = new TreeFinishedProject();
    TreeFinishedActivity actTree = new TreeFinishedActivity();


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testImportData() throws Exception {


        ArrayList<FinishedActivity> actList = new ArrayList<>();
        actList.add(new FinishedActivity("P1", "A", "Estrutura", 2, 0));
        actList.add(new FinishedActivity("P1", "B", "Montagem", 5, 0));
        actList.add(new FinishedActivity("P1", "C", "Testes", 1, 0));
        actList.add(new FinishedActivity("P1", "D", "Interiores", 2, 0));
        actList.add(new FinishedActivity("P1", "E", "Pintura", 3, 0));
        actList.add(new FinishedActivity("P1", "F", "Acabamento", 1, 0));
        actList.add(new FinishedActivity("P2", "A", "Fundacoes", 10, 1));
        actList.add(new FinishedActivity("P2", "B", "Estrutura", 30, 3));
        actList.add(new FinishedActivity("P2", "C", "Montagem", 46, 3));
        actList.add(new FinishedActivity("P2", "D", "Especialidades", 15, 2));
        actList.add(new FinishedActivity("P2", "E", "Interiores", 30, 7));
        actList.add(new FinishedActivity("P2", "F", "Pintura", 10, 1));
        actList.add(new FinishedActivity("P2", "G", "Acabamento", 9, 2));
        actList.add(new FinishedActivity("P3", "A", "Requisitos", 30, 3));
        actList.add(new FinishedActivity("P3", "B", "Implementacao", 120, 12));
        actList.add(new FinishedActivity("P3", "C", "Testes", 10, 2));
        actList.add(new FinishedActivity("P3", "D", "Documentacao", 20, 4));
        actList.add(new FinishedActivity("P4", "A", "Estrutura", 3, 1));
        actList.add(new FinishedActivity("P4", "B", "Montagem", 4, 2));
        actList.add(new FinishedActivity("P4", "C", "Testes", 2, 1));
        actList.add(new FinishedActivity("P4", "D", "Pintura", 1, 2));
        actList.add(new FinishedActivity("P4", "E", "Expedicao", 1, 1));

        for (FinishedActivity act : actList)
            actTree.insert(act);

        ArrayList<FinishedProject> projList = new ArrayList<>();
        projList.add(new FinishedProject("P1", "Carro", 14, 0));
        projList.add(new FinishedProject("P2", "Casa", 150, 19));
        projList.add(new FinishedProject("P3", "Software", 200, 21));
        projList.add(new FinishedProject("P4", "Telemovel", 11, 7));

        for (FinishedProject proj : projList)
            projTree.insert(proj);


        System.out.println("importData");
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
        Map<String, List<FinishedActivity>> result =  projectBuild.lateActivitiesOfTwoProjects(finishedProjectList.get(0), finishedProjectList.get(1));


        // print results
        System.out.println("## lateActivitiesOfTwoProjects Test ##");

        System.out.println("Project Ref : " + finishedProjectList.get(0).getProjectReference() + " Project Delay = " + finishedProjectList.get(0).getDelay());
        System.out.println("Project Ref : " + finishedProjectList.get(1).getProjectReference() + " Project Delay = " + finishedProjectList.get(1).getDelay());

        System.out.println("results:");
        // iterate trhough map
        for (Map.Entry<String, List<FinishedActivity>> entry : result.entrySet()) {
            System.out.println("   prj.Ref = " + entry.getValue().get(0).getProjectReference() +
                    " / " + entry.getKey() + "/" + " activity delay = " + entry.getValue().get(0).getDelay());
        }


    }



}
