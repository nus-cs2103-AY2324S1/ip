package main;

import java.io.IOException;

import controllers.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * The <code>main.Corubi</code> class represents the main class for the main.Corubi chatbot application.
 * It serves as the entry point and orchestrates the interaction between the user interface,
 * task management, storage, and command parsing.
 */
public class Corubi extends Application {
    private static final Ui userUi = new Ui();
    private static final TaskList tasks = new TaskList();
    private static final Parser parser = new Parser();
    private static final Storage store = new Storage("./src/main/java/OUTPUT.txt", tasks);


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private final Image bot = new Image(this.getClass().getResourceAsStream("/images/chad.png"));

    public Storage getStore() {
        return store;
    }

    @Override
    public void start(Stage stage) throws IOException {
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        return userUi.takeCommands(store, tasks, parser, input);
    }
}
