package pers.wl;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.util.*;

import wl.controller.Wordladder;
/** 
* Wordladder Tester. 
* 
* @author <Authors name> 
* @since <pre>03/10/2018</pre> 
* @version 1.0 
*/ 
public class WordladderTest {
    static private Wordladder wl = new Wordladder();

@Before
public void before() throws Exception {
    wl.clear();
    assertTrue(Wordladder.word1.equals(""));
    assertTrue(Wordladder.word2.equals(""));
    System.out.println("before");
} 

@After
public void after() throws Exception {
    System.out.println("after");
} 

/** 
* 
* Method: neighbor(String w, String w2, Stack<String>curs) 
* 
*/ 
@Test
public void testNeighbor() throws Exception { 
//TODO: Test goes here...
    System.out.println("testNeighbor");
    String x = "data";
    String y = "date";
    Stack<String>s = new Stack<String>();
    s.add(y);
    assertTrue(s.peek().equals("date"));
    assertNotNull(s);
} 

/** 
* 
* Method: main(String args[]) 
* 
*/ 
@Test
public void testMain() throws Exception {
    System.out.println("main");
    assertSame(wl.word1,wl.word2);
//TODO: Test goes here... 
} 


/** 
* 
* Method: ladder_to_word(String word2, Stack<String>cur_stack) 
* 
*/ 
@Test
public void testLadder_to_word() throws Exception { 
//TODO: Test goes here...
    System.out.println("Ladder_to_word");
    assertNotSame(wl.used_wordSet,wl.word2);
/*
try { 
   Method method = Wordladder.getClass().getMethod("ladder_to_word", String.class, Stack<String>cur_stack.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} */

} 

/** 
* 
* Method: printLadder(String word1, String word2, Stack<String>cur_stack) 
* 
*/ 
@Test
public void testPrintLadder() throws Exception { 
//TODO: Test goes here...
    System.out.println("print");
/*
try { 
   Method method = Wordladder.getClass().getMethod("printLadder", String.class, String.class, Stack<String>cur_stack.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: searchLadder(String w1, String w2) 
* 
*/

@Test
public void testSearchLadder() throws Exception { 
//TODO: Test goes here...
    System.out.println("searchLadder");
    String a  = "data";
    String b = "code";
    wl.searchLadder("data","code");
/* 
try { 
   Method method = Wordladder.getClass().getMethod("searchLadder", String.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
