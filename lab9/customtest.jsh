/open InfiniteList.java
/open Lazy.java
/open InfiniteListImpl.java
/open EmptyList.java

InfiniteList<Integer> list, list2
Supplier<Integer> generator = () -> { System.out.println("generate: 1"); return 1; }
Function<Integer,Integer> doubler = x -> { System.out.printf("map: %d -> %d\n", x, x * 2); return x * 2; };
Function<Integer,Integer> oneLess = x -> { System.out.printf("map: %d -> %d\n", x, x - 1); return x - 1; };
InfiniteList.generate(generator)
