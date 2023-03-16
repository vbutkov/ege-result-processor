package ru.product.star.vbutkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.product.star.vbutkov.entity.Answer;
import ru.product.star.vbutkov.entity.Letter;
import ru.product.star.vbutkov.utils.PropertiesInitializer;

import java.util.Optional;

@Service
public class MapperAnswer {

    private final PropertiesInitializer properties;

    @Autowired
    public MapperAnswer(PropertiesInitializer properties) {
        this.properties = properties;
    }

    public Answer createAnswer(String answerFromFile) {
        var tokens = answerFromFile.split("-");
        int id = Integer.parseInt(tokens[0].strip());
        Letter letter = Letter.valueOf(tokens[1].strip());

        var groupName = getGroupNameByAnswerId(id);
        int countPoint = getNumberPointByGroupName(groupName.get());

        var answer = new Answer(id, letter, countPoint);

        return answer;
    }

    private Optional<String> getGroupNameByAnswerId(int id) {
        for (var entries : properties.getAnswerGroups().entrySet()) {
            if (entries.getValue().contains(id)) {
                return Optional.ofNullable(entries.getKey());
            }
        }
        return Optional.empty();
    }

    private int getNumberPointByGroupName(String groupName) {
        return properties.getNumberPointGroups().get(groupName);
    }


}
