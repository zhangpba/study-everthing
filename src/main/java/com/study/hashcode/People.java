package com.study.hashcode;

import java.util.Objects;

public class People {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 在这里我只重写了equals方法，也就说如果两个People对象，如果它的姓名和年龄相等，则认为是同一个人
    // 这段代码本来的意愿是想这段代码输出结果为“1”，但是事实上它输出的是“null”。
    // 为什么呢？原因就在于重写equals方法的同时忘记重写hashCode方法。
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.name.equals(((People) o).name) && this.age == ((People) o).age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
