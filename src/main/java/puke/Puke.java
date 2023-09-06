package puke;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import puke.command.Command;
import puke.command.ErrorCommand;
import puke.managers.DataHandler;
import puke.managers.Parser;
import puke.managers.PukeException;
import puke.managers.TaskList;
import puke.managers.Ui;

/**
 * A chatbot that uses overly complicated sentences.
 */
public class Puke {
    /**
     * List of tasks stored by the chatbot
     */
    private TaskList tasks;
    /**
     * The UI of the chatbot that prints all applicable messages.
     */
    private final Ui ui;

    /**
     * Constructor for the chatbot
     * @throws IOException when an error occurs with the file reader.
     */
    public Puke() throws IOException {
        this.ui = new Ui();
        try {
            tasks = new TaskList(DataHandler.loadDatabase());
        } catch (PukeException e) {
            new File("ListData.txt").createNewFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.startup();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            String input = ui.input();
            ui.line();
            Command next;
            try {
                next = Parser.parse(command, input);
            } catch (PukeException e) {
                next = new ErrorCommand();
            }
            next.execute(tasks, ui);
            isExit = next.isExit();
        }
    }

    /**
     * Dummy method to create GUI
     * @param stage the stage.
     */
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        new Puke().run();
    }
}
