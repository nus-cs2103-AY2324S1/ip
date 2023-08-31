package duke.messages;

/**
 * An enum class that states all general messages.
 */
public enum Messages {
    INTRODUCTION("Hello! I'm Chad. \n" + "What can I do for you?"),
    LIST_COMMAND("Here are the tasks on your list: "),
    MARK_COMMAND("Nice! I've marked this task as done:"),
    UNMARK_COMMAND("OK, I've marked this task as not done yet."),
    DELETED_COMMAND("Noted. I've removed this task:"),
    GOODBYE_MESSAGE("Bye. Hope to see you again soon!"),
    TASK_ADDED("Got it. I've added this task:"),
    DATE_FORMAT("yyyy-MM-dd ha"),
    FOUND_TASKS("Here are the matching tasks in your list");

    private final String MESSAGE;

    Messages(String message){
        this.MESSAGE = message;
    }

    public String getMessage(){
        return this.MESSAGE;
    }
}
