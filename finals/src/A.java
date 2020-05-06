class A implements I {

    void foo(A a) {
        System.out.println("1");
    }

    void foo(I i) {
        System.out.println("2");
    }

    public static void main(String[] args) {
//    A a = new A();
//    A ab = new B();
//    B b = new B();
//    I i = b;
//    a.foo(a);
//    b.foo(b);
//    b.foo(ab);
//    b.foo(i);
        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class B extends A {

    void foo(A a) {
        System.out.println("3");
    }


    void foo(I i) {
        System.out.println("4");
    }

    void foo(B b) {
        System.out.println("5");
    }
}

interface I {

}