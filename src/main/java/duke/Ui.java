package duke;

/**
 * Text UI of the application
 */
public class Ui {

    /**
     * Returns the welcome message for the application
     *
     * @return welcome message for the application
     */
    public static String welcomeMessage() {
        return "Hello! I'm Handsome!\nWhat can I do for you?\n"
                + "Type 'help' to see the list of commands and instructions";
    }

    /**
     * Returns the goodbye message for the application
     *
     * @return goodbye message for the application
     */
    public static String goodbyeMessage() {
        return "Goodbye! Hope to see you again soon!";
    }

    /**
     * Returns a help message with commands and instructions
     *
     * @return help message with commands and instructions
     */
    public static String helpMessage() {
        return "Here are the list of commands:\n"
                + "All dates/times should be in the format dd/mm/yyyy hhmm, eg: 01/01/2024 2359\n\n"
                + "1. Deadline: adds a deadline to the task list\n"
                + "Format: deadline <task description> /by <date/time to do by>\n\n"
                + "2. Event: adds an event to the task list\n"
                + "Format: event <task description> /from <starting date/time> /to <ending date/time>\n\n"
                + "3. Todo: adds a todo to the task list\n"
                + "Format: todo <task description>\n\n"
                + "4. List: displays the current task list\n"
                + "Format: list\n\n"
                + "5. Mark: mark a task as done\n"
                + "Format: mark <index of task as shown in list>\n\n"
                + "6. Unmark: mark a task as undone\n"
                + "Format: unmark <index of task as shown in list>\n\n"
                + "7. Delete: delete a task from the task list\n"
                + "Format: delete <index of task as shown in list>\n\n"
                + "8. Find: search for tasks in the task list\n"
                + "Format: find <keyword to search by>\n\n"
                + "9. Bye: exits the application\n"
                + "Format: bye";
    }
}
