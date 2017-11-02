package yarn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Yarn implements YarnInterface {
	
	public static void main (String[] args) {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn ball2 = new Yarn();
        ball.insert("hi");
        ball.insert("cool");
        Yarn ball3 = knit(ball, ball2);
        System.out.println(ball3.toString());
	}

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Entry[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Yarn () {
        items = new Entry[MAX_SIZE];
        size = 0;
        uniqueSize = 0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
    	return size == 0;
    }
    
    public int getSize () {
    	return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public boolean insert (String toAdd) {
    	if ( uniqueSize == MAX_SIZE ) {
    		return false;
    	} else if (this.contains(toAdd)) {
    		int i = this.findIndex(toAdd);
    		items[i].count = items[i].count +1;
        	size ++;
        	return true;
    	}
    	items[uniqueSize] = new Entry(toAdd, 1);
		uniqueSize ++;
		size ++;
    	return true;
    }
    
    public int remove (String toRemove) {
    	if(!this.contains(toRemove)) {
    		return 0;
    	} 
    	int i = this.findIndex(toRemove);
    	int c = items[i].count;
    	if (c > 1 ) {
    		items[i].count = c-1;
    		size --;
    		return c -1;
    	}
    	this.removeIndex(i);
    	size --;
    	return 0;
    }
    
    public void removeAll (String toNuke) {
    	if (this.contains(toNuke)) {
	        int i = this.findIndex(toNuke);
	        int amount = items[i].count;
	        this.removeIndex(i);
	        size -=  amount;
    	}
    }
    
    public int count (String toCount) {
    	return this.contains(toCount) ? items[this.findIndex(toCount)].count : 0;
    }
    
    public boolean contains (String toCheck) {
    	int i = this.findIndex(toCheck);
    	return i >= 0;
    }
    
    public String getNth (int n) {
    	if (n > size) {
    		throw new ArrayIndexOutOfBoundsException();
    	}
    	int counter = 0;
    	for (int i = 0; i < uniqueSize; i ++ ) {
    		for (int j = 0; j < items[i].count; j++) {
    			if (counter == n) {
    				return items[i].text;
    			}
    			counter ++;
    		}
    	}
    	return "";
    }
    
    public String getMostCommon () {
    	if ( size == 0 ) { return null; }
    	int largestIndex = 0;
    	for (int i = 1; i < uniqueSize; i++) {
    		largestIndex =  (items[i].count > items[largestIndex].count) ? i : largestIndex;
    	}
    	return items[largestIndex].text;
    }
    
    public Yarn clone () {
    	Yarn duplicateTrial = new Yarn();
    	for (int i = 0; i < size; i++) {
    		duplicateTrial.insert(this.getNth(i));
    	}
    	return duplicateTrial;
    }
    
    public void swap (Yarn other) {
    	int copySize = this.size;
    	int copyUniqueSize = this.uniqueSize;
    	Entry [] copyItems = this.items;
    	this.size = other.size;
    	this.uniqueSize = other.uniqueSize;
    	this.items =other.items;
    	other.size = copySize;
    	other.uniqueSize = copyUniqueSize;
    	other.items = copyItems;
    }
    
    public String toString() {
    	String output = "{ ";
    	for (int i = 0; i < uniqueSize; i ++) {
    		output += items[i].text + ": " + items[i].count + ", ";
    	}
    	output += " }";
    	return output;
    }
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static Yarn knit (Yarn y1, Yarn y2) {
    	Yarn knitted = y1.clone();
    	for (int i = 0; i < y2.size; i++) {
    		knitted.insert(y2.getNth(i));
    	}
    	return knitted;
    }
    
    public static Yarn tear (Yarn y1, Yarn y2) {
    	Yarn torn = y1.clone();
    	for (int i = 0; i < y2.uniqueSize; i++) {
    		if (torn.contains(y2.items[i].text)) {
    			torn.remove(y2.items[i].text);
    		}
    	}
    	return torn;
    }
    
    public static boolean sameYarn (Yarn y1, Yarn y2) {
    	if (y1.uniqueSize != y2.uniqueSize && y1.size != y2.size) {
    		return false;
    	}
    	Yarn comparison = y1.clone(); 
    	for (int i = 0; i < y2.size; i++) {
        	String test = y2.getNth(i);
        	if (comparison.contains(test)) {
        		comparison.remove(test);
        	}
        }
    	return comparison.isEmpty();
    }
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    // Add your own here!
    
    private int findIndex (String toCheck) {
    	for (int i = 0; i < uniqueSize; i++) {
    		if (items[i].text.equals(toCheck)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    private void removeIndex(int indexToRemove) {
    	items[indexToRemove] = items[uniqueSize - 1];
    	uniqueSize --;
    }
}

class Entry {
	String text;
    int count;
    
    Entry (String s, int c) {
        text = s;
        count = c;
    }
}