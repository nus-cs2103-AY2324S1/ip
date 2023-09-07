package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;




/**
 * Contains the Duke Chatbot.
 *
 * @author Marcus Soh
 */
public class Duke extends Application {
    public static final Path SAVE_FILE_LOCATION = Paths.get("data", "duke.txt");
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());
    private Storage storage = new Storage(String.valueOf(SAVE_FILE_LOCATION));

    private Image userAvatar = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeAvatar = new Image(this.getClass().getResourceAsStream("/images/elonmusk.jpg"));


    /**
     * Constructor for our chatbot.
     *
     * @param filePath Specifies where the save file to store previous information is to be saved.
     */
    public Duke() {



        try {
            this.listContainer = new TaskList(storage.load());
            ui = new Ui(this.listContainer, storage);
        } catch (DukeException e) {
            System.out.println(e.getErrorMessage());
        }
    }

    /**
     * Begins the chatbot.
     */
    private void run() {
        ui.beginLogging();
    }


    @Override
    public void start(Stage stage) throws Exception {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // styling padding and spacing
//        dialogContainer.setPadding(new Insets(10, 20, 50, 20));
//        dialogContainer.setSpacing(20);
//
//
//        // step 3: user interactions
//        // add submit handlers
//        sendButton.setOnMouseClicked(e -> handleUserInput());
//        userInput.setOnAction(e -> handleUserInput());
//
//        // Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        // Render!
//        stage.setScene(scene);
//        stage.show();


    }

    public static void main(String[] args) {
        new Duke().run();
    }



    public String getResponse(String input) {
        // TODO
        try {
            return Parser.parse(input, listContainer, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
}

