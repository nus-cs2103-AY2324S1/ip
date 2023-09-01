package smolbrain.exception;

public class MissingTimeException extends Exception{

    String task_type;
    String time_type;
    public MissingTimeException(String time_type, String task_type) {
        super();
        this.task_type = task_type;
        this.time_type = time_type;
    }

    @Override
    public String toString() {
        return "The " + time_type + " time of a " + task_type + " cannot be empty.";
    }

}