package test;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        int value1 = 3;
        int value2 = 5;
        int result = test.addNumber(value1, value2);
        System.out.println("result is：" + result);
    }

    public int addNumber(int a, int b) {
        return a + b;
    }
}
