import java.util.stream.Stream;
import java.util.stream.IntStream;

class Main {
    static int[] twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                        .filter(x -> isPrime(x))
                        .filter(x -> isPrime(x + 2) || isPrime(x - 2))
                        .toArray();
    }

    static boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            //these ain't primes man
            return false;
        } else {
            return IntStream.range(2, n).noneMatch(x -> n % x == 0);
        }
    }

    static int gcd(int m, int n) {
        return Stream.iterate(new Pair(m, n), (Pair p) -> {
            int k = p.getKey();
            int v = p.getValue();

            if (k > v) {
                return new Pair(k % v, v);
            } else {
                return new Pair(k, v % k);
            }
        }).filter(p -> p.getKey() == 0 || p.getValue() == 0)
          .findFirst()
          .get()
          .getGCD();
    }

    static long countRepeats(int... array) {
        return IntStream.range(0, array.length)
                             .filter(x -> {
                                 if (x == 0) {
                                     return (array[x] == array[x + 1]);
                                 } else if (x == array.length - 1) {
                                     return (array[x] == array[x - 1]);
                                 } else {
                                     return (array[x] == array[x + 1] ||
                                            array[x] == array[x - 1]);
                                 }
                             })
                             .filter(x -> (x == array.length - 1 || array[x] != array[x + 1]))
                             .count();
    }

    static double normalizedMean(Stream<Integer> stream) {
        return stream.reduce(new Statistics(), (stats, number) -> stats.accept(number),
                            (x, y) -> x)
                     .getNormalizedMean();
        //the last lambda is a dummy used only for parallelisation.
    }
}
