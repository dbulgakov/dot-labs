package com.example.lab5;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private String name;
    private String groupNumber;

    public Student(String name, String groupNumber){
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public String getName(){
        return name;
    }

    public String getGroupNumber(){
        return groupNumber;
    }

    private final static ArrayList<Student> students = new ArrayList<Student>(
    Arrays.asList(
            new Student("Булгаков Дмитро", "К21-3"),
            new Student("Герасименко Антон", "К21-1"),
            new Student("Улянович Михайло", "К21-1"),
            new Student("Урбанович Ірина",  "К21-3"),
            new Student("Хоменко Назар", "К21-2"),
            new Student("Волошин Дмитро", "К21-2")
            )
    );

    public static ArrayList<Student> getStudents(){
        return getStudents("");
    }

    public static ArrayList<Student> getStudents(String groupNumber){
        ArrayList<Student> stList = new ArrayList<>();

        for (Student s : students){
            if(s.getGroupNumber().equals(groupNumber) || (groupNumber == "")){
                stList.add(s);
            }
        }

        return stList;
    }

    @Override
    public String toString(){
        return name;
    }
}
