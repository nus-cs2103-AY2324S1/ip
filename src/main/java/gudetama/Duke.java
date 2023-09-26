package gudetama;

import java.io.FileNotFoundException;

import gudetama.commands.Command;
import gudetama.exceptions.DukeException;
import gudetama.parser.Parser;
import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.DialogBox;
import gudetama.ui.Ui;

import javafx.application.Application;

import javafx.geometry.Insets;
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

public class Duke extends Application {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    private Scene scene;
    private VBox dialogContainer;
    private ScrollPane scrollPane;
    private TextField userInput;
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/gudetama1.jpg"));
    private Image gudetama = new Image(this.getClass().getResourceAsStream("/images/gudetama2.jpg"));

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasklist = storage.readFromFile();
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.tasklist = storage.readFromFile();
    }

    @Override
    public void start(Stage stage) {

        Duke duke = new Duke("./data/duke.txt");
        this.ui = duke.ui;
        this.storage = duke.storage;
        this.tasklist = duke.tasklist;

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Gudetama");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setOnCloseRequest(
                (event) -> {
                    close();
                }
        );

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Label welcomeMessage = new Label(ui.showWelcome());
        welcomeMessage.setPadding(new Insets(10));
        DialogBox gudetamaDialog = DialogBox.getGudetamaDialog(welcomeMessage, new ImageView(gudetama));
        dialogContainer.getChildren().addAll(gudetamaDialog);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        });

        stage.setScene(scene);
        stage.show();

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws DukeException {
        String userMessage = userInput.getText();

        Label userText = new Label(userMessage);
        Label gudetamaText = new Label(getResponse(userMessage));

        DialogBox userDialog = DialogBox.getUserDialog(userText, new ImageView(user));
        DialogBox gudetamaDialog = DialogBox.getGudetamaDialog(gudetamaText, new ImageView(gudetama));

        gudetamaText.setPadding(new Insets(10));
        userText.setPadding(new Insets(10));
        dialogContainer.setSpacing(10);

        dialogContainer.getChildren().addAll(userDialog, gudetamaDialog);

        userInput.clear();
    }

    private void close(){
        storage.saveToFile(tasklist.retrieveList());
        System.exit(0);
    }

    public String getResponse(String input) throws DukeException {
        Command command = Parser.parse(input);
        String output = command.execute(tasklist, ui, storage);
        if (command.isExit()){
            close();
        }
        return output;
    }
}
