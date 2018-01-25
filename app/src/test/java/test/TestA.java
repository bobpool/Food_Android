package test;

/**
 * Created by Osy on 2018-01-25.
 */

public class TestA {

    public static void main(String args[]) {
        B b = new B();
        A a = new A(b);
        if (a.calc(1, 2) == 2) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }

    }
}
