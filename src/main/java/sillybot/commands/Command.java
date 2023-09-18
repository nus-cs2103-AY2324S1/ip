package sillybot.commands;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents an abstract Command class that deals with the commands.
 */
public abstract class Command {
    /**
     * Enum representing available commands along with descriptions.
     */
    public enum CommandType {
        LIST("list", "List all tasks", "list"),
        TODO("todo", "Add a new todo task", "todo borrow book"),
        EVENT("event", "Add a new event task", "event project meeting /at 2020-08-25 14:00"),
        DEADLINE("deadline", "Add a new deadline task", "deadline return book /by 2020-08-25 14:00"),
        MARK("mark", "Mark a task as done", "mark 1"),
        UNMARK("unmark", "Unmark a task as done", "unmark 3"),
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
     * @return The String representation of the Command object.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}
