package ru.product.star.vbutkov.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@PropertySource("classpath:application.config.properties")
public class PropertiesInitializer {
    private final HashMap<String, List<Integer>> answerGroups;
    private final HashMap<String, Integer> numberPointGroups;

    public PropertiesInitializer(
            @Value("#{${answers.of_groups}}") HashMap<String, List<Integer>> answerInGroups,
            @Value("#{${number.point.in.groups}}") HashMap<String, Integer> numberPointOfGroups) {

        this.answerGroups = answerInGroups;
        this.numberPointGroups = numberPointOfGroups;
    }

    public HashMap<String, List<Integer>> getAnswerGroups() {
        return answerGroups;
    }

    public HashMap<String, Integer> getNumberPointGroups() {
        return numberPointGroups;
    }

}
