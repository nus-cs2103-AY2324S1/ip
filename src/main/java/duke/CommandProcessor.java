package duke;

import java.util.Arrays;

public class CommandProcessor {
    private final TaskList tasks;
    private static final String[] VALIDCOMMANDS = {"mark", "unmark", "list", "todo", "event", "deadline", "delete"};

    private static final Storage storage = new Storage();

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


    //processCommand is a method that process the command and prints the relevant output
    public String processCommand(String command) {

        try {

            String [] splitCommand = parseCommand(command);
            String commandType = splitCommand[0];
            // print the list of tasks
            if (commandType.equals("list")) {

                return this.tasks.listContent();
            }

            String taskName = splitCommand[1];

            // process command types: mark, unmark
            if (commandType.equals("mark")) {
                return tasks.mark(taskName);

            } else if (commandType.equals("unmark")) {
                return tasks.unMark(taskName);
            }

            if (commandType.equals("delete")) {
                return tasks.delete(taskName);
            }

            // process commands involving tasks (todo, deadline, event)
            if (commandType.equals("todo")) {
                Task task = new Todo(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);

            } else if (commandType.equals("deadline")) {
                Task task = new Deadline(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);

            } else if (commandType.equals("event")) {
                Task task = new Event(taskName);
                storage.writeToFile(task.storageText());
                return tasks.addToList(task);
            }

        } catch(DukeException e) {
            return (e.getMessage());
        }

        return ("OOPS!! This is out of my job scope...\n");


    }
}
