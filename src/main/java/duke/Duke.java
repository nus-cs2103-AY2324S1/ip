package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the Duke Class.
 *
 * @author Shishir
 */
public class Duke extends Application {

    /** Task List of all tasks. */
    private TaskList tasks;

    /** Storage of all tasks. */
    private Storage storage;

    /** UI for chatting and interacting with the bot. */
    private Ui ui;

    /**
     * Constructs the Duke Object.
     * @param filePath Path of the text file with stored tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /**
     * Constructs the Duke Object with the default file path
     * being data/tasks.txt.
     */
    public Duke() {
        this.storage = new Storage("data/tasks.txt");
        this.tasks = new TaskList(this.storage.readData());
        this.ui = new Ui();
    }

    /** Provides interaction with the user. */
    public void interact() {
        this.ui.showLine();
        this.ui.greet();
        this.ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException exc) {
                this.ui.showLine();
                System.out.println(exc.getMessage());
                this.ui.showLine();
            }
        }
    }

    /**
     * Initialises J.A.R.V.I.S
     * @param args Input args.
     */
    public static void main(String[] args) {
//        Duke bot = new Duke("data/tasks.txt");
//        bot.interact();
        launch();
    }

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
