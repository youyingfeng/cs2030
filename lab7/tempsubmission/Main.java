import java.util.stream.Stream;

class Main {
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
}
