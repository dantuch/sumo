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

    public boolean isEqual(Node x, Node y) {
        Set<List<Integer>> parsedX = parseNodes(x);
        Set<List<Integer>> parsedY = parseNodes(y);
        return parsedX.equals(parsedY);
    }

    private Set<List<Integer>> parseNodes(Node node) {
        Set<List<Integer>> connections = new LinkedHashSet<>();
        parseRecursive(node, connections, new LinkedList<>());
        return connections;
    }

    private void parseRecursive(Node node, Set<List<Integer>> values, LinkedList<Node> parents) {
        if (node == null) {
            return;
        }
        if (node.right == null && node.left == null) {
            // its a leaf
            values.add(
                    parents.stream()
                            .map(p -> nodesToChildrenNumber.getOrDefault(p, countChildren(p)))
                            .collect(Collectors.toList()));
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
