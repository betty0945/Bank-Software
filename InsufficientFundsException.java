package edu.ithaca.dturnbull.bank;

public class InsufficientFundsException extends Exception{
    private static final long serialVersionUID = 1L;

    public InsufficientFundsException(String s) {
        super(s);
    }

}