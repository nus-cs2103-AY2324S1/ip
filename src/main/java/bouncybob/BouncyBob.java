package bouncybob;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import bouncybob.gui.TaskCell;
import bouncybob.task.Deadline;
import bouncybob.task.Event;
import bouncybob.task.Task;
import bouncybob.task.ToDo;
import bouncybob.util.Parser;
import bouncybob.util.TaskFileHandler;
import bouncybob.util.TaskList;
import bouncybob.util.TaskManager;
import bouncybob.util.Ui;

/**
 * Main class for the BouncyBob application.
 */
public class BouncyBob extends Application {

    private TaskList taskList = new TaskList();
    private TaskManager taskManager;

    /**
     * Starts the BouncyBob application.
     *
     * @param stage The stage for the application.
     */
    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("BouncyBob's List");
        ListView<Task> taskListView = initializeTaskListView();
        taskManager = new TaskManager(taskListView);

        HBox inputBox = createTaskInputBox(taskListView);

        VBox rootLayout = new VBox(20, titleLabel, taskListView, inputBox);
        rootLayout.setPadding(new Insets(20));

        Scene scene = new Scene(rootLayout, 800, 800);

        stage.setTitle("BouncyBob To-Do List Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the task list view.
     *
     * @return The task list view.
     */
    private ListView<Task> initializeTaskListView() {
        ListView<Task> taskListView = new ListView<>();
        TaskFileHandler.loadTasksFromDisk(taskList);
        taskListView.getItems().addAll(taskList.getTasks());
        taskListView.setCellFactory(task -> new TaskCell());
        return taskListView;
    }

    /**
     * Creates the task input box.
     *
     * @param taskListView The task list view.
     * @return The task input box.
     */
    private HBox createTaskInputBox(ListView<Task> taskListView) {
        ComboBox<TaskType> taskTypeComboBox = new ComboBox<>();
        taskTypeComboBox.getItems().addAll(TaskType.TODO, TaskType.DEADLINE, TaskType.EVENT);
        taskTypeComboBox.setValue(TaskType.TODO);

        TextField taskInputField = new TextField();
        taskInputField.setPromptText("Enter a task...");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAddAction(taskTypeComboBox, taskInputField, taskListView));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDeleteAction(taskListView));

