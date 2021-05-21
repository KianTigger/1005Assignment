
/**
  * RunMapSearch.java
  *
  * 
  * Phil Green 2013 version
  * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
  run a map traversal
**/

import java.util.*;

public class RunRamblersBB {

  public static void main(String[] arg) {

    TerrainMap map1 = new TerrainMap("tmc.pgm");
    // TerrainMap map1 = new TerrainMap("diablo.pgm");
    int depthLimit = map1.getDepth();
    int widthLimit = map1.getWidth();
    //the following can be used for batch testing.
    int[][] testingArray = { { 12, 1, 4, 8 }, { 3, 0, 1, 13 }, { 13, 4, 3, 11 }, { 7, 2, 1, 4 }, { 14, 11, 12, 8 },
        { 11, 9, 13, 7 }, { 10, 0, 1, 0 }, { 7, 2, 15, 2 }, { 1, 11, 3, 0 }, { 3, 14, 11, 0 }, { 8, 1, 6, 13 },
        { 9, 9, 1, 13 }, { 10, 9, 5, 1 }, { 14, 13, 13, 14 }, { 4, 4, 4, 14 }, { 2, 11, 11, 9 }, { 1, 2, 8, 1 },
        { 13, 7, 9, 5 }, { 11, 8, 4, 5 }, { 8, 11, 11, 5 }, { 4, 9, 3, 3 }, { 5, 12, 4, 5 }, { 13, 7, 5, 15 },
        { 7, 1, 12, 13 }, { 13, 1, 10, 12 }, { 13, 5, 15, 3 }, { 8, 2, 6, 11 }, { 11, 2, 12, 11 }, { 5, 8, 0, 7 },
        { 0, 15, 12, 4 }, { 2, 9, 9, 11 }, { 14, 8, 2, 1 }, { 15, 3, 9, 2 }, { 0, 2, 6, 0 }, { 15, 14, 11, 10 },
        { 5, 5, 14, 3 }, { 3, 3, 10, 1 }, { 13, 2, 7, 15 }, { 10, 11, 14, 8 }, { 3, 4, 14, 12 }, { 8, 10, 7, 2 },
        { 12, 1, 14, 12 }, { 5, 9, 2, 0 }, { 10, 14, 10, 2 }, { 15, 8, 13, 9 }, { 2, 13, 0, 2 }, { 1, 13, 15, 3 },
        { 15, 9, 14, 2 }, { 3, 14, 14, 14 }, { 3, 14, 2, 2 } };
    System.out.println(map1.getDepth());
    System.out.println(map1.getWidth());
    // System.out.println(map1.toString());
    // System.out.println(map1.getLinks("Start"));

    //Change testing to true to run 50 tests.
    boolean testing = false;
    if (testing == false) {
      RamblersSearch searcher = new RamblersSearch(map1, new Coords(13, 9));
      // RamblersSearch searcher = new RamblersSearch(map1, new
      // Coords(map1.getDepth(), map1.getWidth()));

      int tempx = 1;
      int tempy = 2;
      SearchState initState = (SearchState) new RamblersState(new Coords(tempy, tempx), map1.getTmap()[tempy][tempx]);

      // change from search1 - specify strategy
      // String res_df = searcher.runSearch(initState, "breadthFirst");
      // System.out.println(res_df);
      // String res_bf = searcher.runSearch(initState, "depthFirst");
      // System.out.println(res_bf);

      String res_bb = searcher.runSearch(initState, "branchAndBound");
      System.out.println(res_bb);
    } else {
      int tempxstart, tempystart, tempxend, tempyend;
      int count = 0;
      double totalEfficiency = 0;
      double averageEfficiency = 0; // used for efficiency purposes
      for (int[] tempArray : testingArray) {
        count += 1;
        System.out.println("Count: " + count + " out of: " + testingArray.length);
        tempystart = tempArray[0];
        tempxstart = tempArray[1];
        tempyend = tempArray[2];
        tempxend = tempArray[3];
        RamblersSearch searcher = new RamblersSearch(map1, new Coords(tempystart, tempxstart));
        SearchState initState = (SearchState) new RamblersState(new Coords(tempyend, tempxend),
            map1.getTmap()[tempyend][tempxend]);
        //String res_bb = searcher.runSearch(initState, "aStar");
        //System.out.println(res_bb);
      }
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
    }
  }
}
