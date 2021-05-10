package Visitor;

import AVL.Node;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 10.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public class PostOrderVisitor extends Visitor {
    @Override
    public <T> void countMatching(Node<T> node, Consumer<? super T> cons) {
        if (node != null) {
            countMatching(node.getLeft(), cons);
            countMatching(node.getRight(), cons);
            cons.accept(node.getData());
        }
    }

    @Override
    protected <T> void visitHelper(Node<T> node, Predicate<? super T> pred, List<T> list) {
        if (node != null) {
            visitHelper(node.getLeft(), pred, list);
            visitHelper(node.getRight(), pred, list);
            if(pred.test(node.getData())){
                list.add(node.getData());
            }
        }
    }

    @Override
    protected <T> void countMatchingHelper(Node<T> node, Predicate<? super T> pred) {
        if (node != null) {
            countMatchingHelper(node.getLeft(), pred);
            countMatchingHelper(node.getRight(), pred);
            if (pred.test(node.getData())) {
                predicateMeet++;
            }
        }
    }
}
