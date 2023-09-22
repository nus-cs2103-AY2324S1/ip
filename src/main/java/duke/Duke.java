package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Personal assistant chatbot that can help you manage a task list.
 *
 * @author Wu Jingya
 */
public class Duke extends Application {
    public static Duke duke;
    private static String filePath = "./data/duke.txt";

    private String name = "Moira";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object with no parameters.
     */
    public Duke() {
        tasks = new TaskList();
        storage = new Storage(tasks, filePath);
        ui = new Ui(name);
        parser = new Parser(ui);
        Duke.duke = this;
    }

    /**
     * Returns the response generated by the user input.
     *
     * @param input The user input.
     * @return The response generated by the chatbot.
     */
    public String getResponse(String input) {
        assert parser != null: "parser is null";
        return parser.parse(input);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Returns the TaskList stored by the chatbot.
     *
     * @return The TaskList stored by the chatbot.
     * @see TaskList
     */
    public TaskList getTaskList() {
        return tasks;
    }

    /**
     * Returns the Ui stored by the chatbot.
     *
     * @return The Ui stored by the chatbot.
     * @see Ui
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Cleans up and exits the application.
     */
    public void exitApplication() {
        exit();
        Launcher.exit();
    }

    private void exit() {
        storage.saveData();
    }
}
