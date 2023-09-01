import commands.Command;
import commands.ExitCommand;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Duke {

    public static final String VERSION = "OwO Bot ─ a CS2103T iP ─ Week 3 Update";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        storage = new Storage();
        ui = new Ui();
    }

    public void run() {

        this.tasks = new TaskList(storage.load());

        ui.printWelcomeMessage(VERSION);
        ui.printInstructions();

        runCommandLoop();

        storage.save(tasks.getTaskList());

        ui.printExitMessage();

    }
    public static void main(String[] args) {
        new Duke().run();
    }


    public void runCommandLoop() {
        Command c;
        Parser parser = new Parser();
        do {
            String userInput = ui.getUserCommand();
            c = parser.parse(userInput);
            c.execute(tasks);
        } while (!ExitCommand.isExit(c));
    }
}
