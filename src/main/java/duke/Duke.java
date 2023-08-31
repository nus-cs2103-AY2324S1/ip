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
    public static void main(String[] args) {
        Duke luke = new Duke(SAVED_TASKS_FILEPATH);
        luke.run();
    }

    private void run() {
        greet();

        String input = ui.readNextInput();
        while (!input.equals("bye")) {
            try {
                processCommand(input);
            } catch (LukeException e) {
                ui.displayError(e.getMessage());
            }
            input = ui.readNextInput();
        }

        bye();
        try {
            storage.save(tasks.getAll());
            ui.displayMessage("Tasks successfully saved");
        } catch (LukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    private void processCommand(String command) throws LukeException {
        switch(command.split(" ")[0]) {
        case "list":
            ui.list(tasks.getAll());
            break;
        case "mark":
            ui.displayMessage("Nice! I've marked this task as done: \n" + tasks.markAsDone(command));
            break;
        case "unmark":
            ui.displayMessage("OK, I've marked this task as not done yet: \n" + tasks.markAsUndone(command));
            break;
        case "delete":
            ui.displayMessage("Noted. I've removed this task: \n" + tasks.delete(command));
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            ui.displayMessage("added : " + tasks.add(command));
            break;
        default:
            throw new LukeException("I'm sorry, but I don't know what that means :-(");
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
