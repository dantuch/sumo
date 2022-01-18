package sumo.logic.alg2;

import org.junit.Assert;
import org.junit.Test;

public class EqualsIfFlippedCheckerTest {

    @Test
    public void shouldNullsBeEqual() {

        //given
        Node x1 = null;
        Node y1 = null;

        // when
        EqualsIfFlippedChecker equalsIfFlippedChecker = new EqualsIfFlippedChecker();

        // then
        Assert.assertTrue("x1 and y1 are nulls and thus equal",
                equalsIfFlippedChecker.isEqual(x1, y1));
        Assert.assertTrue("x1 and y1 are nulls and thus equal",
                equalsIfFlippedChecker.isEqualBF(x1, y1));
    }

    @Test
    public void shouldTheSameTreesBeEqual() {

        //given trees x1 and y1
        //
        // x1:    1      y1:    1
        //      /  \          /  \
        //    2     3       2     3
        //   /             /
        //  4             4 

        Node x4 = new Node("x4");
        Node x2 = new Node(x4, null, "x2");
        Node x3 = new Node("x3");
        Node x1 = new Node(x2, x3, "x1");

        Node y4 = new Node("y4");
        Node y2 = new Node(y4, null, "y2");
        Node y3 = new Node("y3");
        Node y1 = new Node(y2, y3, "y1");

        // when
        EqualsIfFlippedChecker equalsIfFlippedChecker = new EqualsIfFlippedChecker();

        // then
        Assert.assertTrue("x1 and y1 are equal even without flipping",
                equalsIfFlippedChecker.isEqual(x1, y1));
        Assert.assertTrue("x1 and y1 are equal even without flipping",
                equalsIfFlippedChecker.isEqualBF(x1, y1));
    }


    @Test
    public void shouldFlippedTreesBeEqual() {

        //given trees x1 and y1
        //
        // x1:    1      y1:    1
        //      /  \          /  \
        //    2     3       2     3
        //   / \   /      /      / \
        //  4  5  6      4      5   6
        //    / \              / \
        //   7   8            7   8

        Node x4 = new Node("x4");
        Node x7 = new Node("x7");
        Node x8 = new Node("x8");
        Node x5 = new Node(x7, x8, "x5");
        Node x2 = new Node(x4, x5, "x2");
        Node x6 = new Node("x6");
        Node x3 = new Node(x6, null, "x3");
        Node x1 = new Node(x2, x3, "x1");


        Node y4 = new Node("y4");
        Node y7 = new Node("y7");
        Node y8 = new Node("y8");
        Node y5 = new Node(y7, y8, "y5");
        Node y6 = new Node("y6");
        Node y3 = new Node(y5, y6, "y3");
        Node y2 = new Node(y4, null, "y2");
        Node y1 = new Node(y2, y3, "y1");

        // when
        EqualsIfFlippedChecker equalsIfFlippedChecker = new EqualsIfFlippedChecker();

        // then
        Assert.assertTrue("x1 and y1 are equals if flipped",
                equalsIfFlippedChecker.isEqual(x1, y1));
        Assert.assertTrue("x1 and y1 are equals if flipped",
                equalsIfFlippedChecker.isEqualBF(x1, y1));
    }

    @Test
    public void shouldDifferentTreesBeNotEqualEvenIfFlipped() {
        //given trees a and b

        //    a.  /\    b. /  \
        //       / /\      \  /\
        //      / /\       /\   \
        //
        //   a:    1      b:     1
        //       /  \          /   \
        //     2     3        2     3
        //    /     / \        \   / \
        //   4     5  6        4  5   6
        //  /     / \         / \     \
        // 7     8   9       7   8     9


        Node a7 = new Node("a7");
        Node a4 = new Node(a7, null, "a4");
        Node a8 = new Node("a8");
        Node a9 = new Node("a9");
        Node a5 = new Node(a8, a9, "a5");
        Node a2 = new Node(a4, null, "a2");
        Node a6 = new Node("a6");
        Node a3 = new Node(a5, a6, "a3");
        Node a = new Node(a2, a3, "a1");


        Node b7 = new Node("b7");
        Node b8 = new Node("b8");
        Node b4 = new Node(b7, b8, "b4");
        Node b9 = new Node("b8");
        Node b6 = new Node(null, b9, "b6");
        Node b5 = new Node("b5");
        Node b3 = new Node(b5, b6, "b3");
        Node b2 = new Node(null, b4, "b2");
        Node b = new Node(b2, b3, "b1");

        // when
        EqualsIfFlippedChecker equalsIfFlippedChecker = new EqualsIfFlippedChecker();

        // then
        Assert.assertFalse("a and b are too different",
                equalsIfFlippedChecker.isEqual(a, b));
        Assert.assertFalse("a and b are too different",
                equalsIfFlippedChecker.isEqualBF(a, b));
    }


    @Test
    public void shouldRandomTreeWorkAsInBruteForce() {
        // given
        int testCasesNo = 1000000;
        int depthOfTreesToBeGenerated = 3;

        EqualsIfFlippedChecker equalsIfFlippedChecker = new EqualsIfFlippedChecker();

        // when
        TreeGenerator treeGenerator = new TreeGenerator();
        for (int i = 0; i < testCasesNo; i++) {
            Node a = treeGenerator.generateRandomTreeWithDepth(depthOfTreesToBeGenerated);
            Node b = treeGenerator.generateRandomTreeWithDepth(depthOfTreesToBeGenerated);
            // then
            boolean equalBF = equalsIfFlippedChecker.isEqualBF(a, b);
            boolean equal = equalsIfFlippedChecker.isEqual(a, b);
            if (equal != equalBF ) {
                System.out.println("error");
            }
            Assert.assertEquals(equalBF, equal);
        }
    }
}