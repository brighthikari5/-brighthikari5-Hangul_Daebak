package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * NewQuestion Data Transfer Object
 * @author Valeria Moscoso
 * */
public class NewQuestionDTO {
    private int id;
    private OptionDTO option_1;
    private OptionDTO option_2;
    private OptionDTO option_3;
    private int correct;
    private int answer;

    public int getId() {
        return id;
    }

    public OptionDTO getOption_1() {
        return option_1;
    }

    public OptionDTO getOption_2() {
        return option_2;
    }

    public OptionDTO getOption_3() {
        return option_3;
    }

    public int getCorrect() {
        return correct;
    }

    public int getAnswer() {
        return answer;
    }
}
