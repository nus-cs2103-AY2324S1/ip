package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * An interactive digital task manager called ChatterBox.
 * This minimal chatbot can take in simple commands, remember
 * tasks even after termination, and manipulate them as per the 
 * user's requests. 
 */
public class ChatterBox extends Application {
    private static final String USER_IMAGE_LOCATION = "/DaUser.png";
    private static final String DUKE_IMAGE_LOCATION = "/DaDuke.png";
    private static final Double ANCHOR_LENGTH = 1.0;
    private static final double MIN_HEIGHT = 600.0;
    private static final double MIN_WIDTH = 400.0;
    private static final double V_VALUE = 1.0;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui = new Ui();
    private TaskList tl = new TaskList();
    private Storage store = new Storage();

    private Image user = new Image(this.getClass().getResourceAsStream(USER_IMAGE_LOCATION));
    private Image duke = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_LOCATION));

    /**
     * Constructs an empty ChatterBox object.
     */
    public ChatterBox() throws IOException, DukeException {
        this(new Ui(), new TaskList(), new Storage());
        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);

    }

    /**
     * Constructs a ChatterBox object with all parameters specified.
     *
     * @param ui The supplied Ui object.
     * @param tl The supplied TaskList object.
     * @param store The supplied Storage object.
     */
    ChatterBox(Ui ui, TaskList tl, Storage store) throws IOException {
        this.ui = ui;
        this.tl = tl;
        this.store = store;
    }


    @Override
    public void start(Stage stage) {
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
        stage.setTitle("ChatterBox");
        stage.setResizable(false);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        mainLayout.setPrefSize(MIN_WIDTH, MIN_HEIGHT);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(V_VALUE);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, ANCHOR_LENGTH);

        AnchorPane.setBottomAnchor(sendButton, ANCHOR_LENGTH);
        AnchorPane.setRightAnchor(sendButton, ANCHOR_LENGTH);

        AnchorPane.setLeftAnchor(userInput , ANCHOR_LENGTH);
        AnchorPane.setBottomAnchor(userInput, ANCHOR_LENGTH);

        // more code to be added here later

        Label firstText = new Label(ui.startScreen());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(firstText, new ImageView(duke))
        );

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(V_VALUE));

    }

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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label("User: " + userInput.getText() + " ");
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * This method gets a response to be displayed by the GUI, in the
     * form of a String, whether that be a standard String or an error
     * message contained within a thrown exception.
     *
     * @return A String to be returned as duke's response
     *
     */
    private String getResponse(String input) {
        String res = null;
        try {
            res = Parser.parseText(input, tl, store);
        } catch (DukeException e) {
            res = e.getMessage();
        } catch (IOException i) {
            res = new Ui().fileErrorString();
        }
        assert (res != null);
        return " ChatterBox: " + res;
    }

}
