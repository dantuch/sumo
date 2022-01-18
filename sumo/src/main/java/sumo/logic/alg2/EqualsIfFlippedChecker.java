package sumo.logic.alg2;

import java.util.*;
import java.util.stream.Collectors;


class Node {

    final String name;
    final Node left;
    final Node right;

    Node(Node left, Node right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    public Node(String name) {
        // it's a leaf
        this.left = null;
        this.right = null;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}


public class EqualsIfFlippedChecker {

    private Map<Node, Integer> nodesToChildrenNumber = new HashMap<>();


    public boolean isEqualBF(Node x, Node y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return (isEqualBF(x.left, y.left) && isEqualBF(x.right, y.right))
                || (isEqualBF(x.left, y.right) && isEqualBF(x.right, y.left));
    }


    public boolean isEqual(Node x, Node y) {
        nodesToChildrenNumber = new HashMap<>();
        Map<List<Integer>, Integer> parsedX = parseNodes(x);
        Map<List<Integer>, Integer> parsedY = parseNodes(y);
        return parsedX.equals(parsedY);
    }

    private Map<List<Integer>, Integer> parseNodes(Node node) {
        Map<List<Integer>, Integer> connections = new HashMap<>();
        parseRecursive(node, connections, new LinkedList<>());
        return connections;
    }

    private void parseRecursive(Node node, Map<List<Integer>, Integer> values, LinkedList<Node> parents) {
        if (node == null) {
            return;
        }
        if (node.right == null && node.left == null) {
            // its a leaf

            List<Integer> childrenNumberInParents = parents.stream()
                    .map(p -> nodesToChildrenNumber.getOrDefault(p, countChildren(p)))
                    .collect(Collectors.toList());
            Integer orDefault = values.getOrDefault(childrenNumberInParents, 0);
            values.put(childrenNumberInParents, ++orDefault);
        }

        parents.add(node);
        if (node.left != null) {
            parseRecursive(node.left, values, parents);
        }
        if (node.right != null) {
            parseRecursive(node.right, values, parents);
        }
        parents.remove(node);
    }

    private int countChildren(Node node) {
        int children = 0;
        if (node.left != null) {
            children++;
        }
        if (node.right != null) {
            children++;
        }
        return children;
    }

}
