package adam.exception;

public class AdamException extends RuntimeException{
    public String getInfo() {
        return "OOPS!!! I don't know what this means";
    }
}
