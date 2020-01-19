package valeriamoscoso.ioc.hanguldaebak.data.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.data.entity.QuestionDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.QuizDTO;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.User;

/**
 * Class with methods that convert a domain quiz to dto quiz
 * @author Valeria Moscoso
 * */
public class MapperDomainToDto {
    public static QuizDTO mapperQuiz(Quiz quiz, User user) {

        int studentId = user.getStudentId();

        long date = System.currentTimeMillis();

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : quiz.getQuestions()) {
            questionDTOList.add(mapperQuestion(question));
        }

        return new QuizDTO(studentId, date, quiz.getScore(), questionDTOList);
    }

    private static QuestionDTO mapperQuestion(Question question) {

        List<Letter> questionAnswers = question.getAnswers();
        return new QuestionDTO(
                questionAnswers.get(0).getId(),
                questionAnswers.get(1).getId(),
                questionAnswers.get(2).getId(),
                question.getCorrectAnswer().getId(),
                question.getUserSelection().getId()
        );
    }}
