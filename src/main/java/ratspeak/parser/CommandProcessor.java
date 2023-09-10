package ratspeak.parser;

import ratspeak.InputReceiver;
import ratspeak.exception.DukeException;
import ratspeak.data.TaskList;
import ratspeak.storage.Storage;
import ratspeak.task.Deadline;
import ratspeak.task.Event;
import ratspeak.task.Task;
import ratspeak.task.Todo;

import java.util.Arrays;

public class CommandProcessor {
    private final TaskList tasks;
    private static final String[] VALID_COMMANDS = {"bye", "mark", "unmark", "list", "todo"
            , "event", "deadline", "delete", "find"};

    private static final Storage storage = new Storage();

    /**
     * initialize command processor
     * Load list of tasks from storage (if any)
     */
    public CommandProcessor() {
        this.tasks = new TaskList(storage.loadFromFile());
    }


    private String parseCommand(String command) throws DukeException {
        String [] splitCommand = command.split(" ", 2);
        String commandType = splitCommand[0];
        if (!Arrays.asList(VALID_COMMANDS).contains(commandType)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        return commandType;
    }

    private String parseInformation(String command) throws DukeException {
        String [] splitCommand = command.split(" ", 2);
        String commandType = splitCommand[0];

        if (commandType.equals("list")) {
            return "";
        }

        if (splitCommand.length != 2) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.\n");
        }

        String commandDescription = splitCommand[1].trim();

        if (commandDescription.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.\n");
        }
        return commandDescription;
    }


    /**
     * Returns a string that is the output for the command given.
     * Process mark, unmark, delete, find, event, todo, deadline
     * @return the string that is the output string for the command given.
     */
    public String processCommand(String command) {
        try {
            String commandType = parseCommand(command);
            String commandDescription = parseInformation(command);
            return process(commandType, commandDescription);

        } catch(DukeException e) {
            return (e.getMessage());
        }
    }


    private String process(String commandType, String taskName) throws DukeException {
        switch (commandType) {
        case "list":
            return this.tasks.listContent();
        case "mark":
            return tasks.mark(taskName);
        case "unmark":
            return tasks.unMark(taskName);
        case "delete":
            return tasks.delete(taskName);
        case "find":
            return tasks.find(taskName);
        case "todo":
            Task todoTask = new Todo(taskName);
            storage.writeToFile(todoTask.storageText());
            return tasks.addToList(todoTask);
        case "deadline":
            Task deadlineTask = new Deadline(taskName);
            storage.writeToFile(deadlineTask.storageText());
            return tasks.addToList(deadlineTask);
        case "event":
            Task eventTask = new Event(taskName);
            storage.writeToFile(eventTask.storageText());
            return tasks.addToList(eventTask);
        default:
            return "Please enter a valid command from this list: "
                    + Arrays.toString(VALID_COMMANDS) + "\n";
        }
    }

    public String initialReminder() {
        return this.tasks.listUrgentTask();
    }
}
