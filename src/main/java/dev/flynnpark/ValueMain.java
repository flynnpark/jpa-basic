package dev.flynnpark;

public class ValueMain {
    public static void main(String[] args){
        Integer a = new Integer(10);
        Integer b = a;
        // a.setValue(20); // Integer는 불변 객체이므로 값을 변경할 수 없습니다.
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
