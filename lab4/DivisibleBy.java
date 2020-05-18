class DivisibleBy implements BooleanCondition<Integer> {
    private final int number;

    DivisibleBy(int number) {
        this.number = number;
    }

    @Override
    public boolean test(Integer obj) {
        if (obj % number == 0) {
            return true;
        } else {
            return false;
        }
    }
}
