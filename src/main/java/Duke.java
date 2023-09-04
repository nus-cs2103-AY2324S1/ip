import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.ErrorStorageException;
import exceptions.InvalidCommandException;
import helpers.Parser;
import helpers.Storage;
import helpers.TaskList;
import helpers.Ui;
import javafx.application.Application;
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
import uicomponents.DialogBox;


/**
 * Main Class of Duke
 */
public class Duke extends Application {

    private static TaskList taskList;
    private final String DIRECTORY = "data";
    private Storage storage;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/machoduke.jpg"));
    private Scene scene;

    /**
     * Empty public constructor to load Duke
     */
    public Duke() {
    }

    /**
     * Public constructor to load Duke
     *
     * @param filePath File path to get text file data from
     * @throws ErrorStorageException Exception for storage loading error
     */
    public Duke(String filePath) throws ErrorStorageException {
        ui = new Ui();
        String projRoot = System.getProperty("user.dir");
        String path = projRoot + "/" + DIRECTORY + filePath;
        this.storage = new Storage(path);
        try {
            taskList = new TaskList(this.storage.read());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    /**
     * Running method for executing other core processes
     */
    public void run() {
        //Create buffered reader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            ui.showStartLogo();
            ui.showWelcome();
            boolean isRunning = true;
            //exits the echo commands when input contains 'bye'
            while (isRunning) {
                String input = ui.getCommand(br);
                String cmd = input.split(" ")[0].toUpperCase();
                Parser parser = new Parser(cmd, input, taskList, ui, storage);
                parser.execute();
                if (cmd.equalsIgnoreCase("bye")) {
                    isRunning = false;
                }
            }
        } catch (IOException | InvalidCommandException | ErrorStorageException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        //new Duke("/tasks.txt").run();

        //Step 1. setting up required components
        //Setup container for content to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Step 2. Formatting the window to look as expected
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
