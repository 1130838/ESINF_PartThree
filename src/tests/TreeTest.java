/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import TREE.TREE;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ecs
 */
public class TreeTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] bstAscDes = {7, 8, 10, 13, 15, 17, 20, 50, 40, 30};
    TREE<Integer> instance;
        
    public TreeTest() {
    }
    
    @Before
    public void setUp(){
        instance = new TREE();
        for(int i :arr)
            instance.insert(i);        
    }



/**
     * Test of BSTIterator method, of class TREE.
     */
    @Test
    public void testBSTIterator() {
        System.out.println("BSTIterator");
        
        //-----<test next()
        Iterator<Integer> itr = instance.iterator();
        List<Integer> lResult = new ArrayList<Integer>();        
        while(itr.hasNext())
            lResult.add(itr.next());
        assertEquals(Arrays.asList(inorderT), lResult);

        
        //-----<test remove()
        itr = instance.iterator();
        boolean errorDetected=false;
        try{
            itr.remove();
        }catch(IllegalStateException e){
            errorDetected=true;
        }
        assertTrue("cannot call remove() before call next()",errorDetected);

        itr = instance.iterator();
        errorDetected=false;
        itr.next();
        try{
            itr.remove();
        }catch(IllegalStateException e){
            errorDetected=true;
        }
        assertFalse("remove() called after next()", errorDetected);
        assertFalse("after remove() the iterator becomes invalid", itr.hasNext());

    }
    /**
     * Test of autumnTree method, of class TREE.
     */
    @Test
    public void testAutumnTree() {
        System.out.println("autumntree");
        System.out.println("-----------------------------");
        System.out.println(instance);        
        System.out.println("-----------------------------");
        TREE<Integer> newTree = (TREE<Integer>) instance.autumnTree();
        
        Integer[] a = {8,10,15,20,40};
        assertEquals(Arrays.asList(a), newTree.inOrder());
        System.out.println(newTree);
    }    
    /**
     * Test of ascdes method, of class TREE.
     */
    @Test
    public void testAscdes() {
        System.out.println("ascdes");
        assertEquals(Arrays.asList(bstAscDes),instance.ascdes());
    }

    /**
     * Test of iterator method, of class TREE.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator"); 
        Iterator<Integer> itr = instance.iterator();
        int index = 0;
        while(itr.hasNext()){
            assertEquals(inorderT[index], itr.next());
            index++;
        }
    }  
    
}
