package List_1;

import TestResources.Stopwatch;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

// Turn off optimization first to measure raw methods performance:
// -Djava.compiler=NONE

public class
MyArrayListTest {
    private static final Stopwatch s_timer = new Stopwatch();

    @BeforeEach
    public void prepareForTest() {
        s_timer.reset();
        s_timer.start();
    }

    @AfterEach
    public void finishAfterTest(TestInfo a_testInfo) {
        s_timer.stop();
        System.out.println("LOGGER: execution time is " + s_timer.getElapsedTimeMilis() + " [ms] of " + a_testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "testClone (Run: {index}): " +
            "\na_array = {0}, \na_answer = {1}")
    @MethodSource("List_1.MyArrayList_Parameters#testClone")
    public <E> void testClone(MyArrayList<E> a_array, MyArrayList<E> a_answer) {
        MyArrayList<E> clone = a_array.clone();
        a_array.clear();
        Assertions.assertEquals(a_answer, clone);
    }

    @ParameterizedTest(name = "testAdd1 (Run: {index}): " +
            "\na_array = {0}, \na_parameter = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testAdd1")
    public <E> void testAdd1(MyArrayList<E> a_array, E a_parameter, MyArrayList<E> a_answer) {
        a_array.add(a_parameter);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testAdd2 (Run: {index}): " +
            "\na_array = {0}, \na_parameter1 = {1}, \na_parameter2 = {2}, \na_answer = {3}")
    @MethodSource("List_1.MyArrayList_Parameters#testAdd2")
    public <E> void testAdd2(MyArrayList<E> a_array, int a_index, E a_parameter, MyArrayList<E> a_answer) {
        a_array.add(a_index, a_parameter);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testAddAll1 (Run: {index}): " +
            "\na_array = {0}, \na_parameter1 = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testAddAll1")
    public <E> void testAddAll1(MyArrayList<E> a_array, List<E> a_parameter, MyArrayList<E> a_answer) {
        a_array.addAll(a_parameter);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testAddAll2 (Run: {index}): " +
            "\na_array = {0}, \na_parameter1 = {1}, \na_parameter2 = {2}, \na_answer = {3}")
    @MethodSource("List_1.MyArrayList_Parameters#testAddAll2")
    public <E> void testAddAll2(MyArrayList<E> a_array, int a_index, List<E> a_parameter, MyArrayList<E> a_answer) {
        a_array.addAll(a_parameter, a_index);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testClear (Run: {index}): " +
            "\na_array = {0}")
    @MethodSource("List_1.MyArrayList_Parameters#testClear")
    public <E> void testClear(MyArrayList<E> a_array) {
        a_array.clear();
        Assertions.assertEquals(new MyArrayList<>(), a_array);
    }

    @ParameterizedTest(name = "testIndexOf (Run: {index}): " +
            "\na_array = {0}, \na_parameter = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testIndexOf")
    public <E> void testIndexOf(MyArrayList<E> a_array, E a_element, int a_answer) {
        Assertions.assertEquals(a_answer, a_array.indexOf(a_element));
    }

    @ParameterizedTest(name = "testSet (Run: {index}): " +
            "\na_array = {0}, \na_parameter1 = {1}, \na_parameter2 = {2}, \na_answer = {3}")
    @MethodSource("List_1.MyArrayList_Parameters#testSet")
    public <E> void testSet (MyArrayList<E> a_array, int a_index, E a_element, MyArrayList<E> a_answer) {
        a_array.set(a_index, a_element);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testContains (Run: {index}): " +
            "\na_array = {0}, \na_parameter = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testContains")
    public <E> void testContains (MyArrayList<E> a_array, E a_element, boolean a_answer) {
        Assertions.assertEquals(a_answer, a_array.contains(a_element));
    }

    @ParameterizedTest(name = "testRemove1 (Run: {index}): " +
            "\na_array = {0}, \na_parameter = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testRemove1")
    public <E> void testRemove1 (MyArrayList<E> a_array, E a_element, MyArrayList<E> a_answer) {
        a_array.remove(a_element);
        Assertions.assertEquals(a_answer, a_array);
    }

    @ParameterizedTest(name = "testRemove2 (Run: {index}): " +
            "\na_array = {0}, \na_parameter = {1}, \na_answer = {2}")
    @MethodSource("List_1.MyArrayList_Parameters#testRemove2")
    public <E> void testRemove2 (MyArrayList<E> a_array, int a_index, MyArrayList<E> a_answer) {
        a_array.remove(a_index);
        Assertions.assertEquals(a_answer, a_array);
    }

}
