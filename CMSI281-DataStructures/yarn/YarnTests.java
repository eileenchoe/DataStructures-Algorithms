package yarn;

import static org.junit.Assert.*;

import org.junit.Test;

public class YarnTests {

    @Test
    public void testYarn() {
        Yarn ball = new Yarn();
    }

    @Test
    public void testIsEmpty() {									
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());
        ball.remove("not_empty");
        //Tests if Yarn is empty after removing its only Entry
        assertTrue(ball.isEmpty());
    }

    @Test
    public void testGetSize() {
        Yarn ball = new Yarn();
        //Checks if initial size is 0
        assertEquals(ball.getSize(), 0);
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        ball.removeAll("unique");
        assertEquals(ball.getSize(),0);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        //Checks if initial UniqueSize is 0
        assertEquals(ball.getUniqueSize(), 0);
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);
        ball.insert("another");
        ball.insert("another");
        //tests adding another unique word
        assertEquals(ball.getUniqueSize(), 3);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        //Insert works on the empty string
        ball.insert("");
        assertTrue(ball.contains(""));
        //Try to add the 101th Entry, returns false. 
        String test = "test";
        while (ball.getUniqueSize() < 100) {
        	test = test + "+";
        	ball.insert(test);
        }
        assertFalse(ball.insert("too many"));
    }

    @Test
    public void testRemove() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
        //Remove something that doens't exist in the yarn
        assertEquals(ball.remove("random"), 0);
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(),1);
    }

    @Test
    public void testRemoveAll() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        //Tests removes all on string it doesn't contain
        ball.removeAll("");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);
    }

    @Test
    public void testCount() {
        Yarn ball = new Yarn();
        assertEquals(ball.count("nothing"),0);
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);
        //Tests the empty string
        assertEquals(ball.count(""),0);
        //Test count after Entry removal
        ball.removeAll("dup");
        assertEquals(ball.count("dup"),0);
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
        //test empty string
        assertFalse(ball.contains(""));
        ball.removeAll("dup");
        //Tests contains after Entry removal
        assertFalse(ball.contains("dup"));
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = ball.clone();
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }
        //comparison Yarn must be empty 
        assertTrue(comparison.isEmpty());
        
        //testGetNth on an empty Yarn
        Yarn ball2 = new Yarn();
        Yarn comparison2 = ball2.clone();
        for (int i = 0; i < ball2.getSize(); i++) {
            String gotten = ball2.getNth(i);
            assertTrue(comparison2.contains(gotten));
            comparison2.remove(gotten);
        }
        assertTrue(comparison2.isEmpty()); 
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getMostCommon(), "dup");
        ball.insert("unique");
        /*
         * A tie should be either/or
         * assertEquals(ball.getMostCommon(), "dup");
         * assertEquals(ball.getMostCommon(), "unique");
         * but, it should return "dup"
         * 
         */
        assertEquals(ball.getMostCommon(), "dup");
        //Tests if getMostCommon() returns null for empty Yarn
        Yarn ball2 = new Yarn();
        assertEquals(ball2.getMostCommon(), null);
        }

    @Test
    public void testClone() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = ball.clone();
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
        //Tests if modifications to the original affect the clone
        ball.removeAll("dup");
        assertTrue(dolly.contains("dup"));
        dolly.removeAll("dup");
        assertFalse(dolly.contains("dup"));
    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        //the switch carries over multiples of a word
        assertEquals(y2.count("dup"), 2);
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        //testKnit() on empty yarns
        Yarn y4 = new Yarn();
        Yarn y5 = new Yarn();
        Yarn y6 = Yarn.knit(y4, y5);
        assertTrue(y6.isEmpty());
        y6.insert("added");
        y6.insert("added");
        assertEquals(y6.getUniqueSize(), 1);
        assertEquals(y6.getSize(),2);
    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
        //testTear() on empty yarns
        Yarn y4 = new Yarn();
        Yarn y5 = new Yarn();
        Yarn y6 = Yarn.tear(y4, y5);
        assertTrue(y6.isEmpty());
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));
        //2 empty yarns should be equal
        Yarn y3 = new Yarn();
        Yarn y4 = new Yarn();
        assertTrue(Yarn.sameYarn(y3,y4));
        //adding an Entry to an empty yarn => not the same Yarn
        y3.insert("another");
        assertFalse(Yarn.sameYarn(y3, y4));
    }
}
