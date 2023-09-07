package cheese;

import cheese.Ui.Ui;
import cheese.TaskList.TaskList;
import cheese.Storage.Storage;
import cheese.Parser.Parser;
import cheese.Task.Task;
import cheese.dialogbox.DialogBox;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents a Cheesebot that can add, delete, and mark tasks as done.
 *
 * @version v0.1
 */

public class Cheesebot  extends Application{

  /** The task list of the Cheesebot. */
  private TaskList taskList;
  /** The user interface of the Cheesebot. */
  private Ui ui;
  /** The storage of the Cheesebot. */
  private Storage storage;
  /** The parser of the Cheesebot. */
  private Parser parser = new Parser();

  // JavaFX
  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
  private Image cheesebotImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

  private ImageView userImageView = new ImageView(userImage);
  private ImageView cheesebotImageView = new ImageView(cheesebotImage);

  public Cheesebot() {
    this.taskList = new TaskList();
    this.ui = new Ui();
    this.storage = new Storage("./cheese.txt");
  }

  /**
   * Runs the Cheesebot.
   */
  public void run() {
    ui.showWelcome();
    taskList = storage.loadTask();
    Scanner scanner = new Scanner(System.in);
    String userInput;

    do {
      userInput = ui.getUserInput();
      processCommand(userInput);
    } while (!userInput.equals("bye"));

    storage.saveTask(taskList.getCheeseList());
    ui.showBye();
    scanner.close();
  }
  
