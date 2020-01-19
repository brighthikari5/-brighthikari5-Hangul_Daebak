package valeriamoscoso.ioc.hanguldaebak.domain.repository;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

public interface QuizRepository {

    Boolean sendQuiz(Quiz quiz);

    Quiz getTeacherQuiz();
}
