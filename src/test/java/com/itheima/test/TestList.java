package com.itheima.test;

import java.util.*;

/*
 * @Author:  Yajun_Xu
 * @Create: 2019-06-07 11:32
 **/
public class TestList {
    public static void main(String[] args) {
        //Persion persionFirst = new Persion ("张三",11);
        // Persion persionSecond = new Persion ("张三",10);
        HashMap hashMap = new HashMap ();
        hashMap.put ("name","张三");
        hashMap.put ("age","21");

        HashMap hashMap1 = new HashMap ();
        hashMap1.put ("name","张三");
        hashMap1.put ("age","21");

        List arr = new ArrayList ();
        arr.add (hashMap);
        arr.add (hashMap1);
        Set arrSet = new HashSet (arr);

        System.out.println (arr.size () == arrSet.size ());
    }


}

class Persion{
    private String name ;
    private Integer age ;

    public Persion() {

    }
    public Persion (String name ,Integer age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Persion persion = (Persion) o;
        return Objects.equals (name, persion.name) &&
                Objects.equals (age, persion.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash (name, age);
    }
}
