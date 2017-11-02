package sentinal;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RuntimeComparison {
	private final int MAX_NUMBER = 100000;

	// =================================================
    // Unit Tests
    // =================================================
    
    @Test
    public void hashInsert() {
    	Hashtable<Integer,Integer> hash = new Hashtable<Integer, Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		hash.put(i,i);
    	}
    }
    
    @Test
    public void hashRetrieve() {
    	Hashtable<Integer,Integer> hash = new Hashtable<Integer, Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		hash.put(i,i);
    	}
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		hash.get(i);
    	}
    }
    
    @Test
    public void LinkedListPrependInsert() {
    	LinkedList<Integer> llPrepend = new LinkedList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llPrepend.addFirst(i);
    	}
    }
    
    @Test
    public void LinkedListPrependRevrieve() {
    	LinkedList<Integer> llPrepend = new LinkedList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llPrepend.addFirst(i);
    	}
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llPrepend.get(i);
    	}
    }
    
    @Test
    public void LinkedListAppendInsert() {
    	LinkedList<Integer> llAppend = new LinkedList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llAppend.add(i);
    	}
    }
    
    @Test
    public void LinkedListAppendRetrieve() {
    	LinkedList<Integer> llAppend = new LinkedList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llAppend.add(i);
    	}
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		llAppend.get(i);
    	}
    }
    
    @Test
    public void ArrayListAppendInsert() {
    	ArrayList<Integer> arrAppend = new ArrayList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrAppend.add(i);
    	}
    }
    
    @Test 
    public void ArrayListAppendRetrieve() {
    	ArrayList<Integer> arrAppend = new ArrayList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrAppend.add(i);
    	}
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrAppend.get(i);
    	}	
    }
    
    @Test
    public void ArrayListPrependInsert() {
    	ArrayList<Integer> arrPrepend = new ArrayList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrPrepend.add(0, i);
    	}
    }
    
    @Test 
    public void ArrayListPrependRetrieve() {
    	ArrayList<Integer> arrPrepend = new ArrayList<Integer>();
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrPrepend.add(0, i);
    	}
    	for (Integer i = 0; i < MAX_NUMBER; i++) {
    		arrPrepend.get(i);
    	}
    	
    }
}

/*
 * 
 * See test analysis in RuntimeComparison.txt
 *  
 * */
