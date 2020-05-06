String pattern(int n) {
     return IntStream.rangeClosed(1, n).mapToObj(x -> IntStream.rangeClosed(1, x).mapToObj(y -> Integer.toString(x-y+1)).collect(Collectors.joining())).collect(Collectors.joining(" "));
}
