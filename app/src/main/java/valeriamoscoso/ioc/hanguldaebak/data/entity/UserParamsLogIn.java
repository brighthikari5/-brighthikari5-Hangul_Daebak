package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * User parameters login entity, it is used in login process
 * @author Valeria Moscoso León
 * */
public class UserParamsLogIn {

    private String email;
    private String password;

    public UserParamsLogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
