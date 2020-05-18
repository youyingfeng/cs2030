import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;
import java.util.ArrayList;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<InfiniteListImpl<T>> tail;

    public InfiniteListImpl(Lazy<T> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    //Getters
    public Lazy<T> getHead() {
        return this.head;
    }

    public Supplier<InfiniteListImpl<T>> getTail() {
        return this.tail;
    }

    public InfiniteListImpl<T> peek() {
        Optional<T> optionalValue = head.get();
        optionalValue.ifPresent(value -> System.out.println(value));
        return tail.get();
    }

    public static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        Lazy<T> newHead = Lazy.generate(s);
        Supplier<InfiniteListImpl<T>> newTail = () -> InfiniteListImpl.generate(s);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Supplier<InfiniteListImpl<T>> newTail = () ->
                    InfiniteListImpl.iterate(next.apply(seed), next);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = this.head.map(mapper);
        Supplier<InfiniteListImpl<R>> newTail = () -> tail.get().map(mapper);
        return new InfiniteListImpl<R>(newHead, newTail);
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = this.head.filter(predicate);
        //is optional empty generated?
        Supplier<InfiniteListImpl<T>> newTail = () -> tail.get().filter(predicate);
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public InfiniteListImpl<T> limit(long n) {
        Lazy<T> newHead;
        Supplier<InfiniteListImpl<T>> newTail;

        if (n <= 0 || this instanceof EmptyList) {
            //i should dump my emptylist check somewhere else
            return new EmptyList<T>();
        } else if (n == 1) {
            newHead = this.head;
            newTail = () -> newHead.get().isEmpty() ? this.tail.get().limit(n)
                                                    : new EmptyList<T>();
        } else {
            newHead = this.head;
            newTail = () -> newHead.get().isEmpty() ? this.tail.get().limit(n)
                                                    : this.tail.get().limit(n - 1);
        }

        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        Lazy<T> newHead;
        Supplier<InfiniteListImpl<T>> newTail;
        newHead = this.head.filter(predicate);

        //hides the checking inside the supplier to force lazy evaluation
        //originally did not consider the case where the original element was null
        //now this makes sure that the null in the newhead is due to the failure of
        //the predicate and not because the original element was null
        //NEED TO TEST ORIGINAL FAILURE CASE
        newTail = () ->  this.head.get().isEmpty() == false &&
                         newHead.get().isEmpty()  == true ? new EmptyList<T>()
                                                          : this.tail.get().takeWhile(predicate);

        //The original code which used if/else to determine tail will spit out
        //an extra line. Because when the last takeWhile() is called, it will call
        //get on newHead instead of lazily evaluating it.
        //So the mapping in the Lazy.filter() will execute, and the
        //predicate will test.
        return new InfiniteListImpl<T>(newHead, newTail);
    }

    public boolean isEmpty() {
        if (this instanceof EmptyList) {
            return true;
        } else {
            return false;
        }
    }

    //TERMINAL OPERATIONS

    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> current = this;
        while (!(current instanceof EmptyList)) {
            Optional<T> optionalValue = current.getHead().get();

            if (optionalValue.isPresent() == true) {
                action.accept(optionalValue.get());
            }

            current = current.getTail().get();
        }
    }

    public Object[] toArray() {
        ArrayList<T> arrayList = new ArrayList<>();
        InfiniteListImpl<T> current = this;

        while (!(current instanceof EmptyList)) {
            Optional<T> optionalValue = current.getHead().get();

            if (optionalValue.isPresent() == true) {
                arrayList.add(optionalValue.get());
            }

            current = current.getTail().get();
        }

        return arrayList.toArray();
    }

    public long count() {
        InfiniteListImpl<T> current = this;
        long counter = 0;

        while (!(current instanceof EmptyList)) {
            Optional<T> optionalValue = current.getHead().get();

            if (optionalValue.isPresent() == true) {
                counter = counter + 1;
            }

            current = current.getTail().get();
        }

        return counter;
    }

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        InfiniteListImpl<T> current = this;
        while (!(current instanceof EmptyList)) {
            Optional<T> optionalValue = current.getHead().get();

            if (optionalValue.isPresent() == true) {
                identity = accumulator.apply(identity, optionalValue.get());
            }

            current = current.getTail().get();
        }

        return identity;
    }

}
