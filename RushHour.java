/*
 * Using IDDFS (Iterative Deepening Depth First Search) to solve Rush Hour
 */

import java.util.*;

/**
 * Class for running IDDFS on Rush Hour
 *
 * @author Ian Porada
 */
public class RushHour {
  public String[] args; // initial vehicle list
  public int nodeCount; // the number of visisted nodes

  /**
   * @param args Command line arguments representing board state
   */
  public RushHour(String[] args) {
    this.args = args;
    nodeCount = 0;
  }

  /**
   * iterative deapening depth first search
   * @return The final target node where the icecream truck is in position C5
   */
  public Node iddfs() {
    int depth = 0;
    while (true) {
      /* Run depth-limited search for increasing depths [0, infinity) */
      Node found = depthLimitedSearch(new Node(args), depth);
      if (found != null) {
        return found;
      }
      depth++;
    }
  }

  /**
   * Recursive Depth first search w/ limited depth
   * @param node The local root of the search tree
   * @param depth The maximum depth of the search
   * @return The target node (null if not found)
   */
  public Node depthLimitedSearch(Node node, int depth) {
    nodeCount++;
    if (depth == 0 && node.isTarget()) {
      // If on the frontier, check if the node is target
      return node;
    } else if (depth > 0) {
      // Otherwise call DFS on all children
      for (Node child : node.generateChildren()) {
        Node found = depthLimitedSearch(child, depth - 1);
        if (found != null) {
          return found;
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    if (args.length == 0) {
        System.out.println("usage: java RushHour ice_cream_truck [vehicle...]\n\t" +
                           "Vehicles are represented as XYZV\n\t" + 
                           "X=(I)ce|(C)ar|(B)us, Y=(V)ertical|(H)orizontal, Z=row, V=col");
        return;
    }

    // run IDDFS on args and print the number of visited nodes
    RushHour rh = new RushHour(args);
    rh.iddfs();
    System.out.println("# Visited Nodes: " + rh.nodeCount);
  }
}
