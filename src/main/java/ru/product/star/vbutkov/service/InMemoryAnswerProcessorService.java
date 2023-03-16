package ru.product.star.vbutkov.service;

import org.springframework.stereotype.Service;
import ru.product.star.vbutkov.entity.Answer;

import java.util.List;

@Service
public class InMemoryAnswerProcessorService implements AnswerProcessorService {

    @Override
    public List<Answer> getRightAnswers(List<Answer> rightAnswers, List<Answer> personAnswers) {
        rightAnswers.retainAll(personAnswers);
        return rightAnswers;
    }

    @Override
    public int getSummaryNumberOfPoints(List<Answer> answers) {
        var summaryNumberOfPoint = answers.stream()
                .mapToInt(i -> i.getNumberOfPoint())
                .sum();
        return summaryNumberOfPoint;
    }

}