        return new HBox(10, taskTypeComboBox, taskInputField, addButton, deleteButton);
    }

    /**
     * Handles the add action.
     *
     * @param taskTypeComboBox The combo box for the task type.
     * @param taskInputField   The text field for the task input.
     * @param taskListView     The task list view.
     */
    private void handleAddAction(ComboBox<TaskType> taskTypeComboBox, TextField taskInputField, ListView<Task> taskListView) {
        String userInput = taskInputField.getText().trim();
        if (userInput.isEmpty()) {
            return;
        }
        String[] parts = userInput.split(" ");
        System.out.println(parts);
        if (Parser.getAction(parts[0]) == Action.NOTE) {
            String index = parts[1];
            String note = Parser.getNoteFromCommand(parts);
            taskManager.setNoteForTask(index, note);
            return;
        } else {
            userInput = taskTypeComboBox.getValue().toString().toLowerCase() + " " + userInput;
            parts = userInput.split(" ");
            try {
                Task newTask = createTask(parts);
                taskManager.addTask(newTask);
                taskManager.saveTasks();
            } catch (IllegalArgumentException ex) {
                showErrorDialog("Error", "Invalid Input", ex.getMessage());
            }
        }
        taskInputField.clear();
    }

    /**
     * Handles the delete action.
     *
     * @param taskListView The task list view.
     */
    private void handleDeleteAction(ListView<Task> taskListView) {
        Task selectedItem = taskListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            taskListView.getItems().remove(selectedItem);
        }
        ObservableList<Task> tasksFromListView = taskListView.getItems();
        TaskFileHandler.saveTasksToDisk(tasksFromListView);
    }
    /**
     * Shows an error dialog.
     *
     * @param title   The title of the dialog.
     * @param header  The header of the dialog.
     * @param content The content of the dialog.
     */
    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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
        MARK, UNMARK, DELETE, NOTE, UNKNOWN
    }

    /**
     * Adds a task to the task list and prints the task.
     *
     * @param userInputSplits    The parsed user input.
     * @param taskList The task list.
     */
    private static void addTaskAndPrint(String[] userInputSplits, TaskList taskList) {
        TaskType taskType = Parser.getTaskType(userInputSplits[0]);
        assert taskType != TaskType.UNKNOWN : "Unknown task type encountered.";
        String taskName = "";
        Task newTask = null;

        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(userInputSplits);
                assert !taskName.trim().isEmpty() : "Task name is unexpectedly empty.";
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDo(taskName);
                break;
            case DEADLINE:
                taskName = Parser.removeAction(userInputSplits);
                assert !taskName.trim().isEmpty() : "Task name is unexpectedly empty.";
                String datetime = Parser.extractDatetime(taskName);
                assert !datetime.trim().isEmpty() : "Datetime is unexpectedly empty.";
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = Parser.getTaskDeadline(taskName);
                assert !taskName.trim().isEmpty() : "Task name is unexpectedly empty.";
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadline(taskName, datetime);
                break;
            case EVENT:
                taskName = Parser.removeAction(userInputSplits);
                String[] fromTo = Parser.extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = Parser.getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'event' cannot be empty.");
                }
                newTask = new Event(taskName, fromTo[0], fromTo[1]);
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
     * @param userInputSplits The parsed user input.
     * @return The task created.
     */
    private static Task createTask(String[] userInputSplits) {
        TaskType taskType = Parser.getTaskType(userInputSplits[0]);
        assert taskType != TaskType.UNKNOWN : "Unknown task type encountered.";
        String taskName = "";
        Task newTask = null;

        switch (taskType) {
            case TODO:
                taskName = Parser.removeAction(userInputSplits);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'todo' cannot be empty.");
                }
                newTask = new ToDo(taskName);
                break;
            case DEADLINE:
                taskName = Parser.removeAction(userInputSplits);
                String datetime = Parser.extractDatetime(taskName);
                if (datetime.trim().isEmpty()) {
                    throw new IllegalArgumentException("/by cannot be empty!");
                }
                taskName = Parser.getTaskDeadline(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'deadline' cannot be empty.");
                }
                newTask = new Deadline(taskName, datetime);
                break;
            case EVENT:
                taskName = Parser.removeAction(userInputSplits);
                String[] fromTo = Parser.extractFromTo(taskName);
                if (fromTo[0] == null || fromTo[1] == null) {
                    throw new IllegalArgumentException("/from and /to cannot be empty!");
                }
                taskName = Parser.getTaskEvent(taskName);
                if (taskName.trim().isEmpty()) {
                    throw new IllegalArgumentException("bouncybob.task.Task name for 'event' cannot be empty.");
                }
                newTask = new Event(taskName, fromTo[0], fromTo[1]);
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
            String[] userInputSplits = userInput.split(" ");
            assert userInputSplits.length > 0 : "User input is unexpectedly empty.";

            if (userInput.equals("bye")) {
                Ui.printBye();
                break;
            } else if (userInput.equals("list")) {
                Ui.printDatabase(taskList);  // Adjusted for ArrayList
            } else if (userInputSplits[0].equals("find")) {
                String subString = Parser.removeAction(userInputSplits);
                TaskList subTaskList = taskList.getSubTaskList(subString);
                Ui.printDatabase(subTaskList);
            } else if (Parser.getAction(userInputSplits[0]) != Action.UNKNOWN) {
                try {
                    modifyTask(userInputSplits, taskList);
                    TaskFileHandler.saveTasksToDisk(taskList);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printIndexOutOfBound();
                }
            } else {
                try {
                    addTaskAndPrint(userInputSplits, taskList);
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
