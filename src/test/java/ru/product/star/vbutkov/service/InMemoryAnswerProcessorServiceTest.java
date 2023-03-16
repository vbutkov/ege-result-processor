package ru.product.star.vbutkov.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product.star.vbutkov.entity.Answer;
import ru.product.star.vbutkov.entity.Letter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryAnswerProcessorServiceTest {

    AnswerProcessorService answerService;

    private final static int countAnswers = 10;
    private final static List<Answer> rightAnswers = new ArrayList<>();
    private final static List<Answer> personAnswer = new ArrayList<>();
    private final static List<Answer> personRightAnswers = new ArrayList<>();
    private static int summaryNumberOfPoints = 0;


    @BeforeAll
    static void init() {

        initRightAnswer();
        initPersonAnswers();
        initPersonRightAnswers();

//      Вариант инициализации 3х коллекций

//        int numberOfPoint = 1;
//        Letter letterRight = Letter.А;
//        for (int i = 1; i <= countAnswers; i++) {
//
//            Letter letterPerson = letterRight;
//            if (i <= 3) {
//                personRightAnswers.add(new Answer(i, letterRight, numberOfPoint));
//                summaryNumberOfPoints += numberOfPoint;
//            }
//            // Wrong Letter, that pass to add Answer to personRightAnswers
//            else if (i >= 4 && i < 9) {
//                letterPerson = Letter.Б;
//                numberOfPoint = 2;
//
//            } else if (i >= 9) {
//                numberOfPoint = 3;
//                personRightAnswers.add(new Answer(i, letterRight, numberOfPoint));
//                summaryNumberOfPoints += numberOfPoint;
//            }
//
//            rightAnswers.add(new Answer(i, letterRight, numberOfPoint));
//            personAnswer.add(new Answer(i, letterPerson, numberOfPoint));
//        }
    }

    private static void initRightAnswer() {
        rightAnswers.add(new Answer(1, Letter.А, 1));
        rightAnswers.add(new Answer(2, Letter.А, 1));
        rightAnswers.add(new Answer(3, Letter.А, 1));
        rightAnswers.add(new Answer(4, Letter.А, 2));
        rightAnswers.add(new Answer(5, Letter.А, 2));
        rightAnswers.add(new Answer(6, Letter.А, 2));
        rightAnswers.add(new Answer(7, Letter.А, 2));
        rightAnswers.add(new Answer(8, Letter.А, 2));
        rightAnswers.add(new Answer(9, Letter.А, 3));
        rightAnswers.add(new Answer(10, Letter.А, 3));
    }

    private static void initPersonAnswers() {
        personAnswer.add(new Answer(1, Letter.Б, 1));
        personAnswer.add(new Answer(2, Letter.Б, 1));
        personAnswer.add(new Answer(3, Letter.А, 1));
        personAnswer.add(new Answer(4, Letter.А, 2));
        personAnswer.add(new Answer(5, Letter.А, 2));
        personAnswer.add(new Answer(6, Letter.А, 2));
        personAnswer.add(new Answer(7, Letter.А, 2));
        personAnswer.add(new Answer(8, Letter.А, 2));
        personAnswer.add(new Answer(9, Letter.Б, 3));
        personAnswer.add(new Answer(10, Letter.А, 3));
    }

    private static void initPersonRightAnswers() {
        personRightAnswers.add(new Answer(3, Letter.А, 1));
        personRightAnswers.add(new Answer(4, Letter.А, 2));
        personRightAnswers.add(new Answer(5, Letter.А, 2));
        personRightAnswers.add(new Answer(6, Letter.А, 2));
        personRightAnswers.add(new Answer(7, Letter.А, 2));
        personRightAnswers.add(new Answer(8, Letter.А, 2));
        personRightAnswers.add(new Answer(10, Letter.А, 3));
        summaryNumberOfPoints = 14;
    }

    @BeforeEach
    void createContext() {
        answerService = new InMemoryAnswerProcessorService();
    }

    @Test
    void getRightAnswersOfReadFromFileAnswers() {
        var resultPersonRightAnswer = answerService.getRightAnswers(rightAnswers, personAnswer);
        assertEquals(personRightAnswers, resultPersonRightAnswer);
    }

    @Test
    void getSummaryNumberOfPointsOfReadFromFileAnswers() {
        var resultSummaryNumberOfPoints = answerService.getSummaryNumberOfPoints(personRightAnswers);
        assertEquals(summaryNumberOfPoints, resultSummaryNumberOfPoints);
    }
}