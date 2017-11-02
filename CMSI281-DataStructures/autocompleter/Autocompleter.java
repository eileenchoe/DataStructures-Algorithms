package autocompleter;

import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {
	
	// >> [AF] Remove test main methods before submitting!
    // This would cause lots of issues if a user tried to incorporate
    // your Autocompleter into their programs (-3)
	public static void main (String[] args) {
		Autocompleter ac = new Autocompleter();
    	ac.addTerm("item");
    	ac.addTerm("dog");
    	ac.addTerm("app");
    	ac.addTerm("b");
    	// System.out.println(ac.root.left.left.right.letter);
    	// System.out.println(ac.root.left.left.right.wordEnd);
	}

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty () {
        return root == null;
    }
    
    public void addTerm (String toAdd) {
    	toAdd = normalizeTerm(toAdd);
    	if (root == null) {
    		root = new TTNode (toAdd.charAt(0), false);
    	}
    	addLetter(root, 0, toAdd);
    }
    
    private void addLetter (TTNode current, int i, String toAdd) {
    	if (i == toAdd.length() - 1 && current.letter == toAdd.charAt(i)) { 
	    	current.wordEnd = true;
	    	return; 
    	}
    	int comparison = compareChars(current.letter, toAdd.charAt(i));
    	if (comparison == 0) {
            // >> [AF] Can simplify logic of if (current.x == null) {...} with some clever
            // recursion (see solution)
    		if (current.mid == null) {
    			current.mid = new TTNode(toAdd.charAt(i + 1), i + 1 == toAdd.length() - 1 ? true : false);
    		}
    		current = current.mid;
    		addLetter (current, i + 1, toAdd);
    	} else if (comparison < 0) {
    		if (current.right == null) {
    			current.right = new TTNode(toAdd.charAt(i), i == toAdd.length() - 1 ? true : false);
    		}
    		addLetter (current.right, i, toAdd);
    	} else if (comparison > 0) {
    		if (current.left == null) {
    			current.left = new TTNode(toAdd.charAt(i), i == toAdd.length() - 1 ? true : false);
    		}
    		addLetter (current.left, i, toAdd);
    	}
    }
    
    public boolean hasTerm (String query) {
    	query = normalizeTerm(query);
        if (root == null) {
        	return false;
        }
        return hasTerm(query, true);
    }
    
    public String getSuggestedTerm (String query) {
        // >> [AF] Comments should be self-contained and not reference
        // the spec (e.g., "reach a star" below)
    	// fast forward to the node of the last letter of the query. 
    	// build string until you reach a star
    	// append prefix to suffix
    	if (!this.hasTerm(query, false)) {return null;}
    	TTNode last = returnLastNode(query);
    	String suffix = "";
    	while (!last.wordEnd) {
    		if (last.mid != null) {
    			last = last.mid;
    		} else if (last.left != null) {
    			last = last.left;
    		} else if (last.right != null) {
    			last = last.right;
    		}
    		suffix = suffix + last.letter;
    	}
    	return query + suffix;
    }
    
    public ArrayList<String> getSortedTerms () {
        ArrayList<String> answer = new ArrayList<String>();
        answer = sortedTermsHelper(this.root, "", answer);
        return answer;
    }
    
    // >> [AF] Hmm, methinks there were some helper methods above too! :P
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    private boolean hasTerm (String query, boolean requireWordEnd) {
    	int index = 0;
    	TTNode current = root;
    	while (index <= query.length() - 1 && current != null) {
    		int comparison = compareChars(current.letter, query.charAt(index));
    		if (comparison == 0) {
                // >> [AF] A bit sloppy to have a boolean condition return true or false; why not:
                // boolean flag = !(requireWordEnd && !current.wordEnd)
    			boolean flag = (requireWordEnd && !current.wordEnd) ? false : true;
    			if (index == query.length() - 1 && flag) { return true; }
    			index += 1;
    			current = current.mid;
    		} else {
    			if (comparison > 0) {
    				current = current.left;
    			} else {
    				current = current.right;
    			}
    		}
    	}
    	return false;
    }
    
    private TTNode returnLastNode (String query) {
        // >> [AF] Probably means you could abstract some common functionalities into a helper:
    	// same as hasTerm (requireWordEnd = false), but returns the last Node
    	int index = 0;
    	TTNode current = root;
    	while (index <= query.length() - 1 && current != null) {
    		int comparison = compareChars(current.letter, query.charAt(index));
    		if (comparison == 0) {
    			if (index == query.length() - 1) {
    				return current;
    			}
    			index += 1; // >> [AF] index++;
    			current = current.mid;
    		} else {
    			if (comparison > 0) {
    				current = current.left;
    			} else {
    				current = current.right;
    			}
    		}
    	}
    	return null;
    }
    
    private ArrayList<String> sortedTermsHelper (TTNode current, String answer, ArrayList<String> arr) {
    	if (current == null) { return arr;}
    	sortedTermsHelper(current.left, answer, arr);
    	String added = answer + current.letter;
    	if (current.wordEnd) {arr.add(added);}
    	sortedTermsHelper(current.mid, added, arr);
    	sortedTermsHelper(current.right, answer, arr);
    	return arr;
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }        
    }   
}