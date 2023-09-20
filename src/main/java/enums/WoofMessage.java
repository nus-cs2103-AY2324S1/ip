package enums;

/**
 * The `WoofMessage` enum represents the possible woof messages used in the Woof application.
 */
public enum WoofMessage {
    WOOF_TITLE("Woof Woof"),
    HI("Woof Woof! I'm Oreo!!!\nWhat can I do for you?\nsend `help` for help."),
    BYE("Bye. Hope to see you again soon!\nOffing myself... woof :("),
    CONFUSED("OOPS!!! I'm sorry, I don't know what is\n'%s'\nsend `help` for help."),
    TASK_LIST_COUNT("You have %s tasks in the task list."),
    TASK_ADDED("Got it. I've added this task:\n%s%s%s"),
    NO_MATCHING_TASKS("No tasks matched your keyword(s)!\n"),
    TASKS_FOUND("Here are your matching tasks in your list:\n%s%s"),
    EMPTY_TASK_LIST("There's nothing to sort... really\n"),
    TASKS_LISTED("Here are all the tasks in your list:\n%s%s"),
    TASK_DONE("Ok! I've marked this task as done:\n%s%s%s"),
    TASKS_SORTED("Your tasks have been sorted:\n%s%s"),
    TASK_UNDONE("Ok! I've marked this task as undone:\n%s%s%s"),
    TASK_DELETED("Ok! I've deleted this task:\n%s%s%s"),
    HELP("Woof! Woof! Here to help:\n\n"
        + "Available Commands:\n\n"
        + "1. Todo     : Add a simple to-do task.\n"
        + "   Format   : `todo <description>`\n"
        + "   Example  : `todo study`\n\n"
        + "2. Deadline : Add a task with a deadline.\n"
        + "   Format   : `deadline <description> /by <date>`\n"
        + "   Example  : `deadline assignment /by 2023-12-31`\n\n"
        + "3. Event    : Add an event with a start and end date.\n"
        + "   Format   : `event <description> /from <date> /to <date>`\n"
        + "   Example  : `event enjoy /from 2023-11-25 /to 2024-01-14`\n\n"
        + "4. Mark     : Mark a task as completed.\n"
        + "   Format   : `mark <taskIndex>`\n"
        + "   Example  : `mark 1`\n\n"
        + "5. Unmark   : Unmark a completed task as incomplete.\n"
        + "   Format   : `unmark <taskIndex>`\n"
        + "   Example  : `unmark 1`\n\n"
        + "6. Delete   : Delete a task.\n"
        + "   Format   : `delete <taskIndex>`\n"
        + "   Example  : `delete 2`\n\n"
        + "7. List     : View all your tasks.\n"
        + "   Format   : `list`\n\n"
        + "8. Find     : Search for tasks containing n keywords.\n"
        + "   Format   : `find <keyword1> ... <keyword n>`\n"
        + "   Example  : `find meeting project`\n\n"
        + "9. Sort     : Sort tasks by date and time.\n"
        + "   Format   : `sort`\n\n"
        + "10. Bye     : Say goodbye to WoofWoof Bot.\n"
        + "    Format : `bye`\n\n"
        + "11. Help    : Request help on how to use the bot.\n"
        + "    Format : `help`\n\n"
        + "Notes:\n"
        + "- Replace `<description>` with a task description.\n"
        + "- `<date>` should follow the yyyy-mm-dd date format.\n"
        + "           e.g. 2023-12-31\n"
        + "- `<taskIndex>` should be the index of the task in the list\n"
        + "                you want to manage.\n\n"
        + "Feel free to ask for help using the `help` command if you\n"
        + "have any questions or encounter issues.\n"
        + "Woof Woof is here to make your life easier!");

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
    public String toFormattedValue(String... args) {
        return String.format(this.value, (Object[]) args);
    }
}
