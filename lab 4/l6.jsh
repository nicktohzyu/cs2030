/open Box.java
/open BooleanCondition.java
/open DivisibleBy.java
/open LongerThan.java
/open LastDigitsOfHashCode.java
/open Transformer.java
/open BoxIt.java
class A {}
Box<Box<A>> a = Box.of(new A()).map(new BoxIt<>())
//Box.of(4).map(new BoxIt<>())
//Box.of(Box.of(5)).map(new BoxIt<>())
//Box.ofNullable(null).map(new BoxIt<>())
/exit
