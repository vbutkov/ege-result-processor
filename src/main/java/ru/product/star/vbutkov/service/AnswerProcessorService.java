package ru.product.star.vbutkov.service;

import ru.product.star.vbutkov.entity.Answer;

import java.util.List;

public interface AnswerProcessorService {

    List<Answer> getRightAnswers(List<Answer> rightAnswers, List<Answer> personAnswers);

    int getSummaryNumberOfPoints(List<Answer> answers);
}
