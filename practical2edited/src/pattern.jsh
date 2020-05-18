String pattern(int n) {
   return Stream.iterate(1, x -> x == x, y -> y + 1)
                .limit(n)
                .map(x -> Stream.iterate(x, i -> i > 0, j -> j - 1).limit(x))
                .map(x -> x.reduce("", (a, b) -> a + b, (a,b) -> a + b))
                .reduce("", (a,b) -> a + b + "\n", (a,b) -> a + b);
}
