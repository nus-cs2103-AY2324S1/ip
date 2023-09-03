package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import  javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

/**
 * Encapsulates the bot application.
 *
 * @author Donovan Chan Jia Jun
 */
public class Duke {
    /**
     * Temporary data storage to store user text.
     */
    static final String DIR = "/data";
    private String outputPath;
    private Storage data;
    private TaskList tasks;
    private Ui ui;

    public Duke() {

    }

    /**
     * Constructs the bot object.
     *
     * @param filePath String represetation of the relative path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.data = new Storage(filePath);
        this.outputPath = filePath;
        try {
            this.tasks = new TaskList(this.data.loadOutputFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Starting point of the bot.
     * Says hello - Carries out data storage for user text - Says goodbye
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke bot = new Duke("./ipOutput.txt");
        bot.run();
        bot.ui.exit();
    }

    /**
     * Starts the chatbot sequence.
     * When arraylist changes, the entire output file is overwritten and all contents is transferred over
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (Parser.parsable(userInput)) {
            if (this.outputPath.equals("")) {
                this.ui.emptyFilePath();
            }
            Parser.parse(userInput, ui, this.tasks, this.data);
            this.ui.createLine();
            userInput = scanner.nextLine();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
