package tests;

import model.FinishedProject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bruno.devesa on 04/12/2015.
 */
public class FinishedProjectTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCompareTo_One() throws Exception {

        System.out.println("## compareTo Test with the same delay ##");
        FinishedProject instance = new FinishedProject("P1", "Test1", 5, 6);
        FinishedProject finishedProject = new FinishedProject("P2", "Teste2", 5, 6);
        int expResult = -1;
        int result = instance.compareTo(finishedProject);
        assertEquals(expResult, result);

    }

    @Test
    public void testCompareTo_Two() throws Exception {

        System.out.println("## compareTo Test with different delays ##");
        FinishedProject instance2 = new FinishedProject("P1", "Teste1", 5, 6);
        FinishedProject finishedProject = new FinishedProject("P2", "Teste2", 5, 7);
        int expResult2 = 6 - 7;
        int result2 = instance2.compareTo(finishedProject);
        assertEquals(expResult2, result2);

    }


    @Test
    public void testCompareTo_Three() throws Exception {

        System.out.println("## compareTo Test with different delay ##");
        FinishedProject instance3 = new FinishedProject("P3", "Teste1", 14, 5);
        FinishedProject finishedProject = new FinishedProject("P2", "Teste2", 14, 5);
        int expResult3 = 1;
        int result3 = instance3.compareTo(finishedProject);
        assertEquals(expResult3, result3);

    }


}