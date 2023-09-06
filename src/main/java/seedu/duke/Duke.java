package seedu.duke;

//import java.util.Scanner;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.layout.Region;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

/**
 * Encapsulates the Duke class
 * Duke is the chatbot that runs the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    // For JavaFX
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");

        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(storage, tasks, ui);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

//    /**
//     * Executes the chatbot program.
//     */
//    public void run() {
//        System.out.println("I am in method run()!");
//        ui.printIntro();
//
//        Scanner sc = new Scanner(System.in);
//        // Get user command
//        String cmd = sc.nextLine();
//
//        // Parse the cmd as long as it is not "bye"
//        while (!cmd.equals("bye")) {
//            String answer = parser.parse(cmd);
//            System.out.println("I have parsed the cmd and the output I get is " + answer);
////            getResponse(answer);
//            System.out.println("getResponse() has returned. Waiting for next input");
//            cmd = sc.nextLine();
//        }
//
//        ui.printExit();
//    }

//    @Override
//    public void start(Stage stage) {
//        System.out.println("I am in start!");
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//        System.out.println("All main components have been set!");
//
//        // Create a label to display the welcome message
//        Label welcomeLabel = new Label("____________________________________________________________\n" +
//                " Hello! I'm Dookie\n" +
//                " What can I do for you?\n" +
//                "____________________________________________________________");
//
//        System.out.println("Welcome label is " + welcomeLabel);
//        // Add the welcome label to the dialog container
//        dialogContainer.getChildren().add(new DialogBox(welcomeLabel, new ImageView(duke)));
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        stage.setScene(scene);
//        stage.show();
//    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                new DialogBox(userText, new ImageView(user)),
//                new DialogBox(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }

    /**
     * Returns a string containing Duke's response to the user input.
     *
     * @param input The command given by the user.
     * @return The result of parsing that command.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
//    public static void main(String[] args) {
//        new Duke().run();
//    }
}