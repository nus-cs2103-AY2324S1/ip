package duke;

import java.util.Scanner;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * duke.Duke class that encapsulates a personal assistant chatbot.
 *
 * @author Pearlynn
 */

public class Duke extends Application {
    private static boolean isExit = false;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private final String pathname = "data/duke.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for duke.Duke class.
     */
    public Duke() {
        Duke.ui = new Ui();
        Duke.ui.showWelcome();
        Duke.storage = new Storage(this.pathname);
        if (Duke.storage.getHasFile()) {
            Duke.taskList = new TaskList(Duke.storage.loadData());
        } else {
            Duke.taskList = new TaskList();
        }
    }

    /**
     * Constructor for duke.Duke class.
     *
     * @param pathname The pathname of the file.
     */
    public Duke(String pathname) {
        Duke.ui = new Ui();
        //Duke.ui.showWelcome();
        Duke.storage = new Storage(pathname);
        //Duke.taskList = new TaskList(Duke.storage.loadData());
        if (Duke.storage.getHasFile()) {
            Duke.taskList = new TaskList(Duke.storage.loadData());
        } else {
            Duke.taskList = new TaskList();
        }
    }

    /**
     * Sets the exit status of the chatbot.
     *
     * @param isExit The boolean value.
     */
    public static void setExit(boolean isExit) {
        Duke.isExit = isExit;
    }

    /**
     * Gets the storage.
     *
     * @return The storage.
     */
    public static Storage getStorage() {
        return Duke.storage;
    }

    /**
     * Gets the taskList.
     *
     * @return The taskList.
     */
    public static TaskList getTaskList() {
        return Duke.taskList;
    }

    /**
     * Gets the UI.
     *
     * @return The UI.
     */
    public static Ui getUi() {
        return Duke.ui;
    }

    /**
     * Starts the chatbot.
     */
    private void run() {
        Duke.ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (!Duke.isExit) {
            String command = sc.nextLine();
            Parser.parse(command, Duke.taskList);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
        //new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Parser.parse(input, Duke.taskList);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
