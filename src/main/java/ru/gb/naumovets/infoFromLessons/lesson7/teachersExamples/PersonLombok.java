package ru.gb.naumovets.infoFromLessons.lesson7.teachersExamples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
public class PersonLombok {

    @Getter
    private final String name;
    @Getter
    @Setter
    private int age;

    private int nameLength() {
        return name.length();
    }

    @Override
    public String toString() {
        return "[" + name + "; " + age + "]";
    }

}
