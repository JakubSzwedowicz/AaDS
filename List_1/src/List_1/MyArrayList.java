package List_1;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class MyArrayList<E> implements RandomAccess, Cloneable, Serializable, Iterable<E> {
    // MEMBERS
    private int m_size;
    transient private int m_capacity;
    private Object[] m_array;

    @java.io.Serial
    private static final long serialVersionUID = 10L;

    // Empty, unmodifiable placeholders
    private static final Object[] EMPTY_ARRAY = {};

    // Default parameters
    private static final int DEFAULT_CAPACITY = 10;

    // PUBLIC
    // Constructors
    MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    MyArrayList(int a_capacity) throws NegativeArraySizeException {
        if (a_capacity < 0) {
            throw new NegativeArraySizeException("Array with negative size cannot be initialised");
        }
        m_size = 0;
        m_capacity = a_capacity;
        if (a_capacity == 0) {
            m_array = EMPTY_ARRAY;
        } else {
            m_array = new Object[a_capacity];
        }
    }

    MyArrayList(Collection<? extends E> a_collection) throws NullPointerException {
        if (a_collection == null) {
            throw new NullPointerException("Can't construct MyArrayList from null collection!");
        }
        m_size = 0;
        m_capacity = a_collection.size() + (a_collection.size() >> 1);
        if (m_capacity < 0) {
            m_array = EMPTY_ARRAY;
        } else {
            m_array = new Object[m_capacity];
        }
        // Shallow or deep copy?
        for (E e : a_collection) {
            m_array[m_size++] = e;
        }

    }

    // Utilities
    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<E>();
    }

    @Override
    public MyArrayList<E> clone() {
        try {
            MyArrayList<E> res = (MyArrayList<E>) super.clone();
            // In case of mutable elements there has to be made deep copy
            res.m_array = Arrays.copyOf(m_array, m_size);
            return res;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    public boolean add(E a_number) {
        ensureCapacity(m_capacity + 1);
        m_array[m_size++] = a_number;
        return true;
    }

    public void add(int a_index, E a_number) throws IndexOutOfBoundsException {
        if (a_index >= m_size || a_index < 0) {
            throw new IndexOutOfBoundsException("Given index is not correct!"
                    + "\n\tindex = " + a_index
                    + "\n\tsize = " + m_size);
        }
        ensureCapacity(m_capacity + 1);
        moveRight(a_index, 1); // increases the size (m_size += a_distance)
        m_array[a_index] = a_number;
    }

    public boolean addAll(Collection<? extends E> a_array) throws NullPointerException {
        if (a_array == null) {
            throw new NullPointerException("Cannot add null collection!");
        } else if (a_array.isEmpty()) {
            return false;
        }
        ensureCapacity(m_capacity + a_array.size());
        for (E e : a_array) {
            m_array[m_size++] = e;
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> a_collection, int a_index) throws NullPointerException {
        if (a_index >= m_size || a_index < 0) {
            throw new IndexOutOfBoundsException("Given index exceeds size!"
                    + "\n\tindex = " + a_index
                    + "\n\tsize = " + m_size);
        } else if (a_collection == null) {
            throw new NullPointerException("Cannot insert an empty array!");
        } else if (a_collection.isEmpty()) {
            return false;
        }
        ensureCapacity(m_size + a_collection.size());
        moveRight(a_index, a_collection.size());  // increases the size
        for (E e : a_collection) {
            m_array[a_index++] = e;
        }
        return true;
    }

    public void clear() {
        Iterator<E> it = this.iterator();
        for (int i = 0; i < m_size; i++) {
            m_array[i] = null;
        }
        m_size = 0;
    }

    public int indexOf(E a_object) {
        for (int i = 0; i < m_size; i++) {
            if (m_array[i].equals(a_object)) {
                return i;
            }
        }
        return -1;
    }

    public E set(int a_index, E a_element) throws IndexOutOfBoundsException {
        if (a_index < 0 || a_index >= m_size) {
            throw new IndexOutOfBoundsException("Given index is illegal!"
                    + "\n\ta_index = " + a_index);
        }
        E ret = (E) m_array[a_index];
        m_array[a_index] = a_element;
        return ret;
    }

    public boolean contains(E a_element) {
        Objects.requireNonNull(a_element);
        return Arrays.stream(m_array).anyMatch(a_element::equals);
    }

    // Removes first object within the array that equals(a_object)
    // true if found and removed, false otherwise
    public boolean remove(Object a_object) {
        if (a_object == null) {
            return false;
        }
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(a_object)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // Removes element under specified index using shiftLeft.
    // throws if a_index < 0 || a_index >= m_size
    // returns removed element
    public E remove(int a_index) throws IndexOutOfBoundsException {
        if (a_index < 0 || a_index >= m_size) {
            throw new IndexOutOfBoundsException("Given index is out of range!"
                    + "\n\ta_index = " + a_index);
        }
        E res = (E) m_array[a_index];
        shiftLeft(a_index, m_size - 1, 1);
        m_size--;
        return res;
    }

    public void ensureCapacity(int a_minCapacity) throws NegativeArraySizeException {
        if (a_minCapacity < 0) {
            throw new NegativeArraySizeException("Capacity cannot be negative!");
        }
        if (a_minCapacity > m_capacity) {
            grow(a_minCapacity);
        }
    }

    // Shrinks capacity to match size
    public boolean shrinkToFit() {
        if (isEmpty()) {
            return false;
        }
        m_array = Arrays.copyOf(m_array, m_size);
        m_capacity = m_size;
        return true;
    }

    // getters
    public int size() {
        // Nul też jest elementem
        return m_size;
    }

    public E get(int a_index) throws IndexOutOfBoundsException {
        if (a_index >= m_size) {
            throw new IndexOutOfBoundsException("Given index cannot exceed or be equal to the size of the array"
                    + "\n\t size = " + m_size
                    + "\n\t index = " + a_index);
        }
        return (E) m_array[a_index];
    }

    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    // prints
    public String toString() {
        StringBuilder output = new StringBuilder("\n\t size: " + m_size + "\n\t capacity: " + m_capacity + "\n[");
        for (E e : this) {
            output.append(e).append(", ");
        }
        if (m_size != 0) {
            int end = output.length();
            output.replace(end - 2, end, "]");
        } else {
            output.append("]");
        }
        return output.toString();
    }

    @Override
    public boolean equals(Object a_object) {
        if (!(a_object instanceof MyArrayList<?>)) {
            return false;
        }
        MyArrayList<?> someArray = (MyArrayList<?>) a_object;
        if (someArray.m_size != m_size) {
            return false;
        }
        for (int i = 0; i < m_size; i++) {
            if (!(m_array[i].equals(someArray.m_array[i]))) {
                return false;
            }
        }
        return true;
    }

    // test
    public static boolean testClass() {
        try {
            System.out.println("---------------------------------------------\n" +
                    "Testing class List_1.MyArrayList\n" +
                    "----------------------------------------------");
            MyArrayList<Integer> a = new MyArrayList<>();
            a.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 2, 3, 2));
            System.out.println(a);
            System.out.println("Does contain 2?: ");
            System.out.println(a.contains(2));
            System.out.println("printing value on 3rd index:");
            System.out.println(a.get(3));
            System.out.println("Printing size and changing value under 3rd index to 5");
            System.out.println(a.size() + " " + a.set(3, 5));
            System.out.println(a.get(3));
            System.out.println("removing value 6 from the array:");
            Integer num = 6;
            a.remove(num);
            System.out.println(a);
            System.out.println("Adding (10, 10, 10) starting from the 3rd index:");
            System.out.println(a.addAll(Arrays.asList(10, 10, 10), 3));
            System.out.println(a);
            Iterator<Integer> it = a.iterator();
            while(it.hasNext()){
                if(it.next() == 10){
                    a.add(3);
                }
            }
//            System.out.println("shifting to left by from 3rd index by 2");
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage() + " happened");
            return false;
        }
    }


    // PRIVATE

    // discards elements [a_end - a_positions, a_end]
    private boolean shiftRight(int a_begin, int a_end, int a_positions) {
        if (a_begin < 0 || a_begin >= m_size || a_end < 0 || a_end >= m_size || a_positions < 0) {
            return false;
        }
        int i = a_end;
        for (; i >= a_begin + a_positions; i--) {
            m_array[i] = m_array[i - a_positions];
        }
        for (; i >= a_begin; i--) {
            m_array[i] = null;
        }
        return true;
    }

    // discards elements [a_begin, a_begin + a_positiprivate class CustomIterator<E> implements Iterator<E> {ons]
    private boolean shiftLeft(int a_begin, int a_end, int a_positions) {
        if (a_begin < 0 || a_begin >= m_size || a_end < 0 || a_end >= m_size || a_positions < 0) {
            return false;
        }
        int i = a_begin;
        for (i = a_begin; i <= a_end - a_positions; i++) {
            m_array[i] = m_array[i + a_positions];
        }
        for (; i <= a_end; i++) {
            m_array[i] = null;
        }
        return true;
    }

    // moves [a_begin, a_end] a_distance places to the right increasing the size
    // Doesn't discard anything
    private void moveRight(int a_begin, int a_distance) throws IllegalArgumentException {
        if (a_begin < 0 || a_begin >= m_size || a_distance < 0) {
            throw new IllegalArgumentException("Wrong parameters were given!"
                    + "\n\ta_begin = " + a_begin
                    + "\n\ta_distance = " + a_distance);
        } else if (a_distance == 0) {
            return;
        }
        ensureCapacity(m_size + a_distance);
        // first advance all the elements [a_begin, a_end] a_distance places
        if (m_size - a_begin >= 0) System.arraycopy(m_array, a_begin, m_array, a_begin + a_distance, m_size - a_begin);
        // Mark created space with nulls
        for (int i = a_begin; i < a_begin + a_distance; i++) {
            m_array[i] = null;
        }
        m_size += a_distance;
    }

    // increases the array capacity by at least 50%
    private void grow(int a_newCapacity) {
        int newCapacity = m_capacity + m_capacity >> 1;
        if (newCapacity < a_newCapacity) {
            newCapacity = a_newCapacity;
        }
        m_capacity = newCapacity;
        m_array = Arrays.copyOf(m_array, newCapacity);
    }

    // custom iterator class for iterating over MyArrayList
    private class CustomIterator<E> implements Iterator<E> {
        private int m_currentElement = 0;
        private boolean m_removed = true;

        @Override
        public boolean hasNext() {
            return m_currentElement != m_size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("Cannot invoke next() if there is no more elements to iterate over");
            }
            @SuppressWarnings("unchecked")
            E ret = (E) m_array[(m_currentElement++)];
            m_removed = false;
            return ret;
        }

        @Override
        public void remove() throws RuntimeException {
            if (m_removed) {
                throw new IllegalStateException("Iterator hasn't been incremented yet or element is already removed!");
            } else {
                m_removed = shiftLeft(m_currentElement - 1, m_size - 1, 1);
                m_size--;
                m_currentElement --;
                if (!m_removed) {
                    throw new RuntimeException("Removing element failed!"
                            + "\n\tremoved element index = " + (m_currentElement - 1));
                }
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) throws NullPointerException {
            Objects.requireNonNull(action);
            while (hasNext()) {
                action.accept(next());
            }
        }

        public int getCurrentIndex() {
            return m_currentElement;
        }
    }

    // Serialization
    @Serial
    private void readObject(ObjectInputStream a_ois) throws ClassNotFoundException, IOException {
        a_ois.defaultReadObject();    // toy implementation actually needless
        m_capacity = m_array.length;
    }
}
