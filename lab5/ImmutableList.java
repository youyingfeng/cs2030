import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.toIntExact;

class ImmutableList<T> {
    private final ArrayList<T> list;

    ImmutableList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    @SafeVarargs
    ImmutableList(T... obj) {
        ArrayList<T> tempArrayList = new ArrayList<T>(obj.length);

        for (T element: obj) {
            tempArrayList.add(element);
        }

        this.list = new ArrayList<T>(tempArrayList);
    }

    public ImmutableList<T> add(T obj) {
        ArrayList<T> tempList = new ArrayList<T>(list);
        tempList.add(obj);
        return new ImmutableList<T>(tempList);
    }

    public ImmutableList<T> replace(T obj, T newObj) {
        ArrayList<T> tempList = new ArrayList<T>(list);

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).equals(obj)) {
                tempList.set(i, newObj);
            }
        }
        return new ImmutableList<T>(tempList);
    }

    public ImmutableList<T> remove(T obj) {
        ArrayList<T> tempList = new ArrayList<T>(list);

        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).equals(obj)) {
                tempList.remove(i);
                break;
            }
        }
        return new ImmutableList<T>(tempList);
    }

    public ImmutableList<T> filter(Predicate<? super T> predicate) {
        ArrayList<T> tempList = new ArrayList<T>();

        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i)) == true) {
                tempList.add(list.get(i));
            }
        }
        return new ImmutableList<T>(tempList);
    }

    public <U> ImmutableList<U> map(Function<? super T, ? extends U> function) {
        ArrayList<U> tempList = new ArrayList<U>(list.size());

        for (int i = 0; i < list.size(); i++) {
            tempList.add(function.apply(list.get(i)));
        }
        return new ImmutableList<U>(tempList);
    }

    public ImmutableList<T> limit(long maxLength) {
        ArrayList<T> tempList = new ArrayList<T>();

        if (maxLength < 0) {
            throw new IllegalArgumentException("limit size < 0");
        } else if (maxLength > list.size()) {
            maxLength = list.size();
        }

        for (long i = 0; i < maxLength; i++) {
            tempList.add(list.get(toIntExact(i)));
        }

        return new ImmutableList<T>(tempList);
    }

    public ImmutableList<T> sorted() {
        ArrayList<T> tempList = new ArrayList<T>(list.size());

        for (T element:list) {
            if (!(element instanceof Comparable)) {
                throw new IllegalStateException("List elements do not implement Comparable");
            }
        }

        PriorityQueue<T> tempQueue = new PriorityQueue<T>(list);

        for (int i = 0; i < list.size(); i++) {
            tempList.add(tempQueue.poll());
        }

        return new ImmutableList<T>(tempList);
    }

    public ImmutableList<T> sorted(Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        }

        ArrayList<T> tempList = new ArrayList<T>(list);
        Collections.sort(tempList, comparator);

        return new ImmutableList<T>(tempList);
    }

    public Object[] toArray() {
        try {
            return list.toArray();
        } catch (ArrayStoreException exception) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    public <U> U[] toArray(U[] array) {
        if (array == null) {
            throw new NullPointerException("Input array cannot be null");
        }

        try {
            return list.toArray(array);
        } catch (ArrayStoreException exception) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    @Override
    public String toString() {
        String output;

        if (list.size() == 0) {
            output = "";
        } else {
            output = String.valueOf(list.get(0));
        }

        for (int i = 1; i < list.size(); i++) {
            output = String.join(", ", output, String.valueOf(list.get(i)));
        }

        return "[" + output + "]";
    }
}
