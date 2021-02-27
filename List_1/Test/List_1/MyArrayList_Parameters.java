package List_1;

import TestResources.Student;
import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
import java.util.stream.Stream;

public class MyArrayList_Parameters {

    // METHODS SOURCES
    static Stream<Arguments> testClone() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123)))));
    }

    static Stream<Arguments> testAdd1() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList()),
                        "aabbcc",
                        new MyArrayList<String>(Arrays.asList("aabbcc"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        50,
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20, 50))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        -0.12,
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -0.12))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        new Student("ObiWan", "Kenobi", 66226622),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622)))));
    }

    static Stream<Arguments> testAdd2() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList("aa", "bb")),
                        1,
                        "aabbcc",
                        new MyArrayList<String>(Arrays.asList("aa", "aabbcc", "bb"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        2,
                        50,
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 50, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        0,
                        -0.12,
                        new MyArrayList<Double>(Arrays.asList(-0.12, 3.14, 2.5, 0.0))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        2,
                        new Student("ObiWan", "Kenobi", 66226622),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("Han", "Solo", 645123)))));
    }

    static Stream<Arguments> testAddAll1() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList()),
                        Arrays.asList("aabbcc", "abc", "abc"),
                        new MyArrayList<String>(Arrays.asList("aabbcc", "abc", "abc"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        Arrays.asList(50, 69, 120),
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20, 50, 69, 120))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        Arrays.asList(-5.3, 0.0, 1290.1),
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -5.3, 0.0, 1290.1))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        Arrays.asList(
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729)),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729)))));
    }

    static Stream<Arguments> testAddAll2() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList("cde")),
                        0,
                        Arrays.asList("aabbcc", "abc", "abc"),
                        new MyArrayList<String>(Arrays.asList("aabbcc", "abc", "abc", "cde"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        2,
                        Arrays.asList(50, 69, 120),
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 50, 69, 120, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        2,
                        Arrays.asList(-5.3, 0.0, 1290.1),
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, -5.3, 0.0, 1290.1, 0.0))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        1,
                        Arrays.asList(
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729)),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123)))));
    }

    static Stream<Arguments> testClear() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList())),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416)))));
    }

    static Stream<Arguments> testIndexOf() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList()),
                        "abc",
                        -1),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        50,
                        1),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -5.3, 0.0, 1290.1)),
                        0.0,
                        2),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729))),
                        new Student("Master", "Yoda", 123456),
                        -1));
    }

    static Stream<Arguments> testSet() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList("aa", "bb")),
                        1,
                        "aabbcc",
                        new MyArrayList<String>(Arrays.asList("aa", "aabbcc"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        2,
                        50,
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 50))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0)),
                        0,
                        -0.12,
                        new MyArrayList<Double>(Arrays.asList(-0.12, 2.5, 0.0))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123))),
                        2,
                        new Student("ObiWan", "Kenobi", 66226622),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("ObiWan", "Kenobi", 66226622)))));
    }

    static Stream<Arguments> testContains() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList()),
                        "abc",
                        false),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        50,
                        true),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -5.3, 0.0, 1290.1)),
                        0.0,
                        true),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729))),
                        new Student("Master", "Yoda", 123456),
                        false));
    }

    static Stream<Arguments> testRemove1() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList()),
                        "abc",
                        new MyArrayList<String>(Arrays.asList())),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        50,
                        new MyArrayList<Integer>(Arrays.asList(10, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -5.3, 0.0, 1290.1)),
                        0.0,
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, -5.3, 0.0, 1290.1))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729))),
                        new Student("Master", "Yoda", 123456),
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729)))));
    }

    static Stream<Arguments> testRemove2() throws Throwable {
        return Stream.of(
                Arguments.of(
                        new MyArrayList<String>(Arrays.asList("aa", "bb", "cd")),
                        0,
                        new MyArrayList<String>(Arrays.asList("bb", "cd"))),
                Arguments.of(
                        new MyArrayList<Integer>(Arrays.asList(10, 50, 20)),
                        1,
                        new MyArrayList<Integer>(Arrays.asList(10, 20))),
                Arguments.of(
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, 0.0, -5.3, 0.0, 1290.1)),
                        2,
                        new MyArrayList<Double>(Arrays.asList(3.14, 2.5, -5.3, 0.0, 1290.1))),
                Arguments.of(
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622),
                                new Student("General", "Grievous", 823729))),
                        4,
                        new MyArrayList<Student>(Arrays.asList(
                                new Student("Luke", "Skywalker", 243416),
                                new Student("Darth", "Vader", 241243),
                                new Student("Han", "Solo", 645123),
                                new Student("ObiWan", "Kenobi", 66226622)))));
    }

}
