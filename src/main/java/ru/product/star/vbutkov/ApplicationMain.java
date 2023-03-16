package ru.product.star.vbutkov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.product.star.vbutkov.service.InMemoryAnswerProcessorService;
import ru.product.star.vbutkov.service.InMemoryReaderService;
import ru.product.star.vbutkov.config.ApplicationConfig;

import java.io.IOException;

public class ApplicationMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        var readerService = context.getBean(InMemoryReaderService.class);
        var rightAnswers = readerService.readAnswersFromFile("rightAnswer.txt");
        var studentAnswers = readerService.readAnswersFromFile("personAnswer.txt");

        var answerService = context.getBean(InMemoryAnswerProcessorService.class);
        var personRightAnswers = answerService.getRightAnswers(rightAnswers, studentAnswers);
        var numberOfPoints = answerService.getSummaryNumberOfPoints(personRightAnswers);

        //System.out.println("Person right answers:" + studentRightAnswers);
        System.out.println("Person summary number of points: " + numberOfPoints);


    }
}
