package duke;
import java.io.IOException;

import duke.command.Command;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * MeowBot is the main class which runs the iP project in its main function
 * @since 2023-08-30
 */


public class MeowBot {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final String FILE_NAME;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Represents the 2 possible inputs, that is either User Dialog or response from MeowBot
     */

    public enum dialog {
        USER,
        MEOWBOT

    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * @param file indicates the location of the file where data is stored
     * @throws DukeException when generating the tasks back from the data file
     * @throws IOException when the data file cannot be found
     */

    public MeowBot(String file) throws DukeException, IOException {
        this.ui = new Ui();
        this.FILE_NAME = file;
        this.storage = new Storage(this.FILE_NAME);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Meow???? I cant find your data");
            storage.createNewFile();
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            throw new DukeException("Meow???? I cannot write to your files :(");
        }
    }

    /**
     * Empty default constructor for MeowBot
     */

    public MeowBot() {
        this.ui = new Ui();
        this.FILE_NAME = "stop";
    }

    /**
     * Main method to run the MeowBot that will execute commands given
     */

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        ui.bye();
    }

//    @Override
//    public void start(Stage stage) {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
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
//        dialogContainer.setPadding(new Insets(10, 10, 5, 5));
//        dialogContainer.setSpacing(10);
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
//
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
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "Invalid";
        try {
            response = Parser.parse(input).execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }


    public static void main(String[] args) throws DukeException, IOException {
        new MeowBot("src/main/data/meowbot.txt").run();
    }

}
