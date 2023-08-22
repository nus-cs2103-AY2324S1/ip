import java.util.Arrays;

public class CommandProcessor {
    private final TaskList tasks;
    private static final String[] VALIDCOMMANDS = {"mark", "unmark", "list", "todo", "event", "deadline"};

    CommandProcessor() {
        this.tasks = new TaskList();
    }

    private String[] parseCommand(String command) throws DukeException {
        String [] splitCommand = command.split(" ", 2);
        String commandType = splitCommand[0];
        if (!Arrays.asList(VALIDCOMMANDS).contains(commandType)) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (splitCommand.length != 2 && !commandType.equals("list")) {
            throw new DukeException("OOPS!!! The description of a " + commandType + " cannot be empty.");
        }

        String commandDescription = splitCommand[0].trim();

        if (commandDescription.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        return splitCommand;


    }


    //processCommand is a method that process the command and prints the relevant output
    void processCommand(String command) {

        try {

            String [] splitCommand = parseCommand(command);
            String commandType = splitCommand[0];
            // print the list of tasks
            if (commandType.equals("list")) {
                this.tasks.listContent();
                return;
            }

            String taskName = splitCommand[1];

            // processing command types: mark, unmark
            if (commandType.equals("mark")) {
                tasks.mark(taskName);
                return;
            } else if (commandType.equals("unmark")) {
                tasks.unMark(taskName);
                return;
            }

            // processing commands involving tasks (todo, deadline, event)
            if (commandType.equals("todo")) {
                Task task = new Todo(taskName);
                tasks.add(task);
            } else if (commandType.equals("deadline")) {
                Task task = new Deadline(taskName);
                tasks.add(task);



            } else if (commandType.equals("event")) {
                Task task = new Event(taskName);
                tasks.add(task);
            }

        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }


    }
}
