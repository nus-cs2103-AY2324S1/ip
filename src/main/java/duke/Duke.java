package duke;

public class Duke {
    private static final String SAVED_TASKS_FILEPATH = "./data/savedTasks.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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

    public Duke() {
        this(SAVED_TASKS_FILEPATH);
    }
    public static void main(String[] args) {
        Duke luke = new Duke(SAVED_TASKS_FILEPATH);
        luke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return executeCommand(input);
    }

    /**
     * Handles the main program and logic of Luke (Duke)
     *
     */
    private void run() {
        greet();

        String input = ui.readNextInput();
        while (!input.equals("bye")) {
            executeCommand(input);
            input = ui.readNextInput();
        }

        bye();
        try {
            storage.save(tasks.toSaveString());
            ui.displayMessage("Tasks successfully saved");
        } catch (LukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Executes a given command
     *
     * @param command String command detailing what and how to execute
     * @throws LukeException If the leading word in the command is not a recognised command, or
     *         if the processes associated with executing the command encounters an error
     */
    private String executeCommand(String command) {
        try {
            switch(command.split(" ")[0]) {
                case "list":
                    return ui.displayMessage(tasks.list());
                case "mark":
                    return ui.displayMessage("Nice! I've marked this task as done: \n" + tasks.markAsDone(command));
                case "unmark":
                    return ui.displayMessage("OK, I've marked this task as not done yet: \n" + tasks.markAsUndone(command));
                case "delete":
                    return ui.displayMessage("Noted. I've removed this task: \n" + tasks.delete(command));
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    return ui.displayMessage("added : " + tasks.add(command));
                case "find":
                    return ui.displayMessage(tasks.find(command));
                default:
                    throw new LukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (LukeException e) {
            return ui.displayError(e.getMessage());
        }
    }

    private void bye() {
        ui.displayMessage("Bye. Hope to see you again soon!");
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
