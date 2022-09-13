package ru.gb.naumovets.infoFromLessons.lesson7;

public class Cat {
    public String name;
    public String color;
    public int age;
    private int weight;

    public Cat(){}

    public Cat(int weight){
        this.weight = weight;
    }

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }
    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Cat(String name) {
        this.name = name;
    }

    @AdvancedAnnotation
    public void info(){
        System.out.println("weight: " + weight);
    }

    public void meow(int dB) {
        System.out.println(name + ": meow - " + dB + " dB");
    }

    @Override
    public String toString(){
        return name + " " + color + " " + age;
    }
}
