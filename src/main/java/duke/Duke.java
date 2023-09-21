package duke;

/**
 * Duke is a task management application that allows users to manage their tasks.
 */
public class Duke {
    private static final String SAVED_TASKS_FILEPATH = "./data/savedTasks.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the specified file path for saving and loading tasks.
     *
     * @param filepath The file path for saving and loading tasks
     */
    Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
            ui.displayMessage("Saved tasks successfully loaded from '" + filepath + "'");
        } catch (LukeException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke instance with the default file path for saving and loading tasks.
     */
    public Duke() {
        this(SAVED_TASKS_FILEPATH);
    }

    
    public static void main(String[] args) {
        Duke luke = new Duke(SAVED_TASKS_FILEPATH);
        luke.run();
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user's input
     * @return A response to the user's input
     */
    public String getResponse(String input) {
        return executeCommand(input);
    }

    /**
     * Runs the main program and logic of Duke.
     */
    private void run() {
        greet();

        String input = ui.readNextInput();
        while (!input.equals("bye")) {
            executeCommand(input);
            input = ui.readNextInput();
        }

        bye();
        saveData();
    }

    /**
     * Saves the task data to the specified file path.
     */
    public void saveData() {
        try {
            storage.save(tasks.toSaveString());
            ui.displayMessage("Tasks successfully saved");
        } catch (LukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Executes a given command.
     *
     * @param command The command detailing what and how to execute
     * @return A response message based on the execution result
     */
    private String executeCommand(String command) {
        try {
            switch(command.split(" ")[0]) {
            case "ls":
                // Fallthrough
            case "list":
                return ui.displayMessage(tasks.list());
            case "m":
                // Fallthrough
            case "mark":
                return ui.displayMessage("Nice! I've marked this task as done: \n" + tasks.markAsDone(command));
            case "um":
                // Fallthrough
            case "unmark":
                return ui.displayMessage("OK, I've marked this task as not done yet: \n" + tasks.markAsUndone(command));
            case "del":
                // Fallthrough
            case "delete":
                return ui.displayMessage("Noted. I've removed this task: \n" + tasks.delete(command));
            case "t":
                // Fallthrough
            case "todo":
                // Fallthrough
            case "d":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "e":
                // Fallthrough
            case "event":
                return ui.displayMessage("added : " + tasks.add(command));
            case "f":
                // Fallthrough
            case "find":
                return ui.displayMessage(tasks.find(command));
            default:
                throw new LukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (LukeException e) {
            return ui.displayError(e.getMessage());
        }
    }

    /**
     * Displays a goodbye message.
     *
     * @return A goodbye message
     */
    public String bye() {
        return ui.displayMessage("Bye. Hope to see you again soon!");
    }

    private void greet() {
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |__ | |_| |   <  __/\n"
                    + "|____| \\__,_|_|\\_\\___|\n";
        String greetingMsg = "Hello! I'm Luke \n"
                        + "What can I do for you?\n";

        System.out.println("Hello from\n" + logo);
        ui.displayMessage(greetingMsg);
    }

}
