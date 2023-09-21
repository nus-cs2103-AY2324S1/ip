package duke.util;

/**
 * Represents a class that contains all the help messages.
 */
public class Help {

    /**
     * Returns the general help message for commands.
     *
     * @return The help message.
     */
    public static String generalHelp() {
        return "Here is a list of commands that you can use:\n"
                + "-- bye\n"
                + "-- list\n"
                + "-- mark <task number>\n"
                + "-- unmark <task number>\n"
                + "-- delete <task number>\n"
                + "-- todo <task description>\n"
                + "-- event <task description> /from <task time> /to <task time>\n"
                + "-- deadline <task description> /by <task time>\n"
                + "-- print_date [deadline/event] /on <date>\n"
                + "-- find <search word>\n"
                + "-- load <file name>\n"
                + "-- sort <sort type>\n"
                + "-- alias <alias from> <alias to>\n"
                + "-- help <command>\n"
                + "\n"
                + "Type 'help <command>' to find out more about the commands.\n";
    }

    /**
     * Returns the help message for <b>{@code bye}</b> command.
     *
     * @return The bye help message.
     */
    public static String byeHelp() {
        return "bye\n"
                + "================================\n"
                + "- Exits the program.\n";
    }

    /**
     * Returns the help message for <b>{@code list}</b> command.
     *
     * @return The list help message.
     */
    public static String listHelp() {
        return "list\n"
                + "================================\n"
                + "- Lists all the tasks.\n";
    }

    /**
     * Returns the help message for <b>{@code mark}</b> command.
     *
     * @return The mark help message.
     */
    public static String markHelp() {
        return "mark <task number>\n"
                + "mark all\n"
                + "================================\n"
                + "- Marks the task as done.\n"
                + "- The task number should be a positive integer.\n"
                + "- The task number should not be empty.\n"
                + "- In the case of 'mark all', all the tasks will be marked.\n";
    }

    /**
     * Returns the help message for <b>{@code unmark}</b> command.
     *
     * @return The unmark help message.
     */
    public static String unmarkHelp() {
        return "unmark <task number>\n"
                + "unmark all\n"
                + "================================\n"
                + "- Unmarks the task as done.\n"
                + "- The task number should be a positive integer.\n"
                + "- The task number should not be empty.\n"
                + "- In the case of 'unmark all', all the tasks will be unmarked.\n";
    }

    /**
     * Returns the help message for <b>{@code delete}</b> command.
     *
     * @return The delete help message.
     */
    public static String deleteHelp() {
        return "delete <task number>\n"
                + "delete all\n"
                + "================================\n"
                + "- Deletes the task.\n"
                + "- The task number should be a positive integer.\n"
                + "- The task number should not be empty.\n"
                + "- In the case of 'delete all', all the tasks will be deleted.\n";
    }

    /**
     * Returns the help message for <b>{@code todo}</b> command.
     *
     * @return The todo help message.
     */
    public static String todoHelp() {
        return "todo <task description>\n"
                + "================================\n"
                + "- Adds a todo task.\n"
                + "- The task description should not be empty.\n";
    }

    /**
     * Returns the help message for <b>{@code event}</b> command.
     *
     * @return The event help message.
     */
    public static String eventHelp() {
        return "event <task description> /from <task time> /to <task time>\n"
                + "================================\n"
                + "- Adds an event task.\n"
                + "- The task time should be in the format of dd/MM/yyyy HH:mm.\n"
                + "For example: 01/01/2023 12:00\n"
                + "- The task time for 'from' should be before 'to'.\n"
                + "For example: event project meeting /from 01/01/2023 12:00 /to 01/01/2023 19:00\n"
                + "- The task description should not be empty.\n";
    }

    /**
     * Returns the help message for <b>{@code deadline}</b> command.
     *
     * @return The deadline help message.
     */
    public static String deadlineHelp() {
        return "deadline <task description> /by <task time>\n"
                + "================================\n"
                + "- Adds a deadline task.\n"
                + "- The task time should be in the format of dd/MM/yyyy HH:mm.\n"
                + "For example: 01/01/2023 12:00\n"
                + "- The task description should not be empty.\n";
    }

    /**
     * Returns the help message for <b>{@code print_date}</b> command.
     *
     * @return The print_date help message.
     */
    public static String printDateHelp() {
        return "print_date deadline /on <date>\n"
                + "print_date event /on <date>\n"
                + "================================\n"
                + "- Lists all the deadlines/events on the date.\n"
                + "- The date should be in the format of dd/MM/yyyy.\n"
                + "For example: 01/01/2023\n"
                + "- The task type should be either 'deadline' or 'event'.\n"
                + "- The task description should not be empty.\n";
    }

    /**
     * Returns the help message for <b>{@code find}</b> command.
     *
     * @return The find help message.
     */
    public static String findHelp() {
        return "find <search word>\n"
                + "================================\n"
                + "- Finds all the tasks that contain the search word.\n"
                + "- The search word should not be empty.\n";
    }

    /**
     * Returns the help message for <b>{@code load}</b> command.
     *
     * @return The load help message.
     */
    public static String loadHelp() {
        return "load <file name>\n"
                + "load\n"
                + "================================\n"
                + "- Loads the task list from the file in data folder.\n"
                + "- If the file name is not provided, the default file will be loaded.\n"
                + "- If the file is not found, a new file will be created. But not loaded.\n";
    }

    /**
     * Returns the help message for <b>{@code sort}</b> command.
     *
     * @return The sort help message.
     */
    public static String sortHelp() {
        return "sort <sort type>\n"
                + "sort\n"
                + "================================\n"
                + "- Sorts the task list.\n"
                + "- The sort type should be: 'name' | 'deadline' | 'category'.\n"
                + "- If the sort type is not provided, the task list will be sorted by name.\n";
    }

    /**
     * Returns the help message for <b>{@code alias}</b> command.
     *
     * @return The alias help message.
     */
    public static String aliasHelp() {
        return "alias <from> <to>\n"
                + "alias <from>\n"
                + "alias\n"
                + "================================\n"
                + "- Adds an alias.\n"
                + "E.g. alias t todo (Adds the alias t -> todo)\n"
                + "- If only the alias from is provided, the alias will be removed.\n"
                + "E.g. alias t (Removes the alias t -> todo)\n"
                + "- If nothing is provided, all the aliases will be listed.\n";
    }
}
