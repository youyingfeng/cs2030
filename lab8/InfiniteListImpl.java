import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
import java.util.ArrayList;

abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    public abstract Optional<T> get();

    static <T> InfiniteListImpl<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            @Override
            public Optional<T> get() {
                return Optional.ofNullable(supplier.get());
            }
        };
    }

    static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            T element = seed;
            boolean first = true;

            @Override
            public Optional<T> get() {
                if (first == true) {
                    first = false;
                } else {
                    element = f.apply(element);
                }

                return Optional.ofNullable(element);
            }
        };
    }

    public InfiniteListImpl<T> limit(long maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.valueOf(maxSize));
        } else {
            return new InfiniteListImpl<T>() {
                long count = 0;

                @Override
                public Optional<T> get() {
                    if (count < maxSize) {
                        count++;
                        return InfiniteListImpl.this.get();
                    } else {
                        return Optional.empty();
                    }
                }
            };
        }
    }

    public void forEach(Consumer<? super T> action) {
        Optional<T> item = this.get();
        while (item.isPresent() == true) {
            action.accept(item.get());
            item = this.get();
        }
    }

    public Object[] toArray() {
        ArrayList<Object> tempList = new ArrayList<>();
        Optional<T> item = this.get();
        while (item.isPresent() == true) {
            tempList.add(item.get());
            item = this.get();
        }

        return tempList.toArray();
    }

    public <S> InfiniteListImpl<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            @Override
            public Optional<S> get() {
                Optional<T> item = InfiniteListImpl.this.get();
                return item.map(mapper);
            }
        };
    }

    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            @Override
            public Optional<T> get() {
                Optional<T> item = InfiniteListImpl.this.get();

                while (item.isPresent() && predicate.test(item.get()) != true) {
                    item = InfiniteListImpl.this.get();
                }

                return item;
            }
        };
    }

    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            @Override
            public Optional<T> get() {
                Optional<T> item = InfiniteListImpl.this.get();
                return item.filter(predicate);
            }
        };
    }

    public long count() {
        long counter = 0;
        Optional<T> item = this.get();
        while (item.isPresent() == true) {
            counter = counter + 1;
            item = this.get();
        }
        return counter;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        //single variable reduce
        Optional<T> optionalIdentity = this.get();
        Optional<T> item = this.get();

        if (optionalIdentity.isEmpty() == true) {
            return Optional.empty();
        } else {
            T identity = optionalIdentity.get();
            while (item.isPresent() == true) {
                identity = accumulator.apply(identity, item.get());
                item = this.get();
            }
            return Optional.ofNullable(identity);
        }
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        Optional<T> item = this.get();
        while (item.isPresent() == true) {
            identity = accumulator.apply(identity, item.get());
            item = this.get();
        }
        return identity;
    }
}
