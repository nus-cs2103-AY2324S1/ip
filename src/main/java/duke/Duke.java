package duke;

import java.io.IOException;
import java.io.File;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class for the ChatBot
 */
public class Duke extends Application {
    public static final String TEXTFILE = "duke.txt";
    public static final String DATAFILE = "data.txt";
    public static final String DELIM = " ";
    public static final String GREETING = "-------------------------------\n"
            + "Hello! I'm Skog.\n"
            + "What can I do for you?\n"
            + "-------------------------------\n";
    public static final String EXIT = "-------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-------------------------------\n";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for the Duke class.
     * @param filePath Specifies the name of the text file
     */
    public Duke(String filePath) {
        this.storage = new Storage(TEXTFILE, DATAFILE);
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public Duke(){}
    /**
     * Method to initiate the chatbot.
     */
    public void run() {
        try {
            File myFile = new File(TEXTFILE);
            if (myFile.createNewFile()) {
                System.out.println("-------------------------------\n"
                        + "Welcome! New text file created.");
            } else {
                // go straight into task control
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }

        System.out.println(GREETING);

        while (ui.sc.hasNext()) {
            try {
                String[] arr = ui.sc.nextLine().split(DELIM);
                String type = arr[0];

                if (type.equals("bye")) {
                    ui.end();
                    storage.saveTextFile(tasks);
                    storage.saveDataFile(tasks);
                    System.out.println(EXIT);
                    break;
                } else if (type.equals("list")) {
                    tasks.listOut();
                } else if (type.equals("mark")) {
                    tasks.mark(arr);
                } else if (type.equals("unmark")) {
                    tasks.unmark(arr);
                } else if (type.equals("delete")) {
                    tasks.delete(arr);
                } else if (type.equals("find")) {
                    tasks.find(arr);
                } else {
                    // check for task type first
                    if (type.equals("todo")) {
                        if (arr.length == 1) {
                            throw new EmptyDescription();
                        } else {
                            String desc = tasks.getDescription(arr);
                            tasks.addTodo(desc);
                        }
                    } else if (type.equals("deadline")) {
                        String desc = tasks.getDescription(arr);
                        String date = tasks.getDeadline(arr);
                        tasks.addDeadline(desc, date);
                    } else if (type.equals("event")) {
                        String desc = tasks.getDescription(arr);
                        String timeline = tasks.getEventTimeline(arr);
                        tasks.addEvent(desc, timeline);
                    } else {
                        throw new WrongInput();
                    }
                }
            } catch (EmptyDescription | WrongInput e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
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

    public static void main(String[] args) {
        new Duke(TEXTFILE).run();
    }
}
