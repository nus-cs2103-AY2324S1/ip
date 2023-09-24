package duke;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {

    // Print the farewell message
    public String printFarewell() {
        return "Oink. Hope to see you again soon!";
    }

    /**
     * Prints all the available commands.
     */
    public String printAllCommands() {
        StringBuilder commands = new StringBuilder();
        commands.append("OINK! Invalid command. Try the following commands instead:\n");
        commands.append("> todo <task>\n");
        commands.append("> deadline <task> /by yyyy-mm-dd hh:mm\n");
        commands.append("> event <task> /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm\n");
        commands.append("> list\n");
        commands.append("> mark <task number>\n");
        commands.append("> unmark <task number>\n");
        commands.append("> delete <task number>\n");
        commands.append("> find <keyword>\n");
        commands.append("> update <task number> <new task description>\n");
        commands.append("> bye\n");
        return commands.toString();
    }
}
