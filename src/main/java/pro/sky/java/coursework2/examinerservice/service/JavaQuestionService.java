package pro.sky.java.coursework2.examinerservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.examinerservice.domain.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        add("Что такое JVM", "JVM-виртуальная машина,которая обеспечивает среду для выполнения Java-байт кода.");
        add("В чем разница между Spring, StringBuilder и StringBuffer?", "String-неизменяемый класс.StringBuilder-изменяемый,не потокобезопасный.StringBuffer-изменяемый и потокобезопасный.");
        add("Что такое инкапсуляция?", "Это механизм объединения данных и методов в единый блок(класс)и скрытие внутренней реализации от внешнего мира.");
        add("В чем разница между ArrayList и LinkedList?", "ArrayList основан на массиве(быстрый доступ по индексу).LinkedList основан на двусвязном списке(быстрое добавление/удаление).");
        add("Что такое перегрузка(overloading)и переопределение(overriding)методов?", "Перегрузка-это методы с одним именем,но разными параметрами в одном классе.Переопределение-это изменение поведения метода родительского класса в дочернем.");
        add("Для чего используется ключевое слово final?", "Оно делает переменную константой(значение нельзя изменить),метод-запрещенным к переопределению,а класс запрещенным к наследованию.");
        add("Что такое интерфейс в Java?", "Это контракт,который определяет набор методов,которые должны реализовать класс.");
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(random.nextInt(questionList.size()));
    }

}
