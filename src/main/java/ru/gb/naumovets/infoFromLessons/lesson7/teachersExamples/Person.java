package ru.gb.naumovets.infoFromLessons.lesson7.teachersExamples;

@Anno(intArrayParam = {1, 2, 3})
public class Person {

    private static int counter = 0;

    private final String name;
    private int age;

    public Person(@Anno(1) String name) {
        this(name, 0);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    private int nameLength() {
        return name.length();
    }

    @Anno(param = "no-default-param", intArrayParam = {-1, -1, -1})
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[" + name + "; " + age + "]";
    }
}
