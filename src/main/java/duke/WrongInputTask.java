package duke;

public class WrongInputTask extends Exception {

    public WrongInputTask(String taskExceptionMessage, String solution) {
        super("Invalid Format: " + taskExceptionMessage + "\nRecommendation: " + solution);
    }

}
