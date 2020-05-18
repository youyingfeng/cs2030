class Pair {
    private final int key;
    private final int value;

    Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public int getGCD() {
        if (key == 0) {
            return value;
        } else {
            return key;
        }
    }
}
