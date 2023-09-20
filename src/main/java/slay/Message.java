package slay;

/**
 * Container for user visible messages.
 */
public class Message {
    public static final String MESSAGE_WELCOME = "WORK. PLAY. SLAY.\n"
        + "Let's slay your tasks and slay the day!";
    public static final String MESSAGE_PROMPT = "Explore how I can help you SLAY by simply typing 'help':D";
    public static final String MESSAGE_GOODBYE = "YOU SLAY THE DAY! Have a good rest. See you!";

    public static final String MESSAGE_INCORRECT_ADD_FORMAT = "Sorry, I can't understand your task:(\n"
            + "You may retype task following this format:\n";
    public static final String MESSAGE_INCORRECT_ADD_TASK_KEYWORD = "Sorry, I don't know this type of tasks:(\n"
            + "You may choose from what I have:D\n";

    public static final String MESSAGE_EMPTY_ARGUMENT = "It seems that your command is incomplete. "
            + "What do you want to say?";
    public static final String MESSAGE_INVALID_TASK_INDEX = "The task index seems to be invalid."
            + "Double check and try it again!";
}
