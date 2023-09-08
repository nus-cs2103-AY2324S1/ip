package duke;

import java.time.LocalDateTime;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static duke.Parser.inputType;

/**
 * The Duke class is a Personal Assistant Chatbot that
 * helps a person to keep track of various things
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    public Storage storage;
    public TaskList taskList;
    public Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Initializes a new instance of the Duke class.
     * This constructor sets up the initial state of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        String[] actions = storage.loadActions();
        boolean[] isDone = storage.loadIsDone();
        String[] types = storage.loadTypes();
        String[]  dueString = storage.loadDueStrings();
        LocalDateTime[] startTimes = storage.loadStartTimes();
        LocalDateTime[] endTimes = storage.loadEndTimes();

        int counter = Storage.load("./data/duke.txt", actions, types, isDone,dueString, startTimes,endTimes);

        if (actions != null && isDone != null && types != null && counter >= 0) {
            taskList = new TaskList(actions, isDone, types, counter);
        } else {
            ui.showLoadingError();
            String[] actions2 = new String[100];
            boolean[] isDone2 = new boolean[100];
            String[] types2 = new String[100];
            taskList = new TaskList(actions2, isDone2, types2, 0);

        }
    }


    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(); // Process user input when the Send button is clicked
        });

        userInput.setOnAction((event) -> {
            handleUserInput(); // Process user input when Enter is pressed
        });
    }
    /**
     * The run method starts Duke's interaction with user.
     * run() serves as an entry point for duke's interaction.
     */
    public void run() {
        ui.showWelcome();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit = true;
            }
            String response = inputType(input, taskList, storage);
            if (!response.isEmpty()) {
                System.out.println(response);
            }
        }

        // Save data before exiting
        Storage.save("./data/duke.txt", TaskList.actions, TaskList.type, TaskList.isDone, TaskList.dueString, TaskList.startTime, TaskList.endTime, TaskList.counter);
        scanner.close();
    }


    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeResponse = String.valueOf(inputType(userText, taskList, storage));
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, user));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeResponse, duke));

        userInput.clear();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }


    public static void main(String[] args) {
        new Duke().run();

    }
}