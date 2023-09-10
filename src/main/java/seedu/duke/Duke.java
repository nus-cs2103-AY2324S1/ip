package seedu.duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import seedu.duke.datafile.Storage;
import seedu.duke.exceptions.LemonException;
import seedu.duke.parser.Parser;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;


/**
 * Duke is the class representing a chatbot named Lemon.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor of Duke that represents the chatbot Lemon with a given filepath that
     * stores the tasks that are added by the user.
     * @param filePath the file path that stores the task details.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
            System.out.println("mavis");
        } catch (LemonException e) {
            ui.showLoadingError();
            System.out.println(e.getMessage() + "sean");
            tasks = new TaskList();
        }
    }

    /**
     * Constructor of Duke that represents the chatbot Lemon with a default filepath that
     * stores the tasks that are added by the user.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFile());
            System.out.println("mavis");
        } catch (LemonException e) {
            ui.showLoadingError();
            System.out.println(e.getMessage() + "sean");
            tasks = new TaskList();
        }
    }

    /**
     * Run the chatbot named Lemon.
     */
    public void run() {
        ui.welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            //System.out.println("in while loop");
            String input = scanner.nextLine();
            //System.out.println("Received input: " + input); // Provide feedback to the user
            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                try {
                    Parser.parseTasks(input, tasks, storage, ui);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ui.bye();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseTasks(input, tasks, storage, ui);
        } catch (LemonException e) {
            return e.getMessage();
        }
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
