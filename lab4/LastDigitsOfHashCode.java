class LastDigitsOfHashCode implements Transformer<Object, Integer> {
    private final int k;

    LastDigitsOfHashCode(int k) {
        this.k = k;
    }

    public Integer transform(Object obj) {
        int hash = obj.hashCode();
        int result = 0;
        for (int i = 0; i < k; i++) {
            result = result + Math.abs((hash % 10) * ((int) Math.pow(10, i)));
            hash = hash / 10;
        }
        return result;
    }
}