  /**
   * Processes the user input.
   *
   * @param input The user input.
   */
  private void processCommand(String input) {
    if (parser.isCommand(input)) {
      String command = parser.getCommand(input);
      switch (command) {
        case "bye":
        break;
        case "list":
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
          ui.showMessage((i + 1) + ". " + taskList.getTask(i));
        }
        break;
        case "mark":
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.getSize()) {
          Task task = this.taskList.getTask(taskIndex);
          task.markAsDone();
          ui.showMessage("Nice! I've marked this task as done:\n" + task);
        } else {
          ui.showMessage("Task not found!");
        }
        break;
        case "todo":
        case "deadline":
        case "event":
        Task someTask = parser.parseTask(input);
        if (someTask != null) {
          taskList.addTask(someTask);
          ui.showMessage("Got it. I've added this task:\n  " + taskList.getTask(taskList.getSize() - 1)
            + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        } else {
          ui.showMessage("☹ OOPS!!! Task formatting is weird.");
        }
        break;
        case "delete":
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
          Task removedTask = taskList.getTask(taskNumber);
          taskList.deleteTask(taskNumber);
          ui.showMessage("Noted. I've removed this task:\n  " + removedTask
            + "\nNow you have " + taskList.getSize() + " tasks in the list.");
        } else {
          ui.showMessage("Task not found!");
        }
        break;
        case "find":
        String keyword = input.split(" ")[1];
        TaskList filteredList = taskList.filterTasks(keyword);
        if (filteredList.getSize() > 0) {
          ui.showMessage("Here are the matching tasks in your list:");
          for (int i = 0; i < filteredList.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + filteredList.getTask(i));
          }
        } else {
          ui.showMessage("No matching tasks found!");
        }
        break;
        default:
        ui.showMessage("I'm sorry, but I don't know what that means :-(");
      }
    } else {
      ui.showMessage("I'm sorry, but I don't know what that means :-(");
    }
  }

  public static void main(String[] args) {
    Cheesebot cheesebot = new Cheesebot();
    launch();
  }

  @Override
  public void start(Stage stage) {
    //Step 1. Setting up required components
    //
    //The container for the content of the chat to scroll.
    scrollPane = new ScrollPane();
    dialogContainer = new VBox();
    scrollPane.setContent(dialogContainer);

    userInput = new TextField();
    sendButton = new Button("Send");

    AnchorPane mainLayout = new AnchorPane();
    mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

    scene = new Scene(mainLayout);

    stage.setTitle("Cheesebot");
    stage.setResizable(false);
    stage.setMinHeight(600.0);
    stage.setMinWidth(400.0);

    mainLayout.setPrefSize(900.0, 600.0);

    scrollPane.setPrefSize(885, 535);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

    scrollPane.setVvalue(1.0);
    scrollPane.setFitToWidth(true);

    // You will need to import `javafx.scene.layout.AnchorPane`
    dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    userInput.setPrefWidth(325.0);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    //Step 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
      handleUserInput();
    });

    userInput.setOnAction((event) -> {
      handleUserInput();
    });
    
    dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    
    Label startMsg = startMessage();
    startMsg.setStyle("-fx-background-color: lightgreen");
    dialogContainer.getChildren().addAll(
      DialogBox.getCheesebotDialog(
        startMsg,
        new ImageView(cheesebotImage)
      )
    );

    stage.setScene(scene);
    stage.show();
  }

  /**
   * Iteration 1:
   * Creates a label with the specified text and adds it to the dialog container.
   * @param text The specified text.
   * @return The label with the specified text.
   */
  private Label startMessage() {
    // You will need to import `javafx.scene.control.Label`.
    Label textToAdd = new Label(ui.welcomeMessage());
    textToAdd.setWrapText(true);

    return textToAdd;
  }

  /**
   * Iteration 2:
   * Creates two dialog boxes, one echoing user input and the other containing Cheesebot's reply and then appends them to
   * the dialog container. Clears the user input after processing.
   */
  
  private void handleUserInput() {
    Label userText = new Label(userInput.getText());
    Label cheesebotText = new Label(getResponse(userInput.getText()));

    userText.setStyle("-fx-background-color: lightblue;"); // Set your desired color
    cheesebotText.setStyle("-fx-background-color: lightgreen;"); // Set your desired color
    dialogContainer.getChildren().addAll(
        DialogBox.getUserDialog(userText, new ImageView(userImage)),
        DialogBox.getCheesebotDialog(cheesebotText, new ImageView(cheesebotImage))
    );
    userInput.clear();
  }

  /**
   * You should have your own function to generate a response to user input.
   * Replace this stub with your completed method.
   */
  private String getResponse(String input) {
    StringBuilder response = new StringBuilder();

    if (parser.isCommand(input)) {
      String command = parser.getCommand(input);
      switch (command) {
        case "bye":
        response.append("Goodbye!"); // Add a response for the "bye" command
        // Optionally, you can add more text to the response here.
        break;
        case "list":
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
          response.append((i + 1)).append(". ").append(taskList.getTask(i)).append("\n");
        }
        break;
        case "mark":
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.getSize()) {
          Task task = this.taskList.getTask(taskIndex);
          task.markAsDone();
          response.append("Nice! I've marked this task as done:\n").append(task).append("\n");
        } else {
          response.append("Task not found!\n");
        }
        break;
        case "todo":
        case "deadline":
        case "event":
        Task someTask = parser.parseTask(input);
        if (someTask != null) {
          taskList.addTask(someTask);
          response.append("Got it. I've added this task:\n").append(taskList.getTask(taskList.getSize() - 1))
            .append("\nNow you have ").append(taskList.getSize()).append(" tasks in the list.\n");
        } else {
          response.append("☹ OOPS!!! Task formatting is weird.\n");
        }
        break;
        case "delete":
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
          Task removedTask = taskList.getTask(taskNumber);
          taskList.deleteTask(taskNumber);
          response.append("Noted. I've removed this task:\n").append(removedTask).append("\n")
            .append("Now you have ").append(taskList.getSize()).append(" tasks in the list.\n");
        } else {
          response.append("Task not found!\n");
        }
        break;
        case "find":
        String keyword = input.split(" ")[1];
        TaskList filteredList = taskList.filterTasks(keyword);
        if (filteredList.getSize() > 0) {
          response.append("Here are the matching tasks in your list:\n");
          for (int i = 0; i < filteredList.getSize(); i++) {
            response.append((i + 1)).append(". ").append(filteredList.getTask(i)).append("\n");
          }
        } else {
          response.append("No matching tasks found!\n");
        }
        break;
        default:
        response.append("I'm sorry, but I don't know what that means :-(");
      }
    } else {
      response.append("I'm sorry, but I don't know what that means :-(");
    }

    return response.toString();
  }



}

