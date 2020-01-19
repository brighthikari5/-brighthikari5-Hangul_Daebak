package valeriamoscoso.ioc.hanguldaebak.domain.entity;

import java.util.List;

public class Quiz {


    private int score =0;
    private List<Question> questions;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
