package com.datamining;

/**
 * Created by wongleon on 2017/6/26.
 */
public class MyClass {
    private Object num;

    public MyClass(Object num) {
        this.num = num;
    }

    public Object getNum() {
        return num;
    }

    public void setNum(Object num) {
        this.num = num;
    }

    public static void change(MyClass myClass) {
        myClass.setNum(100);
    }

    public static void swap(MyClass a,MyClass b){
        MyClass tmp=a;
        a=b;
        b=tmp;
    }

    public static void main(String[] args){
        MyClass a=new MyClass(10);
        MyClass b=new MyClass(100);
        System.out.println("交换之前a的值为："+a.getNum());
        System.out.println("交换之前b的值为："+b.getNum());
        swap(a,b);
        System.out.println("交换之后a的值为："+a.getNum());
        System.out.println("交换之后b的值为："+b.getNum());
    }
}