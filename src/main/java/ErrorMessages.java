enum ErrorMessages {
    INVALID_INPUT("I'm sorry, I don't understand this command. Did you mean to type "),
    GOODBYE_MESSAGE("Bye. Hope to see you again soon!"),
    INTRODUCTION("Hello! I'm Chad. \n" + "What can I do for you?"),
    MISSING_TASK_NUMBER("I'm sorry, you seem to have forgotten to enter the task number."),
    INVALID_TASK_NUMBER("I'm sorry, the task number you have entered a number that exceeds the size of your task list."),
    EMPTY_DESCRIPTION_HEAD("I'm sorry, the description of a "),
    EMPTY_DESCRIPTION_TAIL(" cannot be empty"),
    TASK_ADDED("Got it. I've added this task:"),
    INCOMPREHENSIBLE_TASK("I'm sorry, I don't understand this task.");

    private final String message ;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message ;
    }
}
