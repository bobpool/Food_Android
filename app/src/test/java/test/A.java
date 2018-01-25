package test;

/**
 * Created by Osy on 2018-01-25.
 */

public class A {
    private B b;
    public A(B b) {
        this.b = b;
    }
    public int calc(int a, int b) {
        return this.b.calc(a ,b);
    }
}
