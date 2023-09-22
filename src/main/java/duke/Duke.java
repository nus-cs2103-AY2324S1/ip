package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the application.
 */

public class Duke extends Application {
    private String filePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/Bulbasaur.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/Charmander.png"));

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Constructor
     * @param filePath String representation of filepath
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ArrayList<String> taskStringList = this.storage.load();
            this.taskList = new TaskList(taskStringList);
        } catch (Exception e) {
            this.taskList = new TaskList(new ArrayList<>());
        }

    }
    /**
     * Runs the chatBot.
     * @throws DukeException if the filepath is incorrectly provided
     */
    public void run() throws DukeException {
        Parser parser = new Parser();
        System.out.println(ui.showWelcome());
        System.out.println(ui.showListMessage(taskList));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // If user types bye, goodbye message is printed.
            try {
                String userInput = scanner.nextLine().trim();
                Command c = parser.parse(userInput);
                String response = c.execute(taskList, ui);
                System.out.println(response);
                if (c instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        File file = new File(this.filePath);
        boolean isFileDeleted = file.delete();
        if (isFileDeleted) {
            storage.update(this.taskList, this.filePath);
        }
        scanner.close();
    }

    @Override
    public void start(Stage stage) {
        String welcomeMessage = "Hi I'm Chad! What can I do for you? ";
        displayMessage(welcomeMessage);
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(900.0);
        stage.setMinWidth(700.0);

        mainLayout.setPrefSize(700.0, 900.0);

        scrollPane.setPrefSize(685, 755);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(585.0);

        sendButton.setPrefWidth(100.0);

        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 5.0);
        AnchorPane.setRightAnchor(scrollPane, 5.0);
        AnchorPane.setBottomAnchor(scrollPane, 70.0);

        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);

        AnchorPane.setLeftAnchor(userInput , 10.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);


        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        storage.update(taskList, filePath);
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void displayMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
