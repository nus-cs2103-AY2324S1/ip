package smolbrain.exception;

public class MissingDescriptionException extends Exception{

    String task_type;
    public MissingDescriptionException(String task_type) {
        super();
        this.task_type = task_type;
    }

    @Override
    public String toString() {
        return "The description of a " + task_type + " cannot be empty.";
    }

}
