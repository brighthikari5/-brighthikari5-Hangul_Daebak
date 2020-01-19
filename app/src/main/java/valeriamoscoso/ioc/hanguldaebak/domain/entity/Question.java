package valeriamoscoso.ioc.hanguldaebak.domain.entity;

import java.util.List;

public class Question {

    private List<Letter> answers;
    private Letter correctAnswer;
    private Letter userSelection;
    private boolean isResponseCorrect;

    public Question(List<Letter> answers, Letter correctAnswer) {
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public List<Letter> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Letter> answers) {
        this.answers = answers;
    }

    public Letter getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Letter correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Letter getUserSelection() {
        return userSelection;
    }

    public void setUserSelection(Letter userSelection) {
        this.userSelection = userSelection;
    }

    public boolean isResponseCorrect() {
        return isResponseCorrect;
    }

    public void setResponseCorrect(boolean responseCorrect) {
        isResponseCorrect = responseCorrect;
    }
}
