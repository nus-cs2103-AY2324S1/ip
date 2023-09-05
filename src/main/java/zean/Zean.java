package zean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import zean.exception.DukeException;
import zean.gui.DialogBox;

/**
 * The main class for the chatbot.
 *
 * @author Zhong Han
 */
public class Zean extends Application {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Zean() {
        new Zean("./data/zean.txt");
    }

    /**
     * Constructor for the chatbot zean.
     *
     * @param filePath The filepath of the data to be retrieved or written.
     */
    public Zean(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage);
        } catch (FileNotFoundException e) {
            this.ui.showError("OOPS! Something went wrong with the file."
                    + "\nShutting down now...");
        } catch (IOException e) {
            this.ui.showError("OOPS! The file cannot be created.\nShutting down now...");
        } catch (SecurityException e) {
            this.ui.showError("OOPS! The file cannot be written due to invalid access."
                    + "\nShutting down now...");
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.greet("Zean");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.strip().equals("bye")) {
                break;
            }
            try {
                this.ui.printOutput(Parser.parse(input, this.tasks));
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            }
        }
        sc.close();
        this.ui.exit();

    }

    @Override
    public void start(Stage stage) {
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

        // Customising the window
        stage.setTitle("Zean");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400, 545);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(340.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        outputMessage("Hi there, I'm Zean!\nHow can I help you today?");

        // Handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            userInput.clear();
        });

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
    @FXML
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
        String output = "";
        if (input.strip().equals("bye")) {
            return "Bye! Have a nice day!";
        }
        try {
            output = Parser.parse(input, this.tasks);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    private void outputMessage(String msg) {
        Label dukeText = new Label(msg);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    @Override
    public void init() {
        String filePath = "./data/zean.txt";
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage);
        } catch (FileNotFoundException e) {
            this.ui.showError("OOPS! Something went wrong with the file."
                    + "\nShutting down now...");
        } catch (IOException e) {
            this.ui.showError("OOPS! The file cannot be created.\nShutting down now...");
        } catch (SecurityException e) {
            this.ui.showError("OOPS! The file cannot be written due to invalid access."
                    + "\nShutting down now...");
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Zean("./data/zean.txt").run();
    }
}
