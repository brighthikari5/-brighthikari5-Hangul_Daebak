package valeriamoscoso.ioc.hanguldaebak.data.entity;

import java.util.List;
/**
 * DTO class for quiz
 * */
public class QuizDTO {
    private int student_id;
    private long date;
    private int score;
    private List<QuestionDTO> questions;

    public QuizDTO(int student_id, long date, int score, List<QuestionDTO> questions) {
        this.student_id = student_id;
        this.date = date;
        this.score = score;
        this.questions = questions;
    }
}
