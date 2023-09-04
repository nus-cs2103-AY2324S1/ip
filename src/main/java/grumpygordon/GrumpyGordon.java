package grumpygordon;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;
import grumpygordon.ui.controllers.DialogBox;
import grumpygordon.ui.controllers.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import java.io.IOException;

/**
 * GrumpyGordon Chatbot
 */
public class GrumpyGordon extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Storage for GrumpyGordon.
     */
    private Storage storage;

    /**
     * List of tasks for GrumpyGordon.
     */
    private TaskList tasks;

    /**
     * User interface for GrumpyGordon.
     */
    private Ui ui;

    /**
     * Constructor for GrumpyGordon.
     * @throws GrumpyGordonException If GrumpyGordon fails to initialise
     */
    public GrumpyGordon() throws GrumpyGordonException {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (GrumpyGordonInitialisationException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui(tasks, storage);
    }

    /**
     * Runs GrumpyGordon.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Main loop for GrumpyGordon.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        try {
            new GrumpyGordon().run();
        } catch (GrumpyGordonException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    @FXML
    public void start(Stage stage) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
//            AnchorPane ap = fxmlLoader.load();
//            Scene scene = new Scene(ap);
//            stage.setScene(scene);
//            grumpyGordon = new GrumpyGordon();
//            fxmlLoader.<MainWindow>getController().setGrumpyGordon(grumpyGordon);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (GrumpyGordonException e) {
//            e.printStackTrace();
//        }
        //Step 1. Setting up required components
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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
        stage.setTitle("Grumpy Gordon");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
