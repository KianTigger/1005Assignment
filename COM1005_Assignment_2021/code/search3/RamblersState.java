/*
*	State in a Ramblers search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersState extends SearchState {

  // city for this state
  private String pixel;

  // constructor
  public RamblersState(String cname, int lc) {
    pixel = cname;
    localCost = lc;
  }

  // accessor
  public String getPixel() {
    return pixel;
  }

  // goalPredicate
  public boolean goalPredicate(Search searcher) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    String tar = msearcher.getGoal(); // get target pixel
    return (pixel.compareTo(tar) == 0);
  }

  // getSuccessors
  public ArrayList<SearchState> getSuccessors(Search searcher) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    TerrainMap Ramblers = msearcher.getMap();
    ArrayList links = Ramblers.getWeights(pixel);
    ArrayList<SearchState> succs = new ArrayList<SearchState>();

    for ( l : links) {
      String spixel;
      if (pixel.compareTo(l.getPixel1()) == 0) {
        spixel = l.getPixel2();
      } else {
        spixel = l.getPixel1();
      }
      ;
      succs.add((SearchState) new RamblersState(spixel, l.getCost()));
    }
    return succs;
  }

  // sameState

  public boolean sameState(SearchState s2) {
    RamblersState ms2 = (RamblersState) s2;
    return (pixel.compareTo(ms2.getPixel()) == 0);
  }

  // toString
  public String toString() {
    return ("Ramblers State: " + pixel);
  }

}
