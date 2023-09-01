package pau.exception;

public class NoSuchTaskException extends Exception{
    public NoSuchTaskException(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println("there is no such task!!");
    }
}
