package duke;


import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Duke
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * @param filePath The file path of the txt document to read and write from.
     */
    public Duke(String filePath) {

    }

    /**
     * Old constructor
     */
    public Duke() throws IOException {
        //String filePath = "../ip/src/main/data/duke.txt";
        String filePath = "./duke.txt";
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
        storage.startStorage();
        ui = new Ui(tasks);
    }
    public static void main(String[] args) throws EmptyDescriptionException, InvalidCommandException,
            NotANumberException {
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @Override
    public void start(Stage stage) {

        Window.setParameters(stage, storage, ui);

        Window.initializeWindow();

        Window.formatWindow();

        Window.addUserInput();

    }

}
