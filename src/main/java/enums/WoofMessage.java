package enums;

/**
 * The `WoofMessage` enum represents the possible woof messages used in the Woof application.
 */
public enum WoofMessage {
    HI("Woof Woof! I'm Doggo\nWhat can I do for you?"),
    BYE("Bye. Hope to see you again soon!\n"
        + "Closing Woof Woof...\n"),
    CONFUSED("OOPS!!! I'm sorry, but I don't know what that means :-(\n"),
    TASK_LIST_COUNT("You have %s tasks in the task list."),
    TASK_ADDED("Got it. I've added this task:\n%s%s%s"),
    NO_MATCHING_TASKS("No tasks matched your keyword!\n"),
    TASKS_FOUND("Here are your matching tasks in your list:\n%s%s"),
    EMPTY_TASK_LIST("There's nothing to sort... really\n"),
    TASKS_LISTED("Here are all the tasks in your list:\n%s%s"),
    TASK_DONE("Ok! I've marked this task as done:\n%s%s%s"),
    TASKS_SORTED("Your tasks have been sorted:\n%s%s"),
    TASK_UNDONE("Ok! I've marked this task as undone:\n%s%s%s"),
    TASK_DELETED("Ok! I've deleted this task:\n%s%s%s");

    private final String value;

    /**
     * Constructs a `Exception Message` enum with the given value.
     *
     * @param value The string representation of the command word.
     */
    WoofMessage(String value) {
        assert value != null : "value cannot be null";

        this.value = value;
    }

    /**
     * Gets the string representation of the woof message with the appropriate args word.
     *
     * @param args The arguments to replace placeholders in the exception message.
     * @return The string representation of the exception message with the appropriate command word.
     */
    public String toFormattedString(String... args) {
        return String.format(this.value, args);
    }
}
