package glen;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import glen.util.Parser;
import glen.util.Storage;
import glen.util.TaskList;
import glen.util.Ui;

/**
 * Glen is a chatbot that helps you keep track of your tasks.
 */
public class Glen extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    /**
     * Creates a new Glen object.
     * Empty constructor for Launcher.
     */
    public Glen() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        tasks = storage.read();
    }

    /**
     * Main entry point for this JavaFX application.
     * Defines the user interface container by means of a stage and a scene.
     * The JavaFX Stage class is the top-level JavaFX container for all content.
     * 
     * @param stage Stage to be displayed.
     */
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

        stage.setTitle("Glen");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        handleUserInput();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    int initial = 0;

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (initial == 0) {
            Label dukeText = new Label(ui.intro());
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            initial++;
            return;
        }

        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(userInput.getText()));

        // start countdown to exit if input == "bye"
        if (input.toLowerCase().equals("bye")) {
            dukeText = new Label(ui.exit());

            executor.schedule(() -> {
                Platform.exit();
                executor.shutdown();
            }, 5, TimeUnit.SECONDS);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     * 
     * @param input User input.
     * @return Response to user input.
     */
    private String getResponse(String input) {
        String output = new Parser(storage, tasks).parseInput(input);
        return output;
    }

}