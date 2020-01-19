package valeriamoscoso.ioc.hanguldaebak.domain.repository;


import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

/**
 * LettersRepository repository interface with the declaration of the possible server responses
 * @author Valeria Moscoso León
 * */
public interface LettersRepository {

    List<Letter> allLetters();

    List<Letter> vowelLetters();

    List<Letter> consonantLetters();

}
