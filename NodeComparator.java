import java.util.Comparator;

/**
 * Basic class for comparing nodes by:
 * f_cost > depth > hashCode
 *
 * @author Ian Porada
 */
public class NodeComparator implements Comparator<Node> {
  @Override
  public int compare(Node a, Node b) {
    int fa = a.f();
    int fb = b.f();comparing precedence
    if (fa < fb) {
      return -1;
    } else if (fa > fb) {
      return 1;
    } else {
      int da = a.depth;
      int db = b.depth;
      if (da > db) {
        return -1;
      } else if (da < db) {
        return 1;
      } else {
        return Integer.compare(a.hashCode(), b.hashCode());
      }
    }
  }
}
