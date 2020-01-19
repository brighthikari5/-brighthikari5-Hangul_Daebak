package valeriamoscoso.ioc.hanguldaebak.domain.entity;

public class Letter {

    private int id;
    private String letter;
    private String translation;
    private int isVowel;
    private String description;

    public Letter(String letter, String translation, int isVowel, String description) {
        this.letter = letter;
        this.translation = translation;
        this.isVowel = isVowel;
        this.description = description;
    }

    public String getLetter() {
        return letter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getVowel() {
        return isVowel;
    }

    public void setVowel(int vowel) {
        isVowel = vowel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
