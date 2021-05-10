package Visitor;

import AVL.Node;
import AVL.Tree;
import Objects.Cat;
import Objects.Dog;
import Objects.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * @author Jakub Szwedowicz
 * @version 1.0
 * date: 09.05.2021
 * email: kuba.szwedowicz@gmail.com
 */
public abstract class Visitor {
    int predicateMeet = 0;

    public abstract <T> void countMatching(Node<T> node, Consumer<? super T> cons);

    public <T> int countMatching(Node<T> node, Predicate<? super T> cons) {
        predicateMeet = 0;
        countMatchingHelper(node, cons);
        return predicateMeet;
    }


    public <T> List<T> visit(Node<T> node, Predicate<? super T> cons) {
        List<T> res = new ArrayList<>();
        visitHelper(node, cons, res);
        return res;
    }

    protected abstract <T> void countMatchingHelper(Node<T> node, Predicate<? super T> pred);

    protected abstract <T> void visitHelper(Node<T> node, Predicate<? super T> pred, List<T> list);

    public void visitDog(Dog dog) {
        System.out.println("Dog's name: " + dog.getName());
    }

    public void visitCat(Cat cat) {
        System.out.println("Cat's name: " + cat.getName());
    }

    public void visitStudent(Student student) {
        System.out.println("Student's number: " + student.getNumber());

    }
}
