package com.study.annotations;

import java.lang.reflect.Method;

@CherryAnnotation
public class Student {

    @CherryAnnotation(name = "cherry-peng", age = 23, score = {99, 66, 77})
    public void study(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Good Good Study, Day Day Up!");
        }
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.study(2);


        Class<?> clazz = Student.class;
        Method[] methods = clazz.getMethods();


        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(CherryAnnotation.class)) {
                CherryAnnotation cherryAnnotation = clazz.getAnnotation(CherryAnnotation.class);

                System.out.println(cherryAnnotation.age() + ":" + cherryAnnotation.name() + ":" + cherryAnnotation.score());
            }
        }


    }
}
