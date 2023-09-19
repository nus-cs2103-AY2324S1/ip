package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents an abstract Command class that deals with the commands.
 */
public abstract class Command {
    /**
     * Represents available commands along with descriptions and examples.
     */
    public enum CommandType {
        LIST("list", "List all tasks", "list"),
        TODO("todo", "Add a new todo task", "todo borrow book"),
        EVENT("event", "Add a new event task", "event project meeting /from 2020-08-25 /to 2020-08-26"),
        DEADLINE("deadline", "Add a new deadline task", "deadline return book /by 2020-08-25"),
        MARK("mark", "Mark a task as done", "mark 1"),
        UNMARK("unmark", "Unmark a task as done", "unmark 3"),
        FIND("find", "Find tasks with a keyword", "find book"),
        DELETE("delete", "Delete a task", "delete 2"),
        BYE("bye", "Save and end the chat", "bye"),
        HELP("help", "Show available commands", "help"),
        EXIT("exit", "Exit the console application", "exit");

        private final String command;
        private final String description;
        private final String example;

        CommandType(String command, String description, String example) {
            this.command = command;
            this.description = description;
            this.example = example;
        }

        public String getCommand() {
            return command;
        }

        public String getDescription() {
            return description;
        }

        public String getExample() {
            return example;
        }
    }

    /**
     * Creates a Command object.
     */
    public Command() {
    }

    /**
     * Executes the Command object based on the type of Command object.
     *
     * @param tasks   The TaskList object that contains the list of tasks.
     * @param ui      The Ui object that deals with user input and output.
     * @param storage The Storage object that deals with saving tasks to the file and loading tasks from the file.
     * @return The response to be displayed to the user.
     * @throws Exception If the command is invalid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
