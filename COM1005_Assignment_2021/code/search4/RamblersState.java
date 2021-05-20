package code.search4;
/*
*	State in a Ramblers search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersState extends SearchState {

  private Coords coords;
  private int localCost;
  private int estimatedCost;

  // constructor
  public RamblersState(Coords coords, int lc, int ec) {
    this.coords = coords;
    this.localCost = lc;
    this.estimatedCost = ec;
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

    int tempY, tempX, estCost;
    for (int i = -1; i <= 1; i += 2) {
      tempY = coords.gety() + i;
      tempX = coords.getx();
      //estCost
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(new Coords(tempY, tempX), pixels[tempY][tempX], 0));
      }

      tempY = coords.gety();
      tempX = coords.getx() + i;

      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(new Coords(tempY, tempX), pixels[tempY][tempX], 0));
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
