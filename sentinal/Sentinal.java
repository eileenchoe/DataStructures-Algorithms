package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private PhraseHash posHash, negHash;

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    Sentinal (String posFile, String negFile) throws FileNotFoundException {
    	posHash = new PhraseHash();
    	negHash = new PhraseHash();
    	loadSentimentFile(posFile, true);
		loadSentimentFile(negFile, false);
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public void loadSentiment (String phrase, boolean positive) {
    	if (positive) {
    		posHash.put(phrase);
    		return;
    	}
    	negHash.put(phrase);
    }
    
    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
    	File file = new File(filename);
    	Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
    		loadSentiment(scanner.nextLine(), positive);	
    	}
    }
    
    public String sentinalyze (String filename) throws FileNotFoundException {
    	int count = 0;
    	File file = new File(filename);
    	Scanner scanner = new Scanner(file);
    	while(scanner.hasNextLine()){
    		String [] line = scanner.nextLine().split(" ");
    		count += sentinalizer(line, this.posHash);
    		count -= sentinalizer(line, this.negHash);
    	}
    	if (count > 0) { return "positive"; }
    	if (count < 0) { return "negative"; }
    	return "neutral";
    }
       
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private int sentinalizer (String [] line, PhraseHash hash) {
    	int count = 0;
    	int longest = hash.longestLength();
    	for (int i = 0; i < longest; i ++) {
    		for (int k = 0; k < line.length - i; k++) {
    			String [] tempCopy = Arrays.copyOfRange(line, k, k + i + 1);
    			String entry = String.join(" ", tempCopy);
    			if (hash.get(entry) != null){
    				count ++;
    			}
    		}
    	}
    	return count;
    }
    
}

