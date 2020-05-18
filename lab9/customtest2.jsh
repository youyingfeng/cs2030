/open InfiniteList.java
/open Lazy.java
/open InfiniteListImpl.java
/open EmptyList.java

InfiniteList<Integer> list, list2
Supplier<Integer> generator = () -> { System.out.println("generate: null"); return null; }
Function<Integer,Integer> doubler = x -> { System.out.printf("map: %d -> %d\n", x, x * 2); return x * 2; };
Function<Integer,Integer> oneLess = x -> { System.out.printf("map: %d -> %d\n", x, x - 1); return x - 1; };
InfiniteList.generate(generator)
InfiniteList.generate(generator).filter(x -> x == 1)
InfiniteList.generate(generator).takeWhile(x -> x == 1).forEach(x -> System.out.println("kappa"));
