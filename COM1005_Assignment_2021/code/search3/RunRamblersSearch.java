
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
    // System.out.println(map1.toString());
    // System.out.println(map1.getLinks("Start"));

    RamblersSearch searcher = new RamblersSearch(map1, new Coords(10, 10));
    SearchState initState = (SearchState) new RamblersState(new Coords(0, 0), 0);

    // change from search1 - specify strategy
    // String res_df = searcher.runSearch(initState, "breadthFirst");
    // System.out.println(res_df);
    // String res_bf = searcher.runSearch(initState, "depthFirst");
    // System.out.println(res_bf);

    String res_bb = searcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);
  }
}
