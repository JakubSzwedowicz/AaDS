package Visitor;

import AVL.Node;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class PreOrderVisitor extends Visitor {
    @Override
    public <T> void countMatching(Node<T> node, Consumer<? super T> cons) {
        if (node != null) {
            cons.accept(node.getData());
            countMatching(node.getLeft(), cons);
            countMatching(node.getRight(), cons);
        }
    }

    @Override
    protected <T> void visitHelper(Node<T> node, Predicate<? super T> pred, List<T> list) {
        if (node != null) {
            if (pred.test(node.getData())) {
                list.add(node.getData());
            }
            visitHelper(node.getLeft(), pred, list);
            visitHelper(node.getRight(), pred, list);
        }
    }

    @Override
    protected <T> void countMatchingHelper(Node<T> node, Predicate<? super T> pred) {
        if (node != null) {
            if (pred.test(node.getData())) {
                predicateMeet++;
            }
            countMatchingHelper(node.getLeft(), pred);
            countMatchingHelper(node.getRight(), pred);
        }
    }
}
