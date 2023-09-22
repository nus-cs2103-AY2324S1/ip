package duke.messages;

/**
 * An enum class that handles the help messages.
 */
public enum HelpMessages {
    HELP_HEADING("What can I help you with ?"),
    HELP_OPTION_1("1. I  seem to not be able to enter my task into the list."),
    HELP_OPTION_2("2. I seem to not be able to enter my deadline/event task into the list."),
    HELP_OPTION_3("3. I seem to be unable to delete my task from the list."),
    IDENTIFY_HELP_OPTION("Please enter Help with your chosen option."),
    HELP_ANSWER_1("All tasks have to be denoted with either a todo/deadline/event at the start. "
            + "E.g. to add a todo event, please type todo return book."),
    HELP_ANSWER_2("All deadline and event dates are in the format yyyy-MM-dd ha where PM and AM are in caps"
            + "E.g. 2023-09-01 2PM"),
    HELP_ANSWER_3("The number you provide must be greater than zero and within the total size of your task list.");

    private final String message;

    HelpMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
