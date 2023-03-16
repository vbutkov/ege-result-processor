package ru.product.star.vbutkov.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.product.star.vbutkov.entity.Answer;
import ru.product.star.vbutkov.utils.PropertiesInitializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryReaderServiceTest {

    private final String ANSWERS_FILE = "answers.txt";
    private final String ANSWERS_FILE_WRONG_FORMAT = "answers_wrong_format.txt";
    private final String ANSWERS_EMPTY_FILE = "answers_empty.txt";
    private final String ANSWERS_FILE_NOT_FOUND = "";

    ReaderService readerService;

    private static HashMap<String, List<Integer>> answerGroups = new HashMap<>();
    private static HashMap<String, Integer> countPointsOfGroups = new HashMap<>();

    @BeforeAll
    static void initProperties() {
        answerGroups.put("group1", List.of(1, 2, 3));
        answerGroups.put("group2", List.of(4, 5, 6, 7, 8));
        answerGroups.put("group3", List.of(9, 10));

        countPointsOfGroups.put("group1", 1);
        countPointsOfGroups.put("group2", 2);
        countPointsOfGroups.put("group3", 3);
    }

    @BeforeEach
    void createContext() {
        PropertiesInitializer properties = new PropertiesInitializer(answerGroups, countPointsOfGroups);
        MapperAnswer mapperAnswer = new MapperAnswer(properties);
        readerService = new InMemoryReaderService(mapperAnswer);
    }

    @Test
    void answersNotEmptyIfAnswersSuccessReadFromFile() {
        var answers = readerService.readAnswersFromFile(ANSWERS_EMPTY_FILE);
        assertTrue(answers.isEmpty(), "File with answers is not be empty");
    }

    @Test
    void throwExceptionIfFileNotFound() {
        assertThrows(RuntimeException.class, () -> readerService.readAnswersFromFile(ANSWERS_FILE_NOT_FOUND));
    }

    @Test
    void throwExceptionIfFileWrongFormat() {
        assertThrows(RuntimeException.class, () -> readerService.readAnswersFromFile(ANSWERS_FILE_WRONG_FORMAT));
    }

    @Test
    void answersSizeIfAnswersSuccessReadFromFile() {
        var answers = readerService.readAnswersFromFile(ANSWERS_FILE);
        assertEquals(10, answers.size());
    }

}