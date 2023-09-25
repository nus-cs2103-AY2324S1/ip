package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parsers.Parser;
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

/**
 * The chatbot Duke.
 * Contains an Ui, Storage, TaskList and Parser object.
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates a new Duke object.
     *
     * @param filePath the file path
     */
    Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
        this.parser = new Parser(ui, storage, tasks);
        assert this.user != null : "no user image";
        assert this.duke != null : "no duke image";
    }

    /**
     * Instantiates a new Duke.
     */
    Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
        this.parser = new Parser(ui, storage, tasks);
        assert this.user != null : "no user image";
        assert this.duke != null : "no duke image";
    }

    /**
     * Runs the chatbot until "bye" is entered.
     */
    private void run() {
        ui.introMessage();
        Scanner scan = new Scanner(System.in);
        String userinput = scan.nextLine();

        while (!userinput.equalsIgnoreCase("bye")) {
            try {
                parser.parze(userinput);
            } catch (DukeException e) {
                System.out.println(e);
            }
            userinput = scan.nextLine();
        }

        ui.byeMessage();
    }

    /**
     * Gets dialog label.
     *
     * @param text the text
     * @return the dialog label
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
     * Gets ouput by using parser with given input.
     *
     * @param input input
     * @return String output
     */
    private String getResponse(String input) {
        try {
            return parser.parzeString(input);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
        stage.show();
    }
}
