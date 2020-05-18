class Box<T> {
    private final T obj;

    Box(T obj) {
        this.obj = obj;
    }

    public static final Box<?> EMPTY_BOX = new Box<>(null);

    boolean isPresent() {
        if (this.obj != null) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    static <T> Box<T> empty() {
        return (Box<T>) EMPTY_BOX;
    }

    static <T> Box<T> of(T item) {
        if (item == null) {
            return null;
        } else {
            return new Box<T>(item);
        }
    }

    static <T> Box<T> ofNullable(T item) {
        if (item == null) {
            return empty();
        } else {
            return new Box<T>(item);
        }
    }

    Box<T> filter(BooleanCondition<? super T> bool) {
        if (this == empty()) {
            return empty();
        } else if (bool.test(this.obj) == true) {
            return this;
        } else {
            return empty();
        }
    }

    <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
        if (this == empty()) {
            return empty();
        } else {
            return Box.ofNullable(transformer.transform(this.obj));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (other instanceof Box) {
            if (this == EMPTY_BOX && other == EMPTY_BOX) {
                return true;
            } else if (this == EMPTY_BOX || other == EMPTY_BOX) {
                return false;
            } else if (this == other) {
                return true;
            } else if (((Box<T>) this).obj.equals(((Box<T>) other).obj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (obj == null) {
            return "[]";
        } else {
            return "[" + obj + "]";
        }
    }
}
