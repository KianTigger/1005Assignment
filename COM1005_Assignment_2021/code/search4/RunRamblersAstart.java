
/**
  * RunMapSearch.java
  *
  * 
  * Phil Green 2013 version
  * Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
  run a map traversal
**/

import java.util.*;

public class RunRamblersAstart {

  public static void main(String[] arg) {

    //TerrainMap map1 = new TerrainMap("tmc.pgm");
    TerrainMap map1 = new TerrainMap("diablo.pgm");
    System.out.println(map1.getDepth());
    System.out.println(map1.getWidth());
    int[][] tmctestingArray = { { 12, 1, 4, 8 }, { 3, 0, 1, 13 }, { 13, 4, 3, 11 }, { 7, 2, 1, 4 }, { 14, 11, 12, 8 },
        { 11, 9, 13, 7 }, { 10, 0, 1, 0 }, { 7, 2, 15, 2 }, { 1, 11, 3, 0 }, { 3, 14, 11, 0 }, { 8, 1, 6, 13 },
        { 9, 9, 1, 13 }, { 10, 9, 5, 1 }, { 14, 13, 13, 14 }, { 4, 4, 4, 14 }, { 2, 11, 11, 9 }, { 1, 2, 8, 1 },
        { 13, 7, 9, 5 }, { 11, 8, 4, 5 }, { 8, 11, 11, 5 }, { 4, 9, 3, 3 }, { 5, 12, 4, 5 }, { 13, 7, 5, 15 },
        { 7, 1, 12, 13 }, { 13, 1, 10, 12 }, { 13, 5, 15, 3 }, { 8, 2, 6, 11 }, { 11, 2, 12, 11 }, { 5, 8, 0, 7 },
        { 0, 15, 12, 4 }, { 2, 9, 9, 11 }, { 14, 8, 2, 1 }, { 15, 3, 9, 2 }, { 0, 2, 6, 0 }, { 15, 14, 11, 10 },
        { 5, 5, 14, 3 }, { 3, 3, 10, 1 }, { 13, 2, 7, 15 }, { 10, 11, 14, 8 }, { 3, 4, 14, 12 }, { 8, 10, 7, 2 },
        { 12, 1, 14, 12 }, { 5, 9, 2, 0 }, { 10, 14, 10, 2 }, { 15, 8, 13, 9 }, { 2, 13, 0, 2 }, { 1, 13, 15, 3 },
        { 15, 9, 14, 2 }, { 3, 14, 14, 14 }, { 3, 14, 2, 2 } };
    int[][] diablotestingArray = { { 230, 251, 239, 86 }, { 246, 164, 167, 183 }, { 2, 136, 128, 45 }, { 151, 202, 64, 147 },
        { 129, 45, 61, 73 }, { 157, 39, 230, 14 }, { 229, 157, 129, 32 }, { 239, 46, 219, 69 }, { 239, 142, 231, 59 },
        { 242, 94, 212, 24 }, { 248, 116, 242, 134 }, { 48, 121, 120, 18 }, { 155, 215, 40, 94 }, { 194, 84, 36, 139 },
        { 122, 173, 125, 124 }, { 100, 133, 132, 30 }, { 130, 89, 151, 89 }, { 37, 81, 207, 197 }, { 68, 100, 187, 65 },
        { 79, 152, 88, 203 }, { 70, 162, 177, 192 }, { 195, 154, 78, 32 }, { 126, 134, 248, 78 }, { 149, 42, 211, 47 },
        { 29, 106, 26, 59 }, { 55, 218, 107, 167 }, { 166, 16, 18, 159 }, { 180, 246, 207, 159 }, { 166, 242, 38, 239 },
        { 240, 114, 28, 248 }, { 201, 61, 150, 235 }, { 169, 121, 220, 202 }, { 160, 176, 157, 101 },
        { 214, 233, 130, 59 }, { 5, 73, 36, 139 }, { 217, 213, 246, 216 }, { 51, 61, 231, 113 }, { 176, 159, 175, 50 },
        { 23, 232, 128, 128 }, { 49, 216, 82, 115 }, { 14, 148, 226, 9 }, { 85, 199, 143, 244 }, { 150, 59, 2, 165 },
        { 214, 78, 33, 206 }, { 140, 235, 195, 241 }, { 144, 138, 74, 149 }, { 54, 239, 133, 234 },
        { 129, 10, 178, 91 }, { 101, 184, 139, 155 }, { 63, 72, 38, 45 } };

        //the following can be used to extend the batch testing to 100 random coordinates.

        // , { 67, 80, 215, 147 }, { 178, 79, 8, 131 },
        // { 26, 118, 197, 201 }, { 116, 238, 146, 193 }, { 153, 241, 233, 99 }, { 168, 57, 190, 167 },
        // { 25, 172, 126, 55 }, { 37, 114, 36, 24 }, { 39, 56, 121, 241 }, { 231, 40, 40, 201 }, { 83, 64, 213, 122 },
        // { 237, 74, 148, 9 }, { 248, 220, 60, 129 }, { 129, 38, 142, 61 }, { 47, 209, 127, 174 }, { 53, 107, 188, 53 },
        // { 117, 167, 170, 216 }, { 199, 249, 210, 176 }, { 116, 77, 213, 49 }, { 164, 35, 75, 179 },
        // { 101, 137, 78, 158 }, { 221, 215, 24, 175 }, { 152, 136, 191, 67 }, { 117, 78, 93, 30 }, { 64, 247, 95, 94 },
        // { 106, 190, 61, 13 }, { 229, 64, 210, 82 }, { 23, 145, 144, 96 }, { 36, 206, 134, 10 }, { 131, 24, 72, 194 },
        // { 138, 65, 123, 64 }, { 168, 52, 193, 192 }, { 157, 59, 102, 43 }, { 35, 75, 185, 44 }, { 39, 200, 117, 75 },
        // { 193, 66, 87, 1 }, { 91, 90, 51, 7 }, { 185, 64, 1, 80 }, { 213, 54, 229, 145 }, { 78, 96, 17, 123 },
        // { 20, 147, 143, 91 }, { 29, 160, 212, 69 }, { 65, 126, 59, 234 }, { 15, 79, 84, 207 }, { 110, 59, 31, 42 },
        // { 245, 75, 108, 16 }, { 118, 108, 0, 157 }, { 244, 223, 6, 188 }, { 78, 135, 84, 113 }, { 20, 33, 99, 190 }

    boolean testing = false;
    if (testing == false) {
      // RamblersSearch searcher = new RamblersSearch(map1, new Coords(100, 100));
      RamblersSearch searcher = new RamblersSearch(map1, new Coords(map1.getDepth() - 1, map1.getWidth() - 1));

      int tempx = 2;
      int tempy = 40;
      SearchState initState = (SearchState) new RamblersState(new Coords(tempy, tempx), map1.getTmap()[tempy][tempx],
          0);

      // change from search1 - specify strategy
      // String res_df = searcher.runSearch(initState, "breadthFirst");
      // System.out.println(res_df);
      // String res_bf = searcher.runSearch(initState, "depthFirst");
      // System.out.println(res_bf);

      // String res_bb = searcher.runSearch(initState, "branchAndBound");
      // System.out.println(res_bb);
      String res_bb = searcher.runSearch(initState, "aStar");
      System.out.println(res_bb);
    } else {
      int tempxstart, tempystart, tempxend, tempyend;
      int count = 0;
      double totalEfficiency = 0;
      double averageEfficiency = 0; // used for efficiency purposes
      for (int[] tempArray : diablotestingArray) {
        count += 1;
        System.out.println("Count: " + count + " out of: " + diablotestingArray.length);
        tempystart = tempArray[0];
        tempxstart = tempArray[1];
        tempyend = tempArray[2];
        tempxend = tempArray[3];
        RamblersSearch searcher = new RamblersSearch(map1, new Coords(tempystart, tempxstart));
        SearchState initState = (SearchState) new RamblersState(new Coords(tempyend, tempxend),
            map1.getTmap()[tempyend][tempxend], 0);
        totalEfficiency += searcher.runSearchE(initState, "aStar");
        //totalEfficiency += searcher.runSearchE(initState, "branchAndBound");
        averageEfficiency = totalEfficiency / count;
        System.out.println("averageEfficiency: " + averageEfficiency);
      }
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();



    }

  }
}
