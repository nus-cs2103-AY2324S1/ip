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
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            storage.save(tasks);
            return output;
        }
    }
}

