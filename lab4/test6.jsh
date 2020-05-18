/open Box.java
/open BooleanCondition.java
/open DivisibleBy.java
/open LongerThan.java
/open Transformer.java
/open LastDigitsOfHashCode.java
/open BoxIt.java
Box.of(4).map(new BoxIt<>())
Box.of(Box.of(5)).map(new BoxIt<>())
Box.ofNullable(null).map(new BoxIt<>())
/exit
