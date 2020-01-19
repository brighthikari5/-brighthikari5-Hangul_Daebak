package valeriamoscoso.ioc.hanguldaebak.domain.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Question;
import valeriamoscoso.ioc.hanguldaebak.domain.entity.Quiz;

public class QuizUtils {

    private static int getRandom(int listSize, Set<Integer> selected) {

        int randomNum;

        do {
            Random random = new Random();
            randomNum = random.nextInt(listSize);

        } while (selected.contains(randomNum));

        return randomNum;
    }

    public static List<Letter> getRandomLettersForQuiz(List<Letter> allLetters, int size) {

        List<Letter> letterArray = new ArrayList<>();

        Set<Integer> selected = new LinkedHashSet<>();

        for (int i = 0; i < size; i++) {

            int newSelection = getRandom(allLetters.size(), selected);

            int letterPositionInList = getLetterPosition(allLetters, allLetters.get(newSelection));

            Letter letter = allLetters.get(letterPositionInList);

            letterArray.add(letter);

            selected.add(newSelection);
        }

        return letterArray;

    }

    private static Letter getRandomLetterForQuestion(List<Letter> allLetters, Set<Integer> selectedCards) {

        int random = getRandom(allLetters.size(), selectedCards);


        return allLetters.get(random);


    }


    private static int getLetterPosition(List<Letter> listLetter, Letter letter) {

        int positionInList = 0;
        int i = 0;
        boolean found = false;

        do {

            Letter letterInList = listLetter.get(i);

            if (letterInList.getId() == letter.getId()) {
                found = true;
                positionInList = i;
            }


            i++;
        } while (!found && i < listLetter.size());

        return positionInList;
    }

    public static Quiz createQuiz(List<Letter> questionsLetterList, List<Letter> allLetters) {

        List<Question> questions = new ArrayList<>();


        for (Letter correctLetter : questionsLetterList) {

            //cada letra de cada iteración es la respuesta correcta de la pregunta



            List<Letter> answers = new ArrayList<>();

            Set<Integer> selectedLetters = new LinkedHashSet<>();//guardamos aqui las posiciones de las resspuestas, siendo cada una su posicion en la lista de todas las letras.

            selectedLetters.add(getLetterPosition(allLetters, correctLetter));

            answers.add(correctLetter);

            //añadir una respuesta
            addAnswer(allLetters, selectedLetters, answers);
            //añadir una respuesta
            addAnswer(allLetters, selectedLetters, answers);


            Collections.shuffle(answers);

            Question question = new Question(answers,correctLetter);

            questions.add(question);
        }


        Collections.shuffle(questions);
        return new Quiz(questions);
    }


    private static void addAnswer(List<Letter> allLetters, Set<Integer> selectedLetters, List<Letter> answers) {
        Letter wrongLetter = getRandomLetterForQuestion(allLetters, selectedLetters);
        answers.add(wrongLetter);

        selectedLetters.add(getLetterPosition(allLetters, wrongLetter));
    }
}
