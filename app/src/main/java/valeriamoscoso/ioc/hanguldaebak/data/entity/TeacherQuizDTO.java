package valeriamoscoso.ioc.hanguldaebak.data.entity;

import java.util.List;

/**
 * TeacherQuiz Data Transfer Object
 * @author Valeria Moscoso
 * */
public class TeacherQuizDTO {

    private int id;
    private String description;
    private String created_at;
    private String updated_at;
    private int professor_id;
    private GradeBookDTO gradebook;
    private List<NewQuestionDTO> questions;

    public TeacherQuizDTO(int id, String description, String created_at, String updated_at, int professor_id, GradeBookDTO gradebook, List<NewQuestionDTO> questions) {
        this.id = id;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.professor_id = professor_id;
        this.gradebook = gradebook;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getProfessor_id() {
        return professor_id;
    }

    public GradeBookDTO getGradebook() {
        return gradebook;
    }

    public List<NewQuestionDTO> getQuestions() {
        return questions;
    }
}
