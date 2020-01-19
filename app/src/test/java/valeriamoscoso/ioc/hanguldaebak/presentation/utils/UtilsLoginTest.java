package valeriamoscoso.ioc.hanguldaebak.presentation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Valeria Moscoso León
 *
 * Test Class to test Utils methods
 * */
public class UtilsLoginTest {

    /**
     * this test checks if the pw lenght is incorrect
     * */
    @Test
    public void checkLenghtIsIncorrect (){

        assertFalse(UtilsLogin.isValidLenght("a"));
    }

    /**
     * this test checks if pw lenght is correct
     * */
    @Test
    public void checkLenghtIsCorrect(){

        assertTrue(UtilsLogin.isValidLenght("asdfagjk"));
    }


    /**
     * this test checks if email is valid
     * */
    @Test
    public void checkValidEmail (){

        assertTrue(UtilsLogin.isValidEmail("valeria@hotmail.com"));
    }

    /**
     * this test checks if pw is too short
     * */
    @Test
    public void checkInvalidPasswordTooShort (){

        assertFalse(UtilsLogin.isValidPassword("12"));

    }

    /**
     * this test checks if pw doesn't have a capital letter
     * */
    @Test
    public void checkInvalidPasswordMissingCapitalLetter (){

        assertFalse(UtilsLogin.isValidPassword("valeria5"));
    }

    /**
     * this test checks if pw is valid
     * */
    @Test
    public void CheckIfPassWOrdIsValid (){

        assertTrue(UtilsLogin.isValidPassword("Valeria5"));
    }
}