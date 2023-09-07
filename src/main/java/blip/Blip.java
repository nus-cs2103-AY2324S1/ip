package blip;

import java.util.Scanner;
import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;
import blip.parser.*;
import blip.exceptions.*;
import blip.commands.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents the Blip ChatBot.
 */
public class Blip extends Application {
    /**
     * File path for tasks.
     */
    private static String FILE_PATH = "./data/blip.txt";

    /**
     * User interface for Blip ChatBot.
     */
    private BlipUI ui;

    /**
     * Task list of tasks.
     */
    private TaskList tasks;

    /**
     * Storage for tasks.
     */
    private BlipStorage storage;

    /**
     * Parser for string inputs by user.
     */
    private BlipParser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    /**
     * Constructor of Blip ChatBot.
     * @param filePath The data file path for tasks
     */
    public Blip(String filePath) {
        this.ui = new BlipUI();
        this.storage = new BlipStorage(filePath);
        this.parser = new BlipParser();
        try {
            tasks = storage.loadFile();
        } catch (BlipException e) {
            ui.showLoadingErr();
            tasks = new TaskList();
        }
    }


    @Override
    public void start(Stage stage) {

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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
        stage.setTitle("Blip");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        if (command instanceof ByeCommand) {
            Platform.exit();
            return ui.showOutro();
        }
        return command.execute(tasks, ui, storage);
    }



/*    *//**
     * Runs the Blip ChatBot.
     *//*
    public void run() {
        ui.showIntro();
        Scanner scanIn = new Scanner(System.in);

        while (true) {
            String userInput;
            userInput = scanIn.nextLine();
            Command command = parser.parse(userInput);
            if (command instanceof ByeCommand) {
                command.execute(tasks, ui, storage);
                break;
            }
            command.execute(tasks, ui, storage);
        }

    }*/

    /**
     * Main for Blip ChatBot where it runs Blip.
     * @param args CLI Arguments
     */
    public static void main(String[] args) {

    }



}
