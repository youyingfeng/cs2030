import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;

public class Lazy<T> {
    private final Supplier<? extends T> supplier;
    private T value;
    private boolean hasBeenComputed;

    Lazy(T value) {
        this.supplier = () -> value;
        this.value = value;
        this.hasBeenComputed = true;
    }

    Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
        this.hasBeenComputed = false;
    }

    Optional<T> get() {
        if (hasBeenComputed == false) {
            this.value = this.supplier.get();
            this.hasBeenComputed = true;
        }

        if (this.value == null) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

    static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<T>(v);
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<T>(supplier);
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        Supplier<R> newSupplier = () -> {
            Optional<T> optionalValue = this.get();

            return optionalValue.map(value -> mapper.apply(value)).orElse(null);
        };
        return new Lazy<R>(newSupplier);
    }

    Lazy<T> filter(Predicate<? super T> predicate) {
        Supplier<T> newSupplier = () -> {
            Optional<T> optionalValue = this.get();

            return optionalValue.map(value -> predicate.test(value) ? value : null)
                         .orElse(null);
        };
        return new Lazy<T>(newSupplier);
    }

    @Override
    public String toString() {
        if (hasBeenComputed == false) {
            return "?";
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
