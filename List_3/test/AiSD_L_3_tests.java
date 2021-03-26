// jUnit 5.3

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedOneWayLinkedListWithHeadTest {
    private ExtendedOneWayLinkedListWithHead<Integer> listUnderTest;

    @BeforeEach
    void setUp() {
        listUnderTest = new ExtendedOneWayLinkedListWithHead<>();
        listUnderTest.add(1);
        listUnderTest.add(2);
        listUnderTest.add(3);
    }

    @Test
    void iteratorTest() {
        assertIterableEquals(Arrays.asList(1, 2, 3), listUnderTest);
    }

    @Test
    void secondRunOfIteratorTest() {
        ArrayList<Integer> actual = new ArrayList<>();

        for (Integer integer : listUnderTest) {
            actual.add(integer);
        }

        for (Integer integer : listUnderTest) {
            actual.add(integer);
        }

        assertIterableEquals(Arrays.asList(1, 2, 3, 1, 2, 3), actual);
    }

    @Test
    void removeInIteratorTest() {
        ArrayList<Integer> actual = new ArrayList<>();

        ListIterator<Integer> iterator = listUnderTest.listIterator();
        assertThrows(IllegalStateException.class, iterator::remove);
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            assertThrows(IllegalStateException.class, iterator::remove);
        }

        assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(0, listUnderTest.size());
    }

    @Test
    void setInIteratorTest() {
        ListIterator<Integer> iterator = listUnderTest.listIterator();
        assertThrows(IllegalStateException.class, () -> iterator.set(8));
        while (iterator.hasNext()) {
            iterator.next();
            iterator.set(8);
        }

        assertIterableEquals(Arrays.asList(8, 8, 8), listUnderTest);
    }

    @Test
    void addInIteratorTest() {
        ListIterator<Integer> iterator = listUnderTest.listIterator();
        ArrayList<Integer> actual = new ArrayList<>();
        while (iterator.hasNext()) {
            actual.add(iterator.next());
            iterator.add(8);
            actual.add(iterator.previous());
            actual.add(iterator.next());
        }

        assertIterableEquals(Arrays.asList(1, 8, 8, 2, 8, 8, 3, 8, 8), actual);
        assertIterableEquals(Arrays.asList(1, 8, 2, 8, 3, 8), listUnderTest);
    }

    @Test
    void forwardAndBackIteratorTest() {
        ArrayList<Integer> actual = new ArrayList<>();
        ListIterator<Integer> iterator = listUnderTest.listIterator();

        actual.add(iterator.next());
        actual.add(iterator.next());
        actual.add(iterator.previous());
        actual.add(iterator.next());
        actual.add(iterator.next());

        while (iterator.hasPrevious()) {
            actual.add(iterator.previous());
        }
        assertThrows(NoSuchElementException.class, iterator::previous);

        assertIterableEquals(Arrays.asList(1, 2, 2, 2, 3, 3, 2, 1), actual);
    }

    @Test
    void indexOfAndClearAndAddTest(){
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(listUnderTest.indexOf(1));
        actual.add(listUnderTest.indexOf(2));
        actual.add(listUnderTest.indexOf(3));
        listUnderTest.clear();
        assertEquals(true, listUnderTest.isEmpty());
        assertEquals(0, listUnderTest.size());
        listUnderTest.add(1);
        listUnderTest.add(2);
        listUnderTest.add(3);
        for(Integer i : listUnderTest){
            actual.add(i);
        }
        assertEquals(Arrays.asList(0, 1, 2, 1, 2, 3), actual);
    }

    @Test
    void nextIndexAndPreviousIndexTest(){
        ListIterator<Integer> it = listUnderTest.listIterator();
        assertEquals(0, it.nextIndex());
        assertEquals(-1, it.previousIndex());
        it.next();
        assertEquals(1, it.nextIndex());
        assertEquals(0, it.previousIndex());
        it.next();
        it.next();
        assertEquals(3, it.nextIndex());
        assertEquals(2, it.previousIndex());
        it.previous();
        assertEquals(2, it.nextIndex());
        assertEquals(1, it.previousIndex());
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }
}