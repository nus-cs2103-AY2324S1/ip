package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private MainWindow mainWindow;

    public Duke() {
        String filePath = "tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.showWelcomeMessage();
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
        mainWindow = new MainWindow();
        mainWindow.setDuke(this);

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        // Handle user input and display responses in the GUI.
        String input = userInput.getText();
        String response = getResponse(input);
        ui.addToDialog("You: " + input);
        ui.addToDialog("Duke: " + response);
        userInput.clear();
    }

    String getResponse(String userInput) {
        Command command = Parser.parseCommand(userInput);
        String description = Parser.parseDescription(userInput);
        switch (command) {
            //if user wants to exit, tasks are saved and exit message is shown
            case EXIT:
                return Parser.handleExit(storage,ui,tasks);
            // lists all the tasks out
            case LIST:
                return Parser.handleList(ui, tasks);

            case FIND:
                return Parser.handleFind(description,tasks);
            // unmarks task
            case UNMARK:
                return Parser.handleUnmark(description,tasks,ui);

            //marks the task
            case MARK:
                return Parser.handleMark(description, tasks, ui);
            // if user wants to add a todo object
            case TODO:
                return Parser.handleTodo(description, tasks);
            // if user wants to input deadline
            case DEADLINE:
                return Parser.handleDeadline(description, tasks);
            // if user wants to input an event
            case EVENT:
                return Parser.handleEvent(description, tasks);

            // if user wants to delete existing task
            case DELETE:
                return Parser.handleDelete(description,ui, tasks);

            // if user just enters a completely invalid command
            case INVALID:
                return Parser.handleInvalid(ui);

        }

        return "Duke heard: " + userInput;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
