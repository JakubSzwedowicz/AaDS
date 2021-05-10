package AVL;

import AVL.Iterators.ITwoWayIterator;
import AVL.Iterators.TwoWayInOrderIterator;
import Visitor.InOrderVisitor;
import Visitor.PostOrderVisitor;
import Visitor.PreOrderVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 10.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
class AVLTreeTest {
    AVLTree<Integer> tree;
    private static List<Integer> list;
    private static List<Integer> naturalOrderList;
    private static int even;
    private static int odd;

    @BeforeAll
    static void beforeAll() {
        list = Arrays.asList(25, 15, 10, 4, 12, 22, 18, 24, 50, 35, 31, 44, 70, 66, 90);
        for (Integer e : list) {
            if (e % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        naturalOrderList = new ArrayList<>(list);
        Collections.sort(naturalOrderList);
    }

    @BeforeEach
    void setUp() {
        tree = new AVLTree<>(list);
    }

    @Test
    void testInOrderIteration() {
        Assertions.assertIterableEquals(
                naturalOrderList,
                tree,
                "Iterating In Order using iterator");
    }

    @Test
    void testVisitorsForCountingMatching() {
        Predicate<Integer> pred = e -> e % 2 == 0;
        Assertions.assertEquals(even, new PostOrderVisitor().countMatching(tree.getRoot(), pred), "Test for PostOrderVisitor");
        Assertions.assertEquals(even, new InOrderVisitor().countMatching(tree.getRoot(), pred), "Test for InOrderVisitor");
        Assertions.assertEquals(even, new PreOrderVisitor().countMatching(tree.getRoot(), pred), "Test for PreOrderVisitor");
    }

    @Test
    void testInOrderVisitorForPredicate() {
        Predicate<Integer> pred = e -> e % 2 == 0;
        List<Integer> filtered = naturalOrderList.stream().filter(pred).collect(Collectors.toList());
        Assertions.assertEquals(filtered, new InOrderVisitor().visit(tree.getRoot(), pred), "Test for InOrderVisitor predicate");
    }

    @Test
    void testTreeForLower() {
        for (int i = 0; i < naturalOrderList.size() - 1; i++) {
            int res = naturalOrderList.get(i);
            int next = naturalOrderList.get(i + 1);

            int searched = next - 1;
            Assertions.assertEquals(res, tree.lower(searched));
        }
    }

    @Test
    void testTreeForUpper() {
        for (int i = 0; i < naturalOrderList.size() - 1; i++) {
            int prev = naturalOrderList.get(i);
            int res = naturalOrderList.get(i + 1);

            int searched = prev + 1;
            Assertions.assertEquals(res, tree.upper(searched));
        }
    }

    @Test
    void testInsertMax() {
        Integer max = tree.getMaxNode().getData();
        max++;
        tree.insert(max);
        Assertions.assertEquals(max, tree.getMaxNode().getData());
    }

    @Test
    void testInsertMin() {
        Integer min = tree.getMinNode().getData();
        min--;
        tree.insert(min);
        Assertions.assertEquals(min, tree.getMinNode().getData());
    }

    @Test
    void testDeleteMax() {
        Node<Integer> maxNode = tree.getMaxNode();
        ITwoWayIterator<Integer> it = new TwoWayInOrderIterator<>(tree, maxNode);
        Node<Integer> preMaxNode = it.prevNode();
        tree.delete(maxNode.getData());

        Assertions.assertEquals(preMaxNode.getData(), tree.getMaxNode().getData());
    }

    @Test
    void testDeleteMin() {
        Node<Integer> minNode = tree.getMinNode();
        ITwoWayIterator<Integer> it = new TwoWayInOrderIterator<>(tree, minNode);
        Node<Integer> preMinNode = it.nextNode();
        tree.delete(minNode.getData());

        Assertions.assertEquals(preMinNode.getData(), tree.getMinNode().getData());
    }
}