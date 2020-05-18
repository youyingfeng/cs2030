import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;

class EmptyList<T> extends InfiniteListImpl<T> {

    public EmptyList() {
        super(Lazy.ofNullable(null), () -> new EmptyList<T>());
    }

    public boolean isEmpty() {
        return true;
    }

    public static <T> EmptyList<T> generate(Supplier<? extends T> s) {
        return new EmptyList<T>();
    }

    public static <T> EmptyList<T> iterate(T seed, UnaryOperator<T> next) {
        return new EmptyList<T>();
    }

    public <R> EmptyList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<R>();
    }

    public EmptyList<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    public EmptyList<T> peek() {
        return this;
    }

    public EmptyList<T> limit(long n) {
        return this;
    }

    public EmptyList<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }

    public void forEach(Consumer<? super T> action) {

    }

    public Object[] toArray() {
        return new Object[0];
    }

    public long count() {
        return 0;
    }

    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

}
