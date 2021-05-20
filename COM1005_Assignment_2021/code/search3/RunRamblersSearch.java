/**
  * RunMapSearch.java
  *
  * 
  * Phil Green 2013 version
  * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
  run a map traversal
**/

import java.util.*;

public class RunRamblersSearch {

  public static void main(String[] arg) {

    TerrainMap map1 = new TerrainMap("tmc.pgm");
    //TerrainMap map1 = new TerrainMap("diablo.pgm");

    System.out.println(map1.getDepth());
    System.out.println(map1.getWidth());
    // System.out.println(map1.toString());
    // System.out.println(map1.getLinks("Start"));

    //RamblersSearch searcher = new RamblersSearch(map1, new Coords(100, 100));
    RamblersSearch searcher = new RamblersSearch(map1, new Coords(map1.getDepth()-1, map1.getWidth()-1));

    int tempx = 4;
    int tempy = 4;
    SearchState initState = (SearchState) new RamblersState(new Coords(tempy, tempx), map1.getTmap()[tempy][tempx]);

    // change from search1 - specify strategy
    // String res_df = searcher.runSearch(initState, "breadthFirst");
    // System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);

    String res_bb = searcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);
  }
}
