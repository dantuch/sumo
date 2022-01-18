package sumo.logic.alg2;

import java.util.Random;

public class TreeGenerator {

    private Random random = new Random();

    public Node generateRandomTreeWithDepth(int depth){
        if(depth == 0) {
            return new Node("Leaf__" + System.nanoTime());
        }
        int childrenNumber = random.nextInt(4);
        if (childrenNumber == 0) {
            return new Node("Leaf_" + System.nanoTime());
        } else if (childrenNumber == 1) {
            return new Node(generateRandomTreeWithDepth(depth-1), null, "node_L_" + System.nanoTime());
        } else if (childrenNumber == 2) {
            return new Node(null, generateRandomTreeWithDepth(depth-1), "node_R_" + System.nanoTime());
        }else {
            return new Node(generateRandomTreeWithDepth(depth-1), generateRandomTreeWithDepth(depth-1), "node_F_" + System.nanoTime());
        }
    }

    public static void main(String[] args) {
        TreeGenerator treeGen = new TreeGenerator();
        Node node = treeGen.generateRandomTreeWithDepth(3);
        System.out.println(node);
    }
}
