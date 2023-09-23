package duke;

import commands.Command;
import functional.DukeException;
import functional.Storage;
import functional.TaskList;
import functional.Ui;
import javafx.scene.layout.VBox;


/**
 * duke.Husky class is a simple chatbot that allows users
 * to mark down their tasks.It allows users to add,
 * list, and manage tasks.
 */
public class Husky {
    private Storage storage;
    private TaskList<tasks.Task> tasks;
    private Ui ui;
    private String filePath;
    private VBox dialogContainer;

    /**
     * Constructs a duke.Husky instance with the specified file path.
     *
     * @param filePath The file path to store task data.
     */
    public Husky(String filePath) {
        ui = new functional.Ui();
        storage = new functional.Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new functional.TaskList<tasks.Task>(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new functional.TaskList<tasks.Task>();
        }
    }

    /**
     * Runs the Duke application.
     * It displays a welcome message and processes user commands
     * until the 'bye' command is received to terminate the program.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        boolean hasExit = false;
        while (!hasExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = functional.Parser.parse(fullCommand);
                String k = c.execute(tasks, ui, false, false);
                System.out.println(k);
                hasExit = c.hasExit();
            } catch (functional.DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Husky("./data/tasks.txt").run();
    }

    /**
     * feeds the i/o to the GUI
     *
     * @param input the user input
     * @return the system output
     */
    public String getResponse(String input) {
        ui.showWelcome();
        String output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        try {
            String fullCommand = ui.readCommand(input);
            ui.showLine();
            Command c = functional.Parser.parse(fullCommand);
            output = c.execute(tasks, ui, false, false);
            assert output.length() > 0 : "invalid output";
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            storage.save(tasks);
            return output;
        }
    }
}

