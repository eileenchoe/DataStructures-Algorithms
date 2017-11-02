import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MapColorer {

    public void colorMap() {
        Region[] regions = Region.getAllRegionsAsArray();
        boolean b = testCombo(0,0);   
        if(regions[1].getColor() == null) {
            System.out.println("IMPOSSIBLE MAP");
        } else {
            printMap();
        }  
    }

    private static boolean testCombo(int index, int color) {
        Region[] regions = Region.getAllRegionsAsArray();
        if (index >= regions.length) {
            return true;
        }
        if (regions[index].canColorWith(color)) {
            regions[index].setColor(color);
            for (int i = 0; i <= 3; i ++) {
                if (testCombo(index + 1, i)) {
                    return true;
                }
            }
        } else {
            regions[index].removeColor();
        }
        return false;
   }

   public void readMapFromStandardInput() {
       new BufferedReader(new InputStreamReader(System.in))
           .lines()
           .forEach(line -> {
               String[] pair = line.trim().split(",");
               Region region0 = Region.forName(pair[0]);
               Region region1 = Region.forName(pair[1]);
               region0.addNeighbor(region1);
           });
   }

    private static void printMap() {
        //Arrays.stream(Region.getAllRegionsAsArray()).forEach(System.out::println);
        for(Region r : Region.getAllRegionsAsArray()) {
            System.out.println(r + ":" + r.getColor());
            for (Region n : r.getNeighbors()) {
                System.out.println(r + "," + n.getName());
            }
        }
    }

   public static void main(String[] args) {
       MapColorer mapColorer = new MapColorer();
       mapColorer.readMapFromStandardInput();
       mapColorer.colorMap();
   }
}

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

   private static Map<String, Region> allRegions = new TreeMap<>();

   public static Region forName(String name) {
       allRegions.putIfAbsent(name, new Region(name));
       return allRegions.get(name);
   }

   public static Region[] getAllRegionsAsArray() {
       return allRegions.values().toArray(new Region[allRegions.size()]);
   }

   private String name;
   private Integer color;
   private Set<Region> neighbors = new HashSet<>();

   private Region(String name) {
       this.name = name;
   }

   public String getName() {
       return name;
   }

   public String toString() {
       return this.name;
   }

   public Integer getColor() {
       return color;
   }

   public boolean canColorWith(int color) {
       return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
   }

   public void setColor(int color) {
       this.color = color;
   }

   public void removeColor() {
       this.color = null;
   }

   public Set<Region> getNeighbors() {
       return neighbors;
   }

   public void addNeighbor(Region region) {
       Objects.requireNonNull(region);
       neighbors.add(region);
       region.neighbors.add(this);
   }
}