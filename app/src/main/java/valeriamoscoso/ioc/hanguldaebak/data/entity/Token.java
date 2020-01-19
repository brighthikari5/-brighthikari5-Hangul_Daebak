package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * Class to save Token from server
 * @author Valeria Moscoso León
 * */
public class Token {

    private String token;

    public Token(String token) {
        this.token = token;
    }

    private Token(){}

    public String getToken() {
        return token;
    }
}
