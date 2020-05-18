class Statistics {
    private final int sum;
    private final int count;
    private final int min;
    private final int max;

    Statistics() {
        this.sum = 0;
        this.count = 0;
        this.min = 0;
        this.max = 0;
    }

    Statistics(int number) {
        this.sum = number;
        this.count = 1;
        this.min = number;
        this.max = number;
    }

    Statistics(int sum, int count, int min, int max) {
        this.sum = sum;
        this.count = count;
        this.min = min;
        this.max = max;
    }

    public int getSum() {
        return sum;
    }

    public Statistics accept(Integer number) {
        if (count == 0) {
            return new Statistics(number, count + 1, number, number);
        } else if (number > max) {
            return new Statistics(this.sum + number, count + 1, this.min, number);
        } else if (number < min) {
            return new Statistics(this.sum + number, count + 1, number, this.max);
        } else {
            return new Statistics(this.sum + number, count + 1, this.min, this.max);
        }
    }

    public Statistics combine(Statistics stats) {
        return this.accept(stats.getSum());
    }

    public double getNormalizedMean() {
        if (min == max || count == 0) {
            return 0.0;
        } else {
            double mean = (double) sum / count;
            return (mean - this.min) / (this.max - this.min);
        }
    }
}
