package haste;

import haste.commands.Command;
import haste.commands.Parser;
import haste.data.Storage;
import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Scanner;

/**
 * Represents a chatbot that keeps track of tasks.
 */
public class Haste {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates an instance of the chatbot.
     *
     */
    public Haste() {
        this.ui = new Ui();
        this.storage = new Storage("./Data.txt");
        this.tasks = new TaskList();
        load();
    }

    public static void main(String[] args) {
        Haste haste = new Haste();

        // greet and check for storage files
        haste.load();
        haste.run();
        haste.end();
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (ui.running) {
            String cmd = sc.nextLine();
            Command c = Parser.handleCommand(cmd, storage);

            try {
                c.execute(tasks, ui);
            } catch (HasteException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Welcomes user and loads any saved tasks.
     */
    public void load() {
        this.ui.greet();
        this.storage.read(tasks);
    }

    /**
     * Close the chatbot and saves tasks in list.
     */
    public void end() {
        this.storage.delete();
        this.storage.save(tasks);
    }


    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    public Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.handleCommand(input, storage);
            return c.execute(tasks, ui);
        } catch (HasteException e) {
            return e.getMessage();
        }
    }

}