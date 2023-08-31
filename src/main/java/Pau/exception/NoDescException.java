package Pau.exception;

public class NoDescException extends Exception {
    public NoDescException(String errorMessage) {
        System.out.println("oi write something please");
        System.out.println(errorMessage);
    }
}
