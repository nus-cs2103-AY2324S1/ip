package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static Path filePath = Paths.get(".", "data", "duke.txt"); // ./data/duke.txt

    public String getResponse(String input) {
        return ("Duke heard: " + input);
    }
    /**
     * Constructor to start the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskList());
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(ui, tasks);
        parser.parse(input);
        while (!parser.isDone()) {
            input = scanner.nextLine();
            parser.parse(input);
        }
        storage.updateTaskList();
    }

    /**
     * Starts the program and loads in the saved task list.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
