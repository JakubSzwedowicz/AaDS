import aisd.list.AbstractList;
import aisd.list.OneWayLinkedListWithHead;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @Author Jakub Szwedowicz
 * @Create 21.03.2021
 * @Version 1.0
 */
public class ExtendedOneWayLinkedListWithHead<T> extends AbstractList<T> implements Iterable<T> {
    private OneWayLinkedListWithHead<T> innerList = new OneWayLinkedListWithHead<>();

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int cursor = -1;
            int movedLeft = 0;
            boolean canBeRemovedOrSet = false;
            boolean activeForwardIterator = true;
            int size = innerList.size();
            Iterator<T> it = innerList.iterator();  // Save me from O(n^2)

            @Override
            public boolean hasNext() {
                return cursor != size - 1;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (!activeForwardIterator) {
                    it = innerList.iterator();
                    for (int i = -1; i != cursor; i++) {
                        it.next();
                    }
                    activeForwardIterator = true;
                }
                cursor++;
                movedLeft = 0;
                canBeRemovedOrSet = true;
                return it.next();
            }

            @Override
            public boolean hasPrevious() {
                return cursor != -1;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                cursor--;
                movedLeft = 1;
                canBeRemovedOrSet = true;
                activeForwardIterator = false;
                return innerList.get(cursor + 1);
            }

            @Override
            public int nextIndex() {
                return cursor + 1;
            }

            @Override
            public int previousIndex() {
                return cursor;
            }

            @Override
            public void remove() {
                if (!canBeRemovedOrSet) {
                    throw new IllegalStateException();
                }
                canBeRemovedOrSet = false;
                innerList.remove(cursor + movedLeft);
                size--;
                cursor -= (1 - movedLeft);
            }

            // Null is legal element
            @Override
            public void set(T t) {
                if (!canBeRemovedOrSet) {
                    throw new IllegalStateException();
                }
                innerList.set(cursor, t);
            }

            @Override
            public void add(T t) {
                innerList.add(cursor + 1, t);
                size++;
                cursor++;
                canBeRemovedOrSet = false;
            }
        };
    }

    @Override
    public T remove(int index) {
        return innerList.remove(index);
    }

    @Override
    public boolean remove(T value) {
        return remove(value);
    }

    @Override
    public boolean add(T value) {
        return innerList.add(value);
    }

    @Override
    public boolean add(int index, T value) {
        return innerList.add(index, value);
    }

    @Override
    public void clear() {
        innerList.clear();
    }

    @Override
    public boolean contains(T value) {
        return innerList.contains(value);
    }

    @Override
    public T get(int index) {
        return innerList.get(index);
    }

    @Override
    public T set(int index, T value) {
        return innerList.set(index, value);
    }

    @Override
    public int indexOf(T value) {
        return innerList.indexOf(value);
    }

    @Override
    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return this.listIterator();
    }

    @Override
    public int size() {
        return innerList.size();
    }
};

