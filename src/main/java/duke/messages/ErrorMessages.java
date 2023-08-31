package duke.messages;

/**
 * An enum class that states all the error messages.
 */
public enum ErrorMessages {
    INVALID_INPUT("I'm sorry, I don't understand this command. Did you mean to type "),
    MISSING_TASK_NUMBER("I'm sorry, you seem to have forgotten to enter the task number."),
    INVALID_TASK_NUMBER("I'm sorry, the task number you have entered a number that exceeds the size of your task list."),
    EMPTY_DESCRIPTION_HEAD("I'm sorry, the description of a "),
    EMPTY_DESCRIPTION_TAIL(" cannot be empty"),
    INCOMPREHENSIBLE_TASK("I'm sorry, I don't understand this task."),
    MISSING_DEADLINE("Deadline is not provided. Please indicate your deadline by placing a '/by' before the deadline."),
    MISSING_EVENT_TIMING("I'm sorry, you seem to have forgotten to enter a start/end time. " +
            "Please indicate the start time starting with /from and the end time starting with /to."),
    TOO_MANY_EVENT_TIMINGS("I'm sorry, you seem to have entered too many timings."),
    INVALID_END_DATE("End date cannot be before start date!"),
    KEYWORD_NOT_FOUND("I'm sorry, I could not find task with this word in it."),
    NO_KEYWORD_PROVIDED("I'm sorry. Did you forget to enter a keyword?");

    private final String message ;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message ;
    }
}
