package duke.parser;

import duke.exception.DukeException;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Arrays;

public class CommandProcessor {
    private final TaskList tasks;
    private static final String[] VALIDCOMMANDS = {"mark", "unmark", "list", "todo"
            , "event", "deadline", "delete", "find"};

    private static final Storage storage = new Storage();

    /**
     * initialize command processor
     * Load list of tasks from storage (if any)
     */
    public CommandProcessor() {
        this.tasks = new TaskList(storage.loadFromFile());
    }


    private String[] parseCommand(String command) throws DukeException {
        String [] splitCommand = command.split(" ", 2);
        String commandType = splitCommand[0];
        if (!Arrays.asList(VALIDCOMMANDS).contains(commandType)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        if (splitCommand.length != 2 && !commandType.equals("list")) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.\n");
        }

        String commandDescription = splitCommand[0].trim();

        if (commandDescription.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.\n");
        }

        return splitCommand;


    }


    /**
     * Returns a string that is the output for the command given.
     * Process mark, unmark, delete, find, event, todo, deadline
     * @param command the command given by the user
     * @return the string that is the output string for the command given.
     */
    public String processCommand(String command) {

        try {

            String [] splitCommand = parseCommand(command);
            String commandType = splitCommand[0];

            // print the list of tasks
            if (commandType.equals("list")) {
                return this.tasks.listContent();
            }

            String taskName = splitCommand[1];


            switch (commandType) {
            case "mark":
                return tasks.mark(taskName);
            case "unmark":
                return tasks.unMark(taskName);

            case "delete":
                return tasks.delete(taskName);

            case "find":
                return tasks.find(taskName);

            case "todo":
                Task task = new Todo(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);

            case "deadline":
                task = new Deadline(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);


            case "event":
                task = new Event(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);
            }

        } catch(DukeException e) {
            return (e.getMessage());
        }

        return ("OOPS!! This is out of my job scope...\n");


    }
}
