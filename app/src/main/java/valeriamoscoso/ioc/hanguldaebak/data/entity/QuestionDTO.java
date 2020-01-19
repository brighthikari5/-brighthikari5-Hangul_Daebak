package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * DTO class for question
 * */
public class QuestionDTO {

    private int option_1;
    private int option_2;
    private int option_3;
    private int correct;
    private int answer;

    public QuestionDTO(int option_1, int option_2, int option_3, int correct, int answer) {
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.correct = correct;
        this.answer = answer;
    }

}
