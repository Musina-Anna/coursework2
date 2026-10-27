package pro.sky.java.coursework2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.coursework2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> AllQuestions = questionService.getAll();

        if (amount > AllQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрошено больше вопросов ,чем есть в базе");
        }

        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество вопросов должно быть больше нуля");
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
