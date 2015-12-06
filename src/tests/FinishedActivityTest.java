package tests;

import model.FinishedActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bruno.devesa on 04/12/2015.
 */
public class FinishedActivityTest {

    FinishedActivity a1;
    FinishedActivity a2;
    FinishedActivity a3;
    FinishedActivity a4;
    FinishedActivity a5;

    @Before
    public void setUp() throws Exception {

        a1 = new FinishedActivity("P1", "A1", "anyActivity", 14, 2);
        a2 = new FinishedActivity("P2", "A1", "anyActivity", 14, 2);
        a3 = new FinishedActivity("P1", "A2", "anyActivity", 14, 2);
        a4 = new FinishedActivity("P1", "A1", "anyActivity", 14, 1);
        a5 = new FinishedActivity("P2", "A1", "anyActivity", 14, 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    /**
     * This test pretends to compare 2 values of delay
     */
    public void testCompareTo() throws Exception {

        System.out.println("compareTo with same delay and activity code, different project");

        assertTrue(a1.compareTo(a2)<0);


        System.out.println("compareTo with same delay and project, different activity");
        assertTrue(a1.compareTo(a3)<0);


        System.out.println("compareTo with same activity and project, diferent delay");
        assertTrue(a1.compareTo(a4)>0);

                 // delay a1>a5, and proj code a1<a5. result should be >0 because it orders by delay first.

        System.out.println("compareTo same activity, diferent delay and project");
        assertTrue(a1.compareTo(a5)>0);


    }
}