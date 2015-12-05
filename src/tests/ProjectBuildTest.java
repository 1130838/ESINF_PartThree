package tests;

import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    public void testlateAtivitiesOf2Projects() throws Exception {



    }



}
