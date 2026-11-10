package pro.sky.java.coursework2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.coursework2.examinerservice.domain.Question;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService service;

    @BeforeEach
    void setUp() {
        service=new JavaQuestionService();
    }

    @Test
    void addAndGetAllTest(){
        service.add("Q1","A1");
        service.add(new Question("Q2","A2"));
        assertEquals(2,service.getAll().size());
    }
    @Test
    void addDuplicateTest(){
        service.add("Q1","A1");
        service.add("Q1","A1");
        assertEquals(1,service.getAll().size());
    }
    @Test
    void removeTest(){
        Question q=new Question("Q1","A1");
        service.add(q);
        assertEquals(q,service.remove(q));
        assertTrue(service.getAll().isEmpty());
    }
    @Test
    void removeEmptyTest(){
        assertNull(service.remove(new Question("Q1","A1")));
    }
    @Test
    void getRandomQuestionTest(){
        assertNull(service.getRandomQuestion());
        service.add("Q1","A1");
        assertNotNull(service.getRandomQuestion());
    }
}