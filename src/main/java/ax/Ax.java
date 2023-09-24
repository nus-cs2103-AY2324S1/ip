package ax;

import static javafx.beans.binding.Bindings.subtract;

import java.util.Scanner;

import ax.commands.Parser;
import ax.display.DialogBox;
import ax.display.Ui;
import ax.storage.Storage;
import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * The main Ax Class for the chatbot
 */
public class Ax extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image ax = new Image(this.getClass().getResourceAsStream("/images/DaAx.png"));


    /**
     * Starts GUI
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane mainLayout = setUpAnchorPane(stage);

        setUpWindow(stage, mainLayout);

        setUpHandleUserInput();
    }

    private void setUpHandleUserInput() {
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // more code to be added here later
    }

    private void setUpWindow(Stage stage, AnchorPane mainLayout) {
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Ax");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.prefWidthProperty().bind(mainLayout.widthProperty());
        scrollPane.prefHeightProperty().bind(mainLayout.heightProperty());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: rgb(255,214,127);\n -fx-background-color: rgb(255,214,127)");
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        NumberBinding userInputWidth = subtract(subtract(mainLayout.widthProperty(), sendButton.widthProperty()), 9);
        userInput.prefWidthProperty().bind(userInputWidth);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 3.0);
        AnchorPane.setRightAnchor(sendButton, 3.0);

        AnchorPane.setLeftAnchor(userInput, 3.0);
        AnchorPane.setBottomAnchor(userInput, 3.0);
    }

    private AnchorPane setUpAnchorPane(Stage stage) {
        //Step 1. Setting up required components
        Ui.greet();
        Storage.readSave();
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
        return mainLayout;
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label axText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getAxDialog(axText, new ImageView(ax)));
        userInput.clear();
    }


    /**
     * Returns the response from Ax
     * @param input String from user
     * @return String from Ax
     */
    private String getResponse(String input) {
        return Parser.getInputString(input);
    }
}
