package ru.product.star.vbutkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.product.star.vbutkov.entity.Answer;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryReaderService implements ReaderService {
    MapperAnswer mapperAnswer;

    @Autowired
    public InMemoryReaderService(MapperAnswer parserResult) {
        this.mapperAnswer = parserResult;
    }

    @Override
    public List<Answer> readAnswersFromFile(String fileName) {
        List<Answer> answers = new ArrayList<>();
        File file = getFileFromResourcesRoot(fileName);
        try (var reader = new BufferedReader(new FileReader(file))) {
            String answerFromFile = reader.readLine();
            while (answerFromFile != null && !answerFromFile.isBlank()) {
                Answer answer = mapperAnswer.createAnswer(answerFromFile);
                answers.add(answer);
                answerFromFile = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Error IO of file: " + fileName);
        }
        return answers;
    }

    private File getFileFromResourcesRoot(String fileName) {
        var resource = getClass().getClassLoader().getResource(fileName);
        var file = Path.of(resource.getPath()).toFile();
        return file;
    }


}
