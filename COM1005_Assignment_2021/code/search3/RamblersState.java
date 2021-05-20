/*
*	State in a Ramblers search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersState extends SearchState {

  private Coords coords;
  private int localCost;
  private int estRemCost;
  

  // constructor
  public RamblersState(Coords coords, int lc) {
    this.coords = coords;
    this.localCost = lc;
  }

  // accessor
  public Coords getCoords() {
    return coords;
  }

  // goalPredicate
  public boolean goalPredicate(Search searcher) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    Coords tar = msearcher.getGoal(); // get target pixel
    return (coords.getx() == tar.getx() && coords.gety() == tar.gety());
  }

  // getSuccessors
  public ArrayList<SearchState> getSuccessors(Search searcher) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    TerrainMap Ramblers = msearcher.getMap();
    int[][] pixels = Ramblers.getTmap();
    ArrayList<SearchState> succs = new ArrayList<SearchState>();

    for (int i = -1; i <= 1; i+=2) {
      for (int j = -1; j <= 1; j+=2) {
        succs.add((SearchState) new RamblersState(new Coords(coords.gety()+i, coords.getx()+j), pixels[coords.gety()+i][coords.getx()+j]));
      }
    }
    return succs;
  }

  // sameState

  public boolean sameState(SearchState s2) {
    RamblersState ms2 = (RamblersState) s2;
    return (coords.getx() == ms2.coords.getx() && coords.gety() == ms2.coords.gety());
  }

  // toString
  public String toString() {
    return ("Ramblers State: " + coords);
  }

}
