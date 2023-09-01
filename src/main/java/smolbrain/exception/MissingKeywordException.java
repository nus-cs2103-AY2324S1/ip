package smolbrain.exception;

public class MissingKeywordException extends Exception{

    public MissingKeywordException() {
        super();
    }

    @Override
    public String toString() {
        return "Please provide a keyword for finding task(s).";
    }

}