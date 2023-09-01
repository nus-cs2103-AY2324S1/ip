package smolbrain.exception;

public class InvalidNumberException extends Exception{

    String task_type;

    public InvalidNumberException(String task_type) {
        super();
        this.task_type = task_type;
    }

    @Override
    public String toString() {
        return "Please provide a valid number to " + task_type + ".";
    }

}