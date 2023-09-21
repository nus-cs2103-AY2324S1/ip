package duke;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class Duke extends Application {
    private static final int MAX_TASKS = 100;
    private static final String DATA_FILE_PATH = "./docs/duke.txt";

    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage(DATA_FILE_PATH);
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image biubiu = new Image(this.getClass().getResourceAsStream("/images/BiuBiu.jpg"));

    /**
     * The main entry point of the Duke program.
     *
     * @param args  Command-line arguments (not used).
     */
    public static void main (String[] args) {
        ui.showWelcome();

        try {
            tasks = ui.loadTasks("./docs/duke.txt");
        } catch (Exception e) {
            ui.showLoadingError();
        }

        boolean isExit = false;

        while(!isExit) {
            String userCommand = ui.readCommand();
            isExit = ui.handleCommand(userCommand, tasks, storage) == ui.showExit()
                    ? true
                    : false;
        }
    }

    @Override
    public void start (Stage stage) {
        // Step 1
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

        // Step 2
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3
        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        // Scroll down to the end automatically
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, biubiu)
        );
        userInput.clear();
    }

    /**
     * Gets s response based on the user input.
     *
     * @param input The user input.
     * @return     A response generated based on the user input.
     */
    public String getResponse (String input) {
        return ui.handleCommand(input, tasks, storage);
    }
}