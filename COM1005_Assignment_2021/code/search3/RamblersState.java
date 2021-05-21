/*
*	State in a Ramblers search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersState extends SearchState {

  private Coords coords;
  private int localCost;

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

    int tempY, tempX;
    //This checks each of North, South, East and West, being the following ((0,1), (1,0), (0,-1), (-1,0)). 
    for (int i = -1; i <= 1; i += 2) {
      tempY = coords.gety() + i;
      tempX = coords.getx();
      
      //checks if the coordinates are in range of the terrain, if so the searchstate becomes a successor.
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(new Coords(tempY, tempX), pixels[tempY][tempX]));
      }
      tempY = coords.gety();
      tempX = coords.getx() + i;

      //checks if the coordinates are in range of the terrain, if so the searchstate becomes a successor.
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(new Coords(tempY, tempX), pixels[tempY][tempX]));
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
