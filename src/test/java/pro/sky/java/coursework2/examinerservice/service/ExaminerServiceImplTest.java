package pro.sky.java.coursework2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.coursework2.examinerservice.domain.Question;

import static org.junit.jupiter.api.Assertions.*;

class ExaminerServiceImplTest {
    private JavaQuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp(){
        questionService=new JavaQuestionService();
        examinerService=new ExaminerServiceImpl(questionService);
    }
    @Test
    void getQuestionsBadRequestTest(){
        questionService.add("Q1","A1");
        ResponseStatusException exception=assertThrows(ResponseStatusException.class,()->{
            examinerService.getQuestions(5);
        });
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());
    }

    @Test
    void getQuestionsZeroAmountTest(){
        ResponseStatusException exception=assertThrows(ResponseStatusException.class,()->{
            examinerService.getQuestions(0);
        });
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());
    }
    @Test
    void getQuestionsSuccessTest(){
        questionService.add("Q1","A1");
        questionService.add("Q2","A2");
        assertEquals(2,examinerService.getQuestions(2).size());
    }


}