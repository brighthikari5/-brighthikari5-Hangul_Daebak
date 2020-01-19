package valeriamoscoso.ioc.hanguldaebak.data.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.data.entity.NewQuestionDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.OptionDTO;
import valeriamoscoso.ioc.hanguldaebak.data.entity.TeacherQuizDTO;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

/**
 * Class with methods that convert dto quiz to domain quiz
 * @author Valeria Moscoso
 * */
public class MapperDtoToDomain {


    public static Quiz teacherQuizToQuiz(TeacherQuizDTO teacherQuizDTO) {

        return new Quiz(questionsDtoToDomain(teacherQuizDTO.getQuestions()));
    }

    private static List<Question> questionsDtoToDomain(List<NewQuestionDTO> questionsListDtos) {

        List<Question> questionList = new ArrayList<>();

        for (NewQuestionDTO questionDTO : questionsListDtos) {
            Question question = singleQuestionDtoToDomain(questionDTO);
            questionList.add(question);
        }

        return questionList;
    }

    private static Question singleQuestionDtoToDomain(NewQuestionDTO questionDTO) {

        List<Letter> letterList = new ArrayList<>();

        letterList.add(optionDtoToLetterDomain(questionDTO.getOption_1()));
        letterList.add(optionDtoToLetterDomain(questionDTO.getOption_2()));
        letterList.add(optionDtoToLetterDomain(questionDTO.getOption_3()));

        OptionDTO correctOptionDTO = findCorrectOptionInNewQuestionDto(questionDTO);

        return new Question(letterList, optionDtoToLetterDomain(correctOptionDTO));
    }

    private static OptionDTO findCorrectOptionInNewQuestionDto(NewQuestionDTO questionDTO) {


        OptionDTO optionDTO = null;

        switch (questionDTO.getCorrect()) {
            case 1:
                optionDTO = questionDTO.getOption_1();
                break;
            case 2:
                optionDTO = questionDTO.getOption_2();
                break;
            case 3:
                optionDTO = questionDTO.getOption_3();
                break;
        }

        return optionDTO;
    }

    private static Letter optionDtoToLetterDomain(OptionDTO option) {
        return new Letter(option.getLetter(), option.getTranslation(), option.getIs_vowel(), option.getDescription());
    }
}
