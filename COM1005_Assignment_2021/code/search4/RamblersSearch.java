/*
* 	MapSearch.java
*
*	search for map traversal
* Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

//import java.util.*;

public class RamblersSearch extends Search {

  private TerrainMap map; // map we're searching
  private Coords goal; // goal Coords

  //applicable getters
  public TerrainMap getMap() {
    return map;
  }

  public Coords getGoal() {
    return goal;
  }

  public RamblersSearch(TerrainMap m, Coords g) {
    map = m;
    goal = g;
  }
}
