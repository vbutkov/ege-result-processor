package ru.product.star.vbutkov.entity;

import java.util.Objects;

public class Answer {
    private int id;
    private Letter letter;
    private int numberOfPoint;


    public Answer(int id, Letter letter, int countPoint) {
        this.id = id;
        this.letter = letter;
        this.numberOfPoint = countPoint;
    }


    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter.name();
    }

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", letter=" + letter +
                ", numberOfPoint=" + numberOfPoint +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id && numberOfPoint == answer.numberOfPoint && letter == answer.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, letter, numberOfPoint);
    }
}
