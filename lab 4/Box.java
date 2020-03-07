class Box<T> {
	private final T t;
    private final static Box<?> EMPTY_BOX =  new Box<>(null);
	private Box(T t) {
		this.t = t;
	}

    @SuppressWarnings("unchecked") 
    public static <T> Box<T> empty(){
        return (Box<T>) EMPTY_BOX;
    }

    public boolean isPresent(){
        return t != null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Box<T> ofNullable(T t){
        return t==null ? (Box<T>) EMPTY_BOX : new Box<>(t);
    }

	static <T> Box<T> of(T t) {
		return t==null ? null : new Box<>(t);
	}

	public T get() {
		return t;
	}

	@Override
		public String toString() {
			return "[" + (t==null ? "" : t.toString()) + "]";
		}
    @Override
		public boolean equals(Object box){
        //    System.out.println("call equals");
        //    System.out.println(t.toString());
        //    System.out.println(((Box<?>)box).get().toString());
			if(box instanceof Box){
                Object u = ((Box<?>)box).get();
                return t==null? u==null : t == u || t.equals(u);
            }
            return false;
		}

    public Box<T> filter(BooleanCondition<? super T> tester){
        return  t!=null && tester.test(t) ? this : empty();
    }

 //   public Box<?> map(Transformer<? super T, ?> transformer){
 //       return Box<?>(transformer.transform(t));
 //   }
    public <U> Box<U> map(Transformer<? super T, U> transformer){
        return t == null ? empty() : new Box<U>(transformer.transform(t));
    }

}
