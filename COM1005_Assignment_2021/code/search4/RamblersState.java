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
    Coords temp;
    for (int i = -1; i <= 1; i += 2) {
      tempY = coords.gety() + i;
      tempX = coords.getx();
      temp = new Coords(tempY, tempX);
      estCost = getAStarHeuristic(searcher, temp);
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(temp, pixels[tempY][tempX], estCost));
      }

      tempY = coords.gety();
      tempX = coords.getx() + i;
      temp = new Coords(tempY, tempX);
      estCost = getAStarHeuristic(searcher, temp);
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        succs.add((SearchState) new RamblersState(temp, pixels[tempY][tempX], estCost));
      }

    }
    return succs;
  }

  public int getAStarHeuristic(Search searcher, Coords tar){
    RamblersSearch msearcher = (RamblersSearch) searcher;
    //Coords tar = msearcher.getGoal(); // get target pixel
    TerrainMap Ramblers = msearcher.getMap();
    int[][] pixels = Ramblers.getTmap();

    int intToReturn;

    intToReturn = manhattenDistance(tar);
    
    //intToReturn = euclidianDistance(tar);
    
    //intToReturn = heightDifference(tar, pixels);

    //intToReturn = manhattenAndEuclidianDistance(target);

    //intToReturn = manhattenAndHeight(target, pixels);

    //intToReturn = EuclidianAndHeight(target, pixels);

    //intToReturn = manhattenAndEuclidianDistanceAndHeight(target, pixels);
    
    return intToReturn;
  }

  public int manhattenDistance(Coords target){
    return (int) (Math.abs(coords.getx() - target.getx()) + Math.abs(coords.gety() - target.gety()));
  }

  public int euclidianDistance(Coords target){
    return (int) Math.sqrt(Math.pow(coords.getx() - target.getx(), 2) + Math.pow(coords.gety() - target.gety(), 2));
  }

  public int heightDifference(Coords target, int[][] pixels){
    return (int) (Math.abs(this.localCost - pixels[target.gety()][target.getx()]));
  }

  public int manhattenAndEuclidianDistance(Coords target){
    return (int) (manhattenDistance(target) - euclidianDistance(target));
  }

  public int manhattenAndHeight(Coords target, int[][] pixels){
    return (int) (manhattenDistance(target) - heightDifference(target, pixels));
  }

  public int EuclidianAndHeight(Coords target, int[][] pixels){
    return (int) (euclidianDistance(target) - heightDifference(target, pixels));
  }

  public int manhattenAndEuclidianDistanceAndHeight(Coords target, int[][] pixels){
    return (int) (manhattenDistance(target) + euclidianDistance(target) - heightDifference(target, pixels));
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
