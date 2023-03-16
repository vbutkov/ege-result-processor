package ru.product.star.vbutkov.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product.star.vbutkov.entity.Answer;
import ru.product.star.vbutkov.entity.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryAnswerProcessorServiceTest {

    AnswerProcessorService answerService;

    private final static int countAnswers = 10;
    private final static List<Answer> rightAnswers = new ArrayList<>();
    private final static List<Answer> personAnswer = new ArrayList<>();
    private final static List<Answer> personRightAnswers = new ArrayList<>();
    private static int summaryNumberOfPoints = 0;

    private final static Map<Integer, Integer> numberOfPoints = new HashMap<>();


    @BeforeAll
    static void init() {

        initNumberOfPointsForAnswer();
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

    private static void initNumberOfPointsForAnswer() {
        int numberOfPoint = 0;
        for (int i = 1; i <= countAnswers; i++) {
            if (i > 3 && i < 9)
                numberOfPoint = 2;
            else if (i > 9) {
                numberOfPoint = 3;
            } else numberOfPoint = 1;

            numberOfPoints.put(i, numberOfPoint);
        }
    }

    private static void initRightAnswer() {
        for (int i = 1; i <= countAnswers; i++) {
            rightAnswers.add(new Answer(i, Letter.А, numberOfPoints.get(i)));
        }
    }

    private static void initPersonAnswers() {
        personAnswer.add(new Answer(1, Letter.Б, 1));
        personAnswer.add(new Answer(2, Letter.Б, 1));
        for (int i = 3; i <= 8; i++) {
            personAnswer.add(new Answer(i, Letter.А, numberOfPoints.get(i)));
        }
        personAnswer.add(new Answer(9, Letter.Б, 3));
        personAnswer.add(new Answer(10, Letter.А, 3));
    }

    private static void initPersonRightAnswers() {
        for (int i = 3; i <= 8; i++) {
            personRightAnswers.add(new Answer(i, Letter.А, numberOfPoints.get(i)));
            summaryNumberOfPoints += numberOfPoints.get(i);
        }
        personRightAnswers.add(new Answer(10, Letter.А, 3));
        summaryNumberOfPoints += 3;
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