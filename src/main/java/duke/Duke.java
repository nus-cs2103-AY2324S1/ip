package duke;

import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.utility.Command;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;
    private Command command;

    public Duke() {
        this.storage = new Storage();
        this.list = new TaskList();

        // Load list of tasks stored in text file "task.txt" into the local task List
        storage.handleLoad(list);

        // Start Scanner to read user inputs
        this.ui = new Ui(list);
        this.command = new Command(storage, ui, list);
    }

    public void run() {
        System.out.println(Ui.greeting());

        String answer = ui.getInput();

        // Listens to user commands and perform the relevant functions
        while (true) {
            if (answer.equalsIgnoreCase("bye")) {
                break;
            } else if (answer.equalsIgnoreCase("list")) {
                System.out.println(ui.listTasks());
                answer = ui.getInput();
            } else if (answer.startsWith("mark")) {
                System.out.println(command.handleMark(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("unmark")) {
                System.out.println(command.handleUnMark(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("todo")) {
                System.out.println(command.handleToDo(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("deadline")) {
                System.out.println(command.handleDeadline(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("event")) {
                System.out.println(command.handleEvent(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("delete")) {
                System.out.println(command.handleDelete(answer));
                answer = ui.getInput();
            } else if (answer.length() == 0) {
                System.out.println(Ui.printError(new EmptyCommandException()));
                answer = ui.getInput();
            } else {
                System.out.println(Ui.printError(new InvalidCommandException()));
                answer = ui.getInput();
            }
        }
        System.out.println(Ui.exit());
        ui.stopScanner();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
