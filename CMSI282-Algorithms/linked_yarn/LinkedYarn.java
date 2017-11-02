package linked_yarn;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    LinkedYarn () {
    	head = null;
    	size = 0;
    	uniqueSize = 0;
    	modCount = 0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return head == null; 
    }
    
    public int getSize () {
    	return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public int getModCount() {
    	return modCount;
    }
    
    public void insert (String toAdd) {
    	insertOccurrences(toAdd, 1);
    }
    
    public int remove (String toRemove) {
    	if (!contains(toRemove)) { return 0; }
        // >> [AF] Glad someone looked at the last solution :)
    	return removeOccurences (toRemove, 1);
    }
    
    public void removeAll (String toNuke) {
    	if (!contains(toNuke)) { return; }
    	removeOccurences(toNuke, count(toNuke));
    }
    
    public int count (String toCount) {
    	if(!contains(toCount)) {return 0; }
    	return getNode(toCount).count;
    }
    
    public boolean contains (String toCheck) {
    	for (Node it = head; it != null; it = it.next) {
    		if (it.text == toCheck) { return true; }
        // >> [AF] Place return on its own line
    	} return false;
    }
    
    public String getMostCommon () {
    	if (isEmpty()) { return null; }
    	int count = 0;
    	String mostCommon = null;
        for (Node it = head; it != null; it=it.next) {
        	if (it.count > count) {
        		count = it.count;
        		mostCommon = it.text;
        	}
        }
    	return mostCommon;
    }
    
    public LinkedYarn clone () {
    	LinkedYarn theNew = new LinkedYarn();
    	for (Node it = head; it != null; it = it.next) {
    		theNew.insertOccurrences(it.text, it.count);
    	}
        return theNew;
    }
    
    public void swap (LinkedYarn other) {
    	Node headCopy = other.head;
    	int sizeCopy = other.size; 
    	int uniqueSizeCopy = other.uniqueSize;
    	int modCountCopy = other.modCount;
    	
    	other.head = this.head;
    	other.size = this.size;
    	other.uniqueSize = this.uniqueSize;
    	other.modCount = this.modCount + 1;
    	
    	this.head = headCopy;
    	this.size = sizeCopy;
    	this.uniqueSize = uniqueSizeCopy;
    	this.modCount = modCountCopy + 1;
    }
    
    public LinkedYarn.Iterator getIterator () {
    	if (isEmpty()) { throw new IllegalStateException() ;}
    	return new Iterator(this);
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn knitted = y1.clone();
        for (Node it = y2.head; it != null; it = it.next) {
        	knitted.insertOccurrences(it.text, it.count);
        }
        return knitted;
    }
    
    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn torn = y1.clone();
        for (Node it = y2.head; it != null; it = it.next) {
        	if (torn.contains(it.text)) {
        		torn.removeOccurences(it.text, it.count);
        	}
        }
        return torn;
    }
    
    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
    	LinkedYarn tear1 = tear(y1, y2);
    	LinkedYarn tear2 = tear(y2, y1);
    	return tear1.isEmpty() && tear2.isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    
    private Node getNode (String toFind) {
    	for (Node it = head; it != null; it = it.next) {
    		if (it.text == toFind) { return it; }
    	}
		return null;
    }
    
    private Node lastNode () {
    	for (Node it = head; it != null; it = it.next) {
    		if (it.next == null) { return it; }
    	} return null;
    }
    
    private void insertOccurrences (String text, int count) {
    	//Insert at the beginning
    	if (isEmpty()) {
    		head = new Node(text, count); 
    		uniqueSize ++;
    	}
    	//Insert an existing String
    	else if (contains(text)) {
    		getNode(text).count += count;
    	}
    	//Insert a new String (at the end)
    	else { 
    		Node oldLast = lastNode();
    		oldLast.next = new Node (text, count);
    		lastNode().prev = oldLast;
    		uniqueSize ++;
    	}
    	size += count;
    	modCount += count;
    }
    
    private int removeOccurences (String text, int count) {
    	Node removal = getNode(text);
    	
        // >> [AF] Comments should leave a space between the // and text
        //not like this
    	//Remove all case (NUKE)
    	if (removal.count - count < 1) {
    		size -= count;
        	modCount += count;
        	uniqueSize --;
        	if (removal == head) {
        		//update the new head
        		head = removal.next;
        		//there was only one node to begin with
        		if (head == null) { return -1; }
        		head.prev = null; 
        	} else if (removal.next == null) {
        		//at the end
        		removal.prev.next = null; 
        		//removal.prev = null;
        	}
        	//in the middle
        	else { 
        		removal.prev.next = removal.next; 
        		removal.next.prev = removal.prev;
        	}
        	return 0;
    	} else {
	    	//Remove, but don't nuke
	    	removal.count -= count;
	    	size -= count;
	    	modCount += count;
	    	return removal.count;
    	}
    }

    
    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        int innerCount;
        
        Iterator (LinkedYarn y) {
            current = y.head;
            owner = y;
            itModCount = y.modCount; 
            innerCount = 1; 
        }
        
        public boolean hasNext () {
        	if (innerCount < current.count) { return true;} 
        	return current.next != null;
        }
        
        public boolean hasPrev () {
        	if (innerCount > 1 ) {return true; }
        	return current != null && current.prev != null;
        }
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        public String getString () {
            if (!isValid ()) { return null; }
            return current.text;
        }

        public void next () {
            // >> [AF] Can delegate validity check to helper method
            // (see repeated first lines of this and prev())
            if (!isValid()) { throw new IllegalStateException(); }
            if (!hasNext()) { throw new NoSuchElementException(); }
            if (innerCount < current.count) {
            	innerCount ++;
            } else {
            	current = current.next;
            	innerCount = 1;
            }
        }
        
        public void prev () {
        	if (!isValid()) { throw new IllegalStateException(); }
        	if (!hasPrev()) { throw new NoSuchElementException(); }
        	if(innerCount > 1) {
        		innerCount--;
        	} else {
        		current = current.prev;
        		innerCount = current.count;
        	}
        }
        
        public void replaceAll (String toReplaceWith) {
        	if (!isValid()) { throw new IllegalStateException(); }
        	current.text = toReplaceWith;
        	itModCount ++;
        	owner.modCount ++;	
        }
        
    }
    
    class Node {
        Node next, prev;
        String text;
        int count;
        
        Node (String t, int c) {
            text = t;
            count = c;
        }
    }
}

    