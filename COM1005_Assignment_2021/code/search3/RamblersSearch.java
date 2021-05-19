/*
* 	MapSearch.java
*
*	search for map traversal
* Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersSearch extends Search {

  private TerrainMap map; // map we're searching
  private String goal; // goal city

  public TerrainMap getMap() {
    return map;
  }

  public String getGoal() {
    return goal;
  }

  public RamblersSearch(TerrainMap m, String g) {
    map = m;
    goal = g;
  }
}
