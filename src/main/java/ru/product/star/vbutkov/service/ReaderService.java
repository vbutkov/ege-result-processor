package ru.product.star.vbutkov.service;

import ru.product.star.vbutkov.entity.Answer;

import java.io.IOException;
import java.util.List;

public interface ReaderService {
    List<Answer> readAnswersFromFile(String fileName);
}
