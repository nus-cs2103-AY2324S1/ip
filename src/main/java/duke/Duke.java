package duke;

import java.net.URL;
import java.util.Scanner;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import duke.ui.Ui;

import duke.util.TaskList;
import duke.components.DialogBox;
import duke.exceptions.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Duke is a personal assistant chatbot that helps a person to keep track of various things.
 */

public class Duke extends Application {
    
    public enum CommandType {
        LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(storage.readTasks());
            this.parser = new Parser();

        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

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

    private void run() {
        ui.printWelcomeMessage();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    break;
                }
                CommandType commandType = parser.parseCommandType(input);
                handleCommand(commandType, input);
            }
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        } catch (Exception e) {
            ui.printErrorMessage(new DukeException("An unexpected error occurred: " + e.getMessage()));
        }
        ui.printFarewellMessage();
    }

    /*
     * Main entry point of the Duke application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        
        Duke ekud = new Duke();
        ekud.run();
    }

    

    private void handleCommand(CommandType commandType, String command) throws DukeException {
        switch (commandType) {
        case LIST:
            ui.printList(tasks.getTasks());
            break;
        case MARK:
            markTask(command);
            break;
        case DELETE:
            deleteTask(command);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            addTask(command);
            break;
        case FIND:
            handleFind(command);
            break;
        case UNKNOWN:
            ui.printErrorMessage(new DukeException("I'm sorry, but I don't know what that means :-("));
            break;
        }
    }

    private void handleFind(String command) {
        ui.printFindMessage();
        String keyword = command.split(" ")[1];
        TaskList filtered = tasks.filter(keyword);
        ui.printList(filtered.getTasks());
    }

    private void addTask(String task) {
        try {
            Task newTask = null;
            if (task.startsWith("todo")) {
                newTask = ToDo.createToDoFromCommand(task);
            } else if (task.startsWith("deadline")) {
                newTask = Deadline.createDeadlineFromCommand(task);
            } else if (task.startsWith("event")) {
                newTask = Event.createEventFromCommand(task);
            }

            if (newTask != null) {
                tasks.add(newTask);
                storage.write(newTask);
                ui.printAddedTaskConfirmation(newTask, tasks);
            } 
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }


    private void markTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            storage.write(tasks.getTasks());
            ui.printMarkedTaskConfirmation(task);            
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    private void deleteTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task task = tasks.remove(index);
            storage.write(tasks.getTasks());
            ui.printDeletedTaskConfirmation(task, tasks);    
        } catch (NumberFormatException e) {
            ui.printErrorMessage(new DukeException("Invalid command format"));
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        } 
    }


}
