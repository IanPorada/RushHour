import java.util.*;

/**
 * Simple search tree
 *
 * @author Ian Porada
 */
public class Node {
  public int depth;
  public Board board; // The board state at this node (i.e. data)

  /**
   * Construct a new node with board in the state of vehicles
   */
  public Node(String[] vehicles) {
    this(vehicles, 0);
  }

  /**
   * Construct a new node with a specified depth
   */
  public Node(String[] vehicles, int depth) {
    this.board = new Board(vehicles);
    this.depth = depth;
  }

  /**
   * Generate all possible children of the current board state
   * @return An List of all children generated
   */
  public ArrayList<Node> generateChildren() {
    ArrayList<Node> children = new ArrayList<Node>();
    for (String[] vehicles : board.possibleMoves()) {
      children.add(new Node(vehicles, depth + 1));
    }
    return children;
  }

  /**
   * @return is this the solution board state?
   */
  public boolean isTarget() {
    return board.isTarget();
  }

  /**
   * Used for printing trees, prints data indented by depth
   */
  public String toString() {
    String s = "";
    for (int i = 0; i < depth; i++) {
      s += "\t";
    }
    s += board.toString();
    return s;
  }
}
