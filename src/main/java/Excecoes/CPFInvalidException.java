package Excecoes;

public class CPFInvalidException extends Exception{

    public CPFInvalidException(){
        super("CPF inválido! Por favor digite conforme essas recomendações (XXX.XXX.XXX-XX).");
    }
}
