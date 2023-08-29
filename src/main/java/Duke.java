import java.nio.file.Path;
import java.util.Scanner;

/**
 * Duke is a chatbot that helps you keep track of your tasks.
 * In this version, Duke is renamed to LilBro.
 */
public class Duke {

    // The task list is used to store the user's tasks.
    private final TaskList list = new TaskList();

    private final Storage storage;

    /**
     * The main method is used to run the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * The command enum is used to store the valid commands that LilBro can
     * understand.
     */
    private static enum Command {
        EXIT("bye"),
        LIST_TASKS("list"),
        ADD_TODO("todo"),
        ADD_DEADLINE("deadline"),
        ADD_EVENT("event"),
        MARK_TASK("mark"),
        UNMARK_TASK("unmark"),
        DELETE_TASK("delete");

        // The string representation of the command.
        private final String value;

        /**
         * The constructor is used to create a new command with the given value.
         * 
         * @param value The string representation of the command.
         */
        private Command(String value) {
            this.value = value;
        }

        /**
         * The fromString method is used to get the command from the given string.
         * 
         * @param value The string representation of the command.
         * @return The command if it exists, null otherwise.
         */
        public static Command fromString(String value) {
            for (Command command : Command.values()) {
                if (command.value.equalsIgnoreCase(value)) {
                    return command;
                }
            }
            return null;
        }
    }

    public Duke(String path) {
        Ui.greet();
        Ui.println("Checking for a save file...");
        String projectRoot = System.getProperty("user.dir");
        String fullPath = Path.of(projectRoot, path).toString();
        this.storage = new Storage(fullPath);
        try {
            storage.load(this.list);
        } catch (DukeException e) {
            Ui.println(e.getMessage());
        }
        Ui.println("OK, ready to roll");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);
            String commandArgs = parts.length > 1 ? parts[1].trim() : "";
            try {
                if (!this.executeCommand(command, commandArgs)) {
                    break;
                }
            } catch (DukeException e) {
                Ui.println(e.getMessage());
            }
        }

        scanner.close();
        Ui.println("Before you go, let me save your tasks...");
        storage.save(this.list);
        Ui.bye();
    }

    /**
     * Executes the given command with the given arguments.
     * 
     * @param command The command to execute.
     * @param args    The arguments to pass to the command.
     * @throws DukeInvalidCommandException  If the command is invalid.
     * @throws DukeInvalidArgumentException If the arguments are invalid.
     * @return True if the command was executed successfully, false otherwise.
     */
    private boolean executeCommand(Command command, String args)
            throws DukeInvalidCommandException, DukeInvalidArgumentException {
        if (command == null) {
            throw new DukeInvalidCommandException(
                    "Not sure what you're blabbering on about, please use a valid command.");
        }

        switch (command) {
        case EXIT:
            return false;

        case LIST_TASKS:
            this.list.listTasks();
            break;

        case MARK_TASK:
            try {
                this.list.markTaskDone(Integer.parseInt(args));
            } catch (NumberFormatException e) {
                throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
            }
            break;

        case UNMARK_TASK:
            try {
                this.list.unmarkTaskDone(Integer.parseInt(args));
            } catch (NumberFormatException e) {
                throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
            }
            break;

        case ADD_TODO:
            this.list.addTask(TaskType.TODO, args);
            break;

        case ADD_DEADLINE:
            this.list.addTask(TaskType.DEADLINE, args);
            break;

        case ADD_EVENT:
            this.list.addTask(TaskType.EVENT, args);
            break;

        case DELETE_TASK:
            try {
                this.list.deleteTask(Integer.parseInt(args));
            } catch (NumberFormatException e) {
                throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
            }
            break;

        default:
            throw new DukeInvalidCommandException("I'm gonna be honest, no idea what you're saying.");
        }

        return true;
    }

}
