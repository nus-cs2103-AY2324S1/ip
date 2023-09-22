package noelPackage.helper;

import noelPackage.command.*;
import noelPackage.tasks.Task;

public class Parser {

    private final Tasklist tasks;
    private final Storage storage;

    /**
     * Constructs a Parser object.
     *
     * @param tasks   The task list to manipulate.
     * @param storage The storage manager to use.
     */
    public Parser(Tasklist tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses a user command and performs the corresponding action.
     *
     * @param nextLine The command entered by the user.
     * @return -1 if the command is "bye", 0 otherwise.
     */
    public String parseCommand(String nextLine) {

        Command currCommand = new InvalidCommand();

        if (nextLine.equals("bye")) {
            currCommand = new ByeCommand();
        } else if (nextLine.equals("list")) {
            currCommand = new ListCommand();
        } else if (nextLine.contains(" ")) {
            return parseCommandHelper(nextLine);
        }
        return currCommand.execute(tasks, storage);
    }

    public String parseCommandHelper(String nextLine) {

        Command currCommand = new InvalidCommand();

        String[] result = nextLine.split(" ");

        switch (result[0]) {

            case "mark": {
                currCommand = new MarkCommand(result);
                break;
            }
            case "unmark": {
                currCommand = new UnMarkCommand(result);
                break;
            }
            case "todo":
                currCommand = new ToDoCommand(nextLine);
                break;

            case "deadline":
                currCommand = new DeadlineCommand(nextLine);
                break;

            case "event":
                currCommand = new EventCommand(nextLine);
                break;

            case "delete":
                currCommand = new DeleteCommand(nextLine);
                break;

            case "find":
                currCommand = new FindCommand(nextLine);
                break;

            case "snooze":
                currCommand = new SnoozeCommand(nextLine);
                break;
        }
        return currCommand.execute(tasks, storage);
    }
}
