package duke;

import java.util.Scanner;

import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A chatbot that helps to record tasks and store tasks.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private Scanner sc;

    /**
     * Constructs Duke object.
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new TaskList();
        this.storage = new Storage(tasks);
        this.parser = new Parser(tasks, storage);
        this.ui = new Ui();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Greets by chatbot.
     */
    private void greet() {
        System.out.println("------------------------------------------");
        System.out.println("  Hello! I'm Jokey :) \n  What can I do for you?");
        System.out.println("------------------------------------------");
    }

    /**
     * Runs Duke chatbot.
     */
    public void run() {
        storage.load();
        greet();
        parser.interact();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }
}
