package com.study.model;

public class Student {
    private int id;

    private String userName;
    private int age;
    private String sex;

    //2020-01-21
    private int max;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", max=" + max +
                ", total=" + total +
                '}';
    }

    public Student(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public Student() {
    }

    public Student(String userName, int age, String sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }
}
