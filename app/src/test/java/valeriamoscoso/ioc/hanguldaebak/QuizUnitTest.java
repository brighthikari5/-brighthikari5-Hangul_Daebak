package valeriamoscoso.ioc.hanguldaebak;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import valeriamoscoso.ioc.hanguldaebak.data.entity.TeacherQuizDTO;
import valeriamoscoso.ioc.hanguldaebak.data.repository.mapper.MapperDtoToDomain;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;
import valeriamoscoso.ioc.hanguldaebak.domain.utils.QuizUtils;

import static org.junit.Assert.assertEquals;


/**
 * Unit test class for Quiz
 * @author Valeria Moscoso León
 * */
public class QuizUnitTest {

    //Example Json of Teacher Quiz from Server
private String teacherQuizDTOJson = "{\n" +
        "    \"id\": 6,\n" +
        "    \"description \": \"Automatic Quiz\",\n" +
        "    \"created_at\": \"2019-11-16T15:57:04.000000Z\",\n" +
        "    \"updated_at\": \"2019-11-16T15:57:04.000000Z\",\n" +
        "    \"professor_id\": 0,\n" +
        "    \"gradebook\": {\n" +
        "        \"id\": 6,\n" +
        "        \"quiz_id\": 6,\n" +
        "        \"student_id\": 2,\n" +
        "        \"score\": 90,\n" +
        "        \"to_do\": 0,\n" +
        "        \"created_at\": \"2019-11-16 15:57:04\",\n" +
        "        \"updated_at\": \"2019-11-16 15:57:04\"\n" +
        "    },\n" +
        "    \"questions\": [\n" +
        "        {\n" +
        "            \"id\": 46,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 1\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 47,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 1\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 48,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 6,\n" +
        "                \"letter\": \"ㅛ\",\n" +
        "                \"translation\": \"yo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to yo as in Yoda\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 3\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 49,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 2\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 50,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 1,\n" +
        "            \"answer\": 1\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 51,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 3,\n" +
        "            \"answer\": 3\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 52,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 8,\n" +
        "                \"letter\": \"ㅠ\",\n" +
        "                \"translation\": \"yoo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yoo as un you\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 1\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 53,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 12,\n" +
        "                \"letter\": \"ㅒ\",\n" +
        "                \"translation\": \"yae\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to yae as in yam\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 3,\n" +
        "                \"letter\": \"ㅓ\",\n" +
        "                \"translation\": \"eo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to eo as in cut\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 1\n" +
        "        },\n" +
        "        {\n" +
        "            \"id\": 54,\n" +
        "            \"option_1\": {\n" +
        "                \"id\": 2,\n" +
        "                \"letter\": \"ㅑ\",\n" +
        "                \"translation\": \"ya\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to ya as in yard\"\n" +
        "            },\n" +
        "            \"option_2\": {\n" +
        "                \"id\": 5,\n" +
        "                \"letter\": \"ㅗ\",\n" +
        "                \"translation\": \"o\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar to o as in order\"\n" +
        "            },\n" +
        "            \"option_3\": {\n" +
        "                \"id\": 4,\n" +
        "                \"letter\": \"ㅕ\",\n" +
        "                \"translation\": \"yeo\",\n" +
        "                \"is_vowel\": 1,\n" +
        "                \"description\": \"The sound is similar yeo as in just\"\n" +
        "            },\n" +
        "            \"correct\": 2,\n" +
        "            \"answer\": 1\n" +
        "        }\n" +
        "    ]\n" +
        "}";

        Gson gson = new Gson();


    /**
     * Gets a random list of 10 of all the letters of the alphabet
     * */
    @Test
    public void getRandomList() {

        int size = 10;
        List<Letter> allLetters = getAllLetters();
        List<Letter> questionsLetterList = QuizUtils.getRandomLettersForQuiz(allLetters, size);

        assertEquals(size, questionsLetterList.size());
    }

    /**
     * Creates a quiz with random letters
     * */
    @Test
    public void createQuiz() {

        int size = 10;
        List<Letter> allLetters = getAllLetters();
        List<Letter> questionsLetterList = QuizUtils.getRandomLettersForQuiz(allLetters, size);

        Quiz quiz = QuizUtils.createQuiz(questionsLetterList, allLetters);

        int i = 0;
    }

    /**
     * Transforms Json to QuizTeacherDTO object and then to QuizTeacherDTOObjecto to Quiz.
     * */
    @Test
    public void fromTeacherQuizDTOToQuiz(){

        TeacherQuizDTO teacherQuizDTOObject = gson.fromJson(teacherQuizDTOJson, TeacherQuizDTO.class);

        Quiz quiz=  MapperDtoToDomain.teacherQuizToQuiz(teacherQuizDTOObject);

    }

    private List<Letter> getAllLetters() {

        InputStream in;

        in = Objects.requireNonNull(this.getClass().getClassLoader()).getResourceAsStream("letters.json");

        String jsonString = inputToString(in);

        return Arrays.asList(new Gson().fromJson(jsonString, Letter[].class));
    }


    private String inputToString(InputStream inputStream) {
        String data = "";
        try {

            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();

            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            data = total.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
