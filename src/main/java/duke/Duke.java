package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the bot SeeWhyAre.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Duke {

    // HORIZONTAL_LINE
    public static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    private static Parser parser;
    private static Storage storage;
    private static TaskList listOfTasks;
    private static Ui ui;
    public static boolean isFinished = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Images
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ElonMusk.jpeg")));
    private final Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/BillGates.jpeg")));

    /**
     * Constructs a new instance of the chat bot.
     *
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        ui = new Ui();
        try {
            listOfTasks = new TaskList(storage);
            parser = new Parser(listOfTasks, ui);
            storage.loadTasks();
            //start();

        } catch (IOException e) {
            System.err.println(e);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
    }

//    /**
//     * Starts the SeeWhyAre bot.
//     *
//     * @throws IOException if there are issues with file handling.
//     * @throws InvalidCommandException if there are invalid commands entered by users.
//     * @throws EmptyDescriptionException if users attempt to create tasks with empty descriptions.
//     */
//    private static void start() throws IOException,
//            InvalidCommandException, EmptyDescriptionException {
//        ui.greet();
//        while (!isFinished) {
//            parser.parseInput(ui.getUserInput());
//        }
//    }

//    /**
//     * Starts the GUI of Duke app.
//     *
//     * @param stage the primary stage for this application, onto which
//     * the application scene can be set.
//     * Applications may create other stages, if needed, but they will not be
//     * primary stages.
//     */
//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
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
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("SeeWhyAre Bot");
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
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration x:
     */
    private void handleUserInput() throws InvalidCommandException, EmptyDescriptionException, IOException {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getSeeWhyAreDialog(dukeText, duke)
        );
        userInput.clear();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public static String getResponse(String userInput) throws InvalidCommandException, EmptyDescriptionException, IOException {
        return parser.parseInput(userInput);
//        return "Duke heard: " + input;
    }


    /**
     * Driver method for CLI version of Duke.
     *
     * @param args not used.
     */
    public static void main(String[] args) throws InvalidCommandException, EmptyDescriptionException, IOException {
        new Duke();
        ui.greet();
        String userInput = ui.getUserInput();
        while (!isFinished) {
            System.out.println(getResponse(userInput));
        }
    }
}

