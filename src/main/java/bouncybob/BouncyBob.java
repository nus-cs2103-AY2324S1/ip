package bouncybob;

import jdk.jfr.Event;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import bouncybob.task.Task;
import bouncybob.task.Deadlines;
import bouncybob.task.Events;
import bouncybob.task.ToDos;
import bouncybob.util.TaskList;
import bouncybob.util.TaskFileHandler;
import bouncybob.util.Parser;
import bouncybob.util.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * Main class for the BouncyBob application.
 */
public class BouncyBob extends Application {
    private TaskList taskList = new TaskList();

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("BouncyBob's List");
        ListView<Task> taskListView = new ListView<>();

        // Load tasks from disk
        TaskFileHandler.loadTasksFromDisk(taskList);
        taskListView.getItems().addAll(taskList.getTasks());

        taskListView.setCellFactory(task -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Button markDoneButton = new Button("Toggle status");
                    markDoneButton.setOnAction(e -> {
                        item.toggleDone();
                        updateItem(item, false); // Refresh the cell
                    });

                    Label statusLabel = new Label();
                    if (item.isDone()) {
                        statusLabel.setText("[Done] " + statusLabel.getText());
                    } else {
                        statusLabel.setText("[Not Done] " + statusLabel.getText());
                    }

                    Label taskLabel = new Label(item.getDescription());
                    HBox.setHgrow(taskLabel, Priority.ALWAYS);

                    Region spacerLeft = new Region();
                    HBox.setHgrow(spacerLeft, Priority.ALWAYS);
                    Region spacerRight = new Region();
                    HBox.setHgrow(spacerRight, Priority.ALWAYS);


                    HBox hbox = new HBox(10, statusLabel, spacerLeft, taskLabel, spacerRight, markDoneButton);
                    setGraphic(hbox);
                }
            }

        });


        TextField taskInputField = new TextField();
        taskInputField.setPromptText("Enter a task...");

        // Add button for adding tasks
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String userInput = taskInputField.getText().trim();
            if (!userInput.isEmpty()) {
                String[] parts = userInput.split(" ");

                try {
                    Task newTask = createTask(parts);
                    taskListView.getItems().add(newTask);
                    taskInputField.clear();
                } catch (IllegalArgumentException ex) {
                    // Handle invalid inputs (e.g., show a dialog to the user).
                }
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Task selectedItem = taskListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                taskListView.getItems().remove(selectedItem);
            }
        });

        // Horizontal box for TextField and Buttons
        HBox inputBox = new HBox(10, taskInputField, addButton, deleteButton);

        // Vertical box for entire layout
        VBox rootLayout = new VBox(20, titleLabel, taskListView, inputBox);
        rootLayout.setPadding(new Insets(20));

        Scene scene = new Scene(rootLayout, 800, 800);
        stage.setTitle("BouncyBob To-Do List Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enum representing the types of tasks.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT, UNKNOWN
    }

    /**
     * Enum representing the types of actions that can be performed on tasks.
     */
    public enum Action {
        MARK, UNMARK, DELETE, UNKNOWN
    }

    /**
     * Adds a task to the task list and prints the task.
     *
     * @param parts    The parsed user input.
     * @param taskList The task list.
     */
    private static void addTaskAndPrint(String[] parts, TaskList taskList) {
        TaskType taskType = Parser.getTaskType(parts[0]);
        String taskName = "";
        Task newTask = null;
        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(parts);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDos(taskName);
                break;
            case DEADLINE:
                taskName = Parser.removeAction(parts);
                String datetime = Parser.extractDatetime(taskName);
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = Parser.getTaskDeadline(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadlines(taskName, datetime);
                break;
            case EVENT:
                taskName = Parser.removeAction(parts);
                String[] fromTo = Parser.extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = Parser.getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'event' cannot be empty.");
                }
                newTask = new Events(taskName, fromTo[0], fromTo[1]);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        taskList.addTask(newTask);
        Ui.printTaskCount(taskList.size() - 1, newTask);  // Adjusted to size of ArrayList
    }

    /**
     * Creates a task based on the action specified.
     *
     * @param parts The parsed user input.
     * @return The task created.
     */
    private static Task createTask(String[] parts) {
        TaskType taskType = Parser.getTaskType(parts[0]);
        String taskName = "";
        Task newTask = null;
        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(parts);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDos(taskName);
                break;
            case DEADLINE:
                taskName = Parser.removeAction(parts);
                String datetime = Parser.extractDatetime(taskName);
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = Parser.getTaskDeadline(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadlines(taskName, datetime);
                break;
            case EVENT:
                taskName = Parser.removeAction(parts);
                String[] fromTo = Parser.extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = Parser.getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'event' cannot be empty.");
                }
                newTask = new Events(taskName, fromTo[0], fromTo[1]);
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        return newTask;
    }

    /**
     * Modifies a task in the task list based on the action specified.
     *
     * @param parts    The parsed user input.
     * @param taskList The task list.
     */
    public static void modifyTask(String[] parts, TaskList taskList) {
        Action action = Parser.getAction(parts[0]);
        int index = Integer.parseInt(parts[1]); // Adjust for 0-based index
        switch(action) {
            case MARK:
                taskList.getTask(index).setDone();
                Ui.printTaskStatus(taskList.getTask(index), action);
                break;
            case UNMARK:
                taskList.getTask(index).setUnDone();
                Ui.printTaskStatus(taskList.getTask(index), action);
                break;
            case DELETE:
                Ui.printTaskStatus(taskList.getTask(index), action);
                taskList.removeTask(index);
                break;
        }
    }


    /**
     * The main method for the BouncyBob application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui.printIntro();
        TaskFileHandler.loadTasksFromDisk(taskList);

        while (true) {
            System.out.println("Enter something:");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");

            if (userInput.equals("bye")) {
                Ui.printBye();
                break;
            } else if (userInput.equals("list")) {
                Ui.printDatabase(taskList);  // Adjusted for ArrayList
            } else if (parts[0].equals("find")) {
                String subString = Parser.removeAction(parts);
                TaskList subTaskList = taskList.getSubTaskList(subString);
                Ui.printDatabase(subTaskList);
            } else if (Parser.getAction(parts[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(parts, taskList);
                    TaskFileHandler.saveTasksToDisk(taskList);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printIndexOutOfBound();
                }
            } else {
                try {
                    addTaskAndPrint(parts, taskList);
                    TaskFileHandler.saveTasksToDisk(taskList);
                } catch (IllegalArgumentException e) {
                    Ui.printIllegalArgumentException(e);
                } catch (DateTimeParseException e) {
                    Ui.printDateTimeParseException();
                }
            }
        }
    }
}
