package jerma;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import jerma.commands.Command;
import jerma.utils.Parser;
import jerma.utils.Storage;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Jerma extends Application {
    private TaskList tasks;
    private Ui ui;
    private Boolean[] running;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image ster = new Image(
            this.getClass().getResourceAsStream("/images/ster.jpg"));
    private Image jerma = new Image(
            this.getClass().getResourceAsStream("/images/jerma.jpg"));

    public Jerma() {
        this.ui = new Ui();
        this.running = new Boolean[] { true };

        try {
            this.tasks = Storage.load();
        } catch (IOException e) {
            ui.error("Save file not found");
        } catch (UnsupportedOperationException e) {
            ui.error("Corrupted save file");
        }
    }

    @Override
    public void start(Stage stage) {
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        this.sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        this.scene = new Scene(mainLayout);

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
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().addAll(new DialogBox(
                new Label(this.ui.hello()), new ImageView(jerma)));

        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Iteration 1: Creates a label with the specified text and adds it to the
     * dialog container.
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

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(ster)),
                new DialogBox(dukeText, new ImageView(jerma)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input, this.ui, this.tasks,
                    this.running);
            return command.execute();
        } catch (IndexOutOfBoundsException e) {
            return ui.error("Invalid arguments. Try again!");
        } catch (UnsupportedOperationException e) {
            return ui.error("Invalid command. Try again!");
        } catch (DateTimeParseException e) {
            return ui.error("Invalid date format. Try again!");
        }
    }
}
