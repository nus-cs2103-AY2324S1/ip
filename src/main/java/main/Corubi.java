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
    @FXML



//
//    public static void main(String[] args) throws IOException {
//        launch();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        //Step 1. Setting up required components
        store.load(parser);

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("main.Corubi");
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

//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            try {
//                handleUserInput();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        userInput.setOnAction((event) -> {
//            try {
//                handleUserInput();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        return userUi.takeCommands(store, tasks, parser, input);
    }
}
