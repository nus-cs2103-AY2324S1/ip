package Exceptions;

public class IllegalFormatException extends Exception{
    String msg;
    public IllegalFormatException(String s){
        msg = s;
    }

    public String toString(){
        return msg;
    }
}
