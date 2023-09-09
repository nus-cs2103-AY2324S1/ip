package gbot;

import exceptions.GBotException;
import java.util.Scanner;

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
 * The chatbot GBot that helps keep track of tasks for users.
 *
 * @author Gallen Ong
 */
public class GBot {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the chatbot GBot.
     *
     * @param filePath The filepath to be written into or updated.
     */
    public GBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public GBot() {
        // do nothing
    }

    private void run() {
        ui.ask();
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String message = inputScanner.nextLine();
            if (message.equals("bye")) {
                inputScanner.close();
                ui.end();
                return;
            }
            try {
                Ui.print(Parser.parse(message, tasks));
            } catch (GBotException e) {
                Ui.showError(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye! Have a nice day!";
        }
        try {
            return Parser.parse(input, this.tasks);
        } catch (GBotException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new GBot("./data/tasks.txt").run();
    }
}

