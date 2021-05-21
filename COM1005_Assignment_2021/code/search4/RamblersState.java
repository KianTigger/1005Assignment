/*
*	State in a Ramblers search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;

public class RamblersState extends SearchState {

  private Coords coords;
  //private int localCost;
  //private int estimatedCost;

  // constructor
  public RamblersState(Coords coords, int lc, int ec) {
    this.coords = coords;
    this.localCost = lc;
    this.estRemCost = ec;
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

  public Coords getGoal(Search searcher) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    return msearcher.getGoal();
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

      //checks if the coordinates are in range of the terrain, if so the searchstate becomes a successor.
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        temp = new Coords(tempY, tempX);
        estCost = getAStarHeuristic(searcher, temp);
        succs.add((SearchState) new RamblersState(temp, pixels[tempY][tempX], estCost));
      }

      tempY = coords.gety();
      tempX = coords.getx() + i;

      //checks if the coordinates are in range of the terrain, if so the searchstate becomes a successor.
      if ((-1 < tempY && tempY < Ramblers.getDepth()) && (-1 < tempX && tempX < Ramblers.getWidth())) {
        temp = new Coords(tempY, tempX);
        estCost = getAStarHeuristic(searcher, temp);
        succs.add((SearchState) new RamblersState(temp, pixels[tempY][tempX], estCost));
      }

    }
    return succs;
  }


  //Allows for the change of multiple heuristics by uncommenting and commenting code for testing.
  public int getAStarHeuristic(Search searcher, Coords current) {
    RamblersSearch msearcher = (RamblersSearch) searcher;
    // Coords tar = msearcher.getGoal(); // get target pixel
    TerrainMap Ramblers = msearcher.getMap();
    int[][] pixels = Ramblers.getTmap();
    Coords goal = getGoal(searcher);
    int intToReturn;

    // intToReturn = manhattanDistance(current, goal);
    // intToReturn = euclideanDistance(current, goal);
    // intToReturn = heightDifference(current, goal, pixels);
    // intToReturn = manhattanAndEuclidianDistance(current, goal);
    // intToReturn = manhattanAndHeight(current, goal, pixels);
    // intToReturn = EuclidianAndHeight(current, goal, pixels);
    intToReturn = manhattanAndEuclidianDistanceAndHeight(current, goal, pixels);

    return intToReturn;
  }

  //The following functions return the applicable heuristics.
  public int manhattanDistance(Coords target, Coords goal) {
    return (int) (Math.abs(goal.getx() - target.getx()) + Math.abs(goal.gety() - target.gety()));
  }

  public int euclideanDistance(Coords target, Coords goal) {
    return (int) Math.sqrt(Math.pow(goal.getx() - target.getx(), 2) + Math.pow(goal.gety() - target.gety(), 2));
  }

  public int heightDifference(Coords target, Coords goal, int[][] pixels) {
    return (int) (Math.abs(pixels[goal.gety()][goal.getx()]) - pixels[target.gety()][target.getx()]);
  }

  public int manhattanAndEuclidianDistance(Coords target, Coords goal) {
    return (int) (manhattanDistance(target, goal) - euclideanDistance(target, goal));
  }

  public int manhattanAndHeight(Coords target, Coords goal, int[][] pixels) {
    return (int) (manhattanDistance(target, goal) - heightDifference(target, goal, pixels));
  }

  public int EuclidianAndHeight(Coords target, Coords goal, int[][] pixels) {
    return (int) (euclideanDistance(target, goal) - heightDifference(target, goal, pixels));
  }

  public int manhattanAndEuclidianDistanceAndHeight(Coords target, Coords goal, int[][] pixels) {
    return (int) (manhattanDistance(target, goal) + euclideanDistance(target, goal)
        - heightDifference(target, goal, pixels));
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
