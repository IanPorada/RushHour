import java.util.*;

/**
 * Represents a 6x6 board state of Rush Hour
 * Vehicles are represented as XYZV where X=type, Y=vertical?, Z=row, V=col
 *
 * @author Ian Porada
 */
public class Board {
  public String[] vehicles; // each vehicle on the board represented as a tree
  public boolean[][] grid; // used for generating possible moves; true if occupied, false otherwise

  /**
   * Construct a new board consisting of the specified vehicles
   */
  public Board(String[] vehicles) {
    this.vehicles = vehicles;
    generateGrid();
  }

  /**
   * populates the grid with information on occupied spaces
   * (spaces are true if occupied and false otherwise)
   */
  public void generateGrid() {
    grid = new boolean[6][6];
    for (String v : vehicles) {
      int length = v.charAt(0) == 'B' ? 3 : 2;
      int y = v.charAt(2) - 'A';
      int x = v.charAt(3) - '1';
      boolean vertical = v.charAt(1) == 'V';
      int fy = y + (vertical ? length : 1);
      int fx = x + (vertical ? 1 : length);
      for (int col = x; col < fx; col++) {
        for (int row = y; row < fy; row++) {
          grid[col][row] = true;
        }
      }
    }
  }

  /**
   * For checking if a vehicle can move up/left to space x,y
   */
  public boolean canMoveBack(int x, int y) {
    return (x >= 0 && y >= 0) && !grid[x][y];
  }

  /**
   * For checking if a vehicle can move down/right to space x,y
   */
  public boolean canMoveForward(int x, int y) {
    return (x < 6 && y < 6) && !grid[x][y];
  }

  /**
   * Generates a new vehicle list where vehicles[i] has moved to (x, y)
   *
   * @param  i The index of the vehicle to change
   * @param  x Vehicle i's new x
   * @param  y Vehicle i's new y
   * @return   A new board state (array of Strings) where vehicle at index i is now in position (x, y)
   */
  public String[] moveTo(int i, int x, int y) {
    String[] new_vehicles = new String[vehicles.length];
    for (int j = 0; j < vehicles.length; j++) {
      if (j == i) {
        new_vehicles[j] = vehicles[j].substring(0, 2) + (char)('A' + y) + (char)('1' + x);
      } else {
        new_vehicles[j] = new String(vehicles[j]);
      }
    }
    return new_vehicles;
  }

  /**
   * @return A list of all possible board states that can be reached from the current board state
   */
  public ArrayList<String[]> possibleMoves() {
    ArrayList<String[]> possibleMoves = new ArrayList<String[]>();
    for (int i = 0; i < vehicles.length; i++) {
      String v = vehicles[i];
      int length = v.charAt(0) == 'B' ? 3 : 2;
      int y = v.charAt(2) - 'A';
      int x = v.charAt(3) - '1';
      boolean vertical = v.charAt(1) == 'V';
      // (fx,fy) is the position in front of vehicle v
      int fy = y + (vertical ? length : 0);
      int fx = x + (vertical ? 0 : length);
      // (bx,by) is the position behind vehicle v
      int by = y - (vertical ? 1 : 0);
      int bx = x - (vertical ? 0 : 1);
      if (canMoveBack(bx, by)) {
        /* can move backwards */
        possibleMoves.add(moveTo(i, bx, by));
      }
      if (canMoveForward(fx, fy)) {
        /* can move forwards */
        possibleMoves.add(moveTo(i, x + (vertical ? 0 : 1), y + (vertical ? 1 : 0)));
      }
    }
    grid = null;
    return possibleMoves;
  }

  /**
   * @return True if the icecream truck is in position C5, false otherwise
   */
  public boolean isTarget() {
    return vehicles[0].charAt(3) == '5';
  }

  public String toString() {
    return String.join(" ", vehicles);
  }
}
