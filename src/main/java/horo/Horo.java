package horo;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

import horo.data.Deadline;
import horo.data.Event;
import horo.data.TaskList;
import horo.data.Todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Horo extends Application {

  private Storage storage;
  private TaskList taskList;
  private Ui ui;
  private static Scanner scanner = new Scanner(System.in);

  private ScrollPane scrollPane;
  private VBox dialogContainer;
  private TextField userInput;
  private Button sendButton;
  private Scene scene;

  public Horo() {
  }

  public Horo(String filePath) {
    ui = new Ui();
    storage = new Storage(filePath);
    taskList = storage.load();
  }

  @Override
  public void start(Stage stage) throws Exception {
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

    dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

    userInput.setPrefWidth(325.0);

    sendButton.setPrefWidth(55.0);

    AnchorPane.setTopAnchor(scrollPane, 1.0);

    AnchorPane.setBottomAnchor(sendButton, 1.0);
    AnchorPane.setRightAnchor(sendButton, 1.0);

    AnchorPane.setLeftAnchor(userInput, 1.0);
    AnchorPane.setBottomAnchor(userInput, 1.0);

    // new Horo("./data/tasks.txt").run();
  }

  public void run() {
    ui.showWelcome();

    while (true) {
      System.out.print(">");
      String input = scanner.nextLine();
      Matcher m;
      Command command;
      try {
        command = Command.commandParser(input);
        m = command.getMatcher(input);
      } catch (HoroException e) {
        System.out.println(e.getMessage());
        continue;
      }

      switch (command) {
        case BYE:
          exit();
          break;
        case LIST:
          taskList.showTasks();
          break;
        case MARK:
          taskList.markTaskDone(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case UNMARK:
          taskList.markTaskDone(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case DELETE:
          taskList.removeTask(Integer.parseInt(m.group(1)) - 1);
          storage.updateTaskData(taskList);
          break;
        case TODO:
          try {
            taskList.addTask(new Todo(m.group(1)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case DEADLINE:
          try {
            taskList.addTask(new Deadline(m.group(1), m.group(2)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case EVENT:
          try {
            taskList.addTask(new Event(m.group(1), m.group(2), m.group(3)));
          } catch (HoroException e) {
            System.out.println(e.getMessage());
            break;
          }
          storage.updateTaskData(taskList);
          break;
        case FIND:
          taskList.findTask(Arrays.asList(m.group(1).split(" ")));
          break;
        default:
          break;
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static void exit() {
    System.out.println("Bye. Hope to see you again soon!");
    scanner.close();
    System.exit(0);
  }
}