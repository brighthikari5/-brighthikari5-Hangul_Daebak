package valeriamoscoso.ioc.hanguldaebak.presentation.studyLetters;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BasePresenter;
import valeriamoscoso.ioc.hanguldaebak.presentation.base.BaseView;

/**
 * Study letter in presenter for sign in
 * @author Valeria Moscoso León
 * */

abstract class StudyLettersPresenter {

    interface Presenter extends BasePresenter{

      void getLettersByType (String type);


    }

    interface View extends BaseView{

       void onLettersReceived(List<Letter> letters);

    }

}
