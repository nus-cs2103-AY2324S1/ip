package duke;
import duke.command.Command;
import javafx.application.Platform;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UiManager uiManager;
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Constructor of Duke class.
     * @param filePath File path of the save file.
     */
    public Duke(String filePath) {
        uiManager = new UiManager();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
//            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the programme.
     */
//    public void run() {
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parseCommand(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input.trim());
            return c.execute(tasks, uiManager, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
