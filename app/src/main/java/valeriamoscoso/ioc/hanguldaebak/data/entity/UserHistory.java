package valeriamoscoso.ioc.hanguldaebak.data.entity;

public class UserHistory {

    private int id;
    private int quiz_id;
    private int student_id;
    private int score;
    private int to_do;
    private String created_at;
    private String updated_at;

    public UserHistory(int id, int quiz_id, int student_id, int score, int to_do, String created_at, String updated_at) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.student_id = student_id;
        this.score = score;
        this.to_do = to_do;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTo_do() {
        return to_do;
    }

    public void setTo_do(int to_do) {
        this.to_do = to_do;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
