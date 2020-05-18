class BoxIt<T> implements Transformer<T, Box<T>> {
    BoxIt() {
    }

    public Box<T> transform(T obj) {
        return Box.ofNullable(obj);
    }
}
