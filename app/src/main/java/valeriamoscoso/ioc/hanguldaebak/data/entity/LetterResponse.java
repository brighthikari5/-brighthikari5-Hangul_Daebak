package valeriamoscoso.ioc.hanguldaebak.data.entity;

import java.util.List;

import valeriamoscoso.ioc.hanguldaebak.domain.entity.Letter;

public class LetterResponse {

 private List<Letter> letterList; //este parametro se ha de llamar igual que la respuesta del servidor, si le pone listaLetras pues asin.

    public List<Letter> getLetterList() {
        return letterList;
    }
}
