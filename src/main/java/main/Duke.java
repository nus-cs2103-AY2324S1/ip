package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import commands.Command;
import components.DukeException;
import components.Parser;
import components.Storage;
import components.Ui;
import tasks.TaskList;


public class Duke extends Application {
    private final Storage storage;
    private static final String chatBotName = "CHAD CCP";
    private TaskList list;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private static final String PARENT_DIR = "./data";
    private static final String FILEPATH = "./data/store.txt";
    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PARENT_DIR, FILEPATH);
        try {
            storage.loadOrCreateFile();
            list = storage.readData(storage);
            assert ui != null : "UI should be initialized";
            assert storage != null : "Storage should be initialized";
            assert list != null : "TaskList should be initialized";
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * Drives the program by reading user input and executing the command.
     */
    public void run() {
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";
        assert list != null : "TaskList should not be null";
        System.out.println(ui.showWelcome(chatBotName));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                System.out.println(Ui.LINE);
                System.out.println(c.execute(list, ui, storage));
                System.out.println(Ui.LINE);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(Ui.LINE);
                System.out.println(ui.showError(e));
                System.out.println(Ui.LINE);
            }
        }
    }

    @Override
    public void start(Stage stage) {
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Display the welcome message in the GUI using DialogBox
        Label welcomeLabel = new Label(ui.showWelcome(chatBotName, true));
        welcomeLabel.setWrapText(true);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLabel, roundImage(duke)));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
                DialogBox.getUserDialog(userText, roundImage(user)),
                DialogBox.getDukeDialog(dukeText, roundImage(duke))
        );
        userInput.clear();
        assert userInput.getText().isEmpty() : "User input should be empty after clearing";
    }
    private ImageView roundImage(Image object) {
        ImageView imageView = new ImageView(object);
        imageView.setFitWidth(50);  // Set the width
        imageView.setFitHeight(50); // Set the height

        // Create a Circle
        Circle clip = new Circle(
                imageView.getFitWidth(),  // center X
                imageView.getFitHeight(), // center Y
                imageView.getFitWidth()  // radius
        );

        imageView.setClip(clip);
        return imageView;
    }
    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed command should not be null";
            String responseString = command.execute(list, ui, storage);
            assert responseString != null && !responseString.isEmpty() : "Response should not be null or empty";
            if (command.isExit()){
                System.exit(0);
            }
            return responseString;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Main method to run the program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
