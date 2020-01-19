package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * Option Data Transfer Object
 * @author Valeria Moscoso
 * */
public class OptionDTO {

    private int id;
    private String letter;
    private String translation;
    private int is_vowel;
    private String description;

    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public String getTranslation() {
        return translation;
    }

    public int getIs_vowel() {
        return is_vowel;
    }

    public String getDescription() {
        return description;
    }
}
