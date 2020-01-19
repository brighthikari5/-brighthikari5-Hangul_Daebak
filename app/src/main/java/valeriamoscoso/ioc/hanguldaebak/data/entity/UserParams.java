package valeriamoscoso.ioc.hanguldaebak.data.entity;

/**
 * User Parameters entity, it is used in Register process
 * @author Valeria Moscoso León
 * */
public class UserParams {

    private String name;
    private String surname;
    private String password;
    private String email;

    public UserParams(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
