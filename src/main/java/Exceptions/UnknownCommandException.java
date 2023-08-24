package Exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(){

    }
    public String toString(){
        return "I have no idea what that means";
    }
}
