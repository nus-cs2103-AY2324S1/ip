package duke;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Starts the chatbot
 */
public class Duke extends Application {
    private final Storage storage;
    private final Ui ui;
    private final TaskList list;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
//        try {
        list = new TaskList(storage.load());
//        } catch (Exceptions.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }

    /**
     * ALlows the chatbbot to run
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser parser = new Parser();
        while (!isExit) {
            String input = ui.getUserCommand();
            parser.parse(input, list);
            isExit = ui.isExit(input);
        }
        storage.save(list);
    }

    /**
     * Executes Duke
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke("duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}


