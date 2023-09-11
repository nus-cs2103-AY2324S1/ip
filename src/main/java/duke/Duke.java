package duke;

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

public class Duke extends Application {
    private static final int MAX_TASKS = 100;
    private static final String DATA_FILE_PATH = "./docs/duke.txt";

    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage(DATA_FILE_PATH);
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image biubiu = new Image(this.getClass().getResourceAsStream("/images/BiuBiu.jpg"));

    /**
     * The main entry point of the Duke program.
     *
     * @param args  Command-line arguments (not used).
     */
    public static void main (String[] args) {
        ui.showWelcome();

        try {
            tasks = ui.loadTasks("./docs/duke.txt");
        } catch (Exception e) {
            ui.showLoadingError();
        }

        boolean isExit = false;

        while (!isExit) {
            String userCommand = ui.readCommand();
            isExit = ui.handleCommand(userCommand, tasks, storage);
        }
    }

    @Override
    public void start (Stage stage) {
//        Label helloWorld = new Label("Hello World!");
//        Scene scene = new Scene(helloWorld);
        // Step 1
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

        // Step 2
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

        // Step 3
        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        // Scroll down to the end automatically
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    private Label getDialogLabel (String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        // Label userText = new Label(userInput.getText());
        // Label dukeText = new Label(getResponse(userInput.getText()));
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                // DialogBox.getUserDialog(userText, new ImageView(user)),
                // DialogBox.getDukeDialog(dukeText, new ImageView(biubiu))
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, biubiu)
        );
        userInput.clear();
    }
    int taskCount = 0;
    public String getResponse (String input) {
        try {
            String output = "";
            if (input.equalsIgnoreCase("bye")) {
                storage.saveTasks(tasks);
                return "Bye. Have a great day!";
            } else if (input.equalsIgnoreCase("list")) {
                output = "Here are the tasks in your list:";
                for (int i = 0; i < tasks.size(); i++) {
                    output += "\n " + (i + 1) + ". " + tasks.getTask(i);
                }
                return output;
            }

            String[] parts = input.split(" ", 2);
            String commandType = parts[0].toLowerCase();
            String command = parts[1];

            switch (commandType) {
                case "mark":
                    int doneTaskIndex = Integer.parseInt(command) - 1;
                    tasks.markTaskAsDone(doneTaskIndex);
                    output = "Nice! I've marked this task as done:";
                    output += "\n " + tasks.getTask(doneTaskIndex);
                    storage.saveTasks(tasks);
                    return output;
                case "unmark":
                    int notDoneTaskIndex = Integer.parseInt(command) - 1;
                    tasks.markTaskAsNotDone(notDoneTaskIndex);
                    output = "Nice! I've marked this task as not done yet:";
                    output += "\n " + tasks.getTask(notDoneTaskIndex);
                    storage.saveTasks(tasks);
                    return output;
                case "todo":
                    if (parts.length == 1) {
                        return "What you want to do?";
                    }
                case "deadline":
                    if (parts.length == 1) {
                        return "What deadline do you have?";
                    }
                case "event":
                    if (parts.length == 1) {
                        return "What event do you have?";
                    }
                    Task newTask = Parser.parse(input);
                    if (newTask == null) {
                        break;
                    }

                    tasks.addTask(newTask);
                    taskCount++;
                    output = "Got it. I've added this task:";
                    output += "\n " + tasks.getTask(taskCount - 1);
                    System.out.println("\nNow you have " + taskCount + " tasks in the list.");
                    storage.saveTasks(tasks);
                    return output;
                case "delete":
                    int deletedTaskIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.deleteTask(deletedTaskIndex);
                    taskCount--;
                    output = "OK, I've removed this task.";
                    output += "\nNow you have " + taskCount + " tasks in the list.";
                    storage.saveTasks(tasks);
                    return output;
                case "find":
                    if (parts.length == 1) {
                        return "Please specify a keywork to search for.";
                    }
                    TaskList matchingTasks = tasks.findTasksByKeyword(command);
                    output = "Here are the matching tasks: ";
                    for (int i = 0; i < matchingTasks.size(); i++) {
                        output += "\n " + (i + 1) + "." + matchingTasks.getTask(i);
                    }
                    return output;
                default:
                    return "Invalid command format.";
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return input;
        }
        return "";
    }
}
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class duke.Duke {
//    private static final int MAX_TASKS = 100;
//    private static ArrayList<duke.Task> tasks = new ArrayList<>();
//    // private static duke.Task[] tasks = new duke.Task[MAX_TASKS];
//    private static int taskCount= 0;
//    private static final String DATA_FILE_PATH = "./docs/duke.txt";
//    public enum TaskType {
//        TODO, DEADLINE, EVENT
//    }
//    public enum TaskStatus {
//        DONE, NOT_DONE
//    }
//    private static void addTask(String userCommand) {
//        if (taskCount < MAX_TASKS) {
//            String[] parts = userCommand.split(" ", 2);
//            if (parts.length == 2) {
//                String taskType = parts[0].toLowerCase();
//                String taskDescription = parts[1];
//
//                switch (taskType) {
//                    case "todo":
//                        tasks.add(new duke.Todo(taskDescription));
//                        taskCount++;
//                        if (taskCount > 0) {
//                            System.out.println("Got it. I've added this task:");
//                            System.out.println(" " + tasks.get(taskCount - 1));
//                            System.out.println("Now you have " + taskCount + " tasks in the list.");
//                        }
//                        break;
//                    case "deadline":
//                        String[] deadlineParts = taskDescription.split(" /by ");
//                        if (deadlineParts.length == 2) {
//                            String deadlineTask = deadlineParts[0];
//                            String deadlineTime = deadlineParts[1];
//                            if (deadlineTask.trim().isEmpty()) {
//                                System.out.println("What kind of deadline do you have??");
//                            } else if (deadlineTime.trim().isEmpty()) {
//                                System.out.println("Can you tell me when is your deadline??");
//                            } else {
//                                tasks.add(new duke.Deadline(deadlineParts[0], deadlineParts[1]));
//                                taskCount++;
//                            }
//                            if (taskCount > 0) {
//                                System.out.println("Got it. I've added this task:");
//                                System.out.println(" " + tasks.get(taskCount - 1));
//                                System.out.println("Now you have " + taskCount + " tasks in the list.");
//                            }
//                        } else {
//                            System.out.println("Invalid deadline format.");
//                        }
//                        break;
//                    case "event":
//                        String[] eventParts = taskDescription.split(" /from | /to ");
//                        if (eventParts.length == 3) {
//                            String eventTask = eventParts[0];
//                            String eventStartTime = eventParts[1];
//                            String eventEndTime = eventParts[2];
//                            if (eventTask.trim().isEmpty()) {
//                                System.out.println("What event do you have?");
//                            } else if (eventStartTime.trim().isEmpty()) {
//                                System.out.println("When will the event start?");
//                            } else if (eventEndTime.trim().isEmpty()) {
//                                System.out.println("When will the event end?");
//                            } else {
//                                tasks.add(new duke.Event(eventParts[0], eventParts[1], eventParts[2]));
//                                taskCount++;
//                            }
//                            if (taskCount > 0) {
//                                System.out.println("Got it. I've added this task:");
//                                System.out.println(" " + tasks.get(taskCount - 1));
//                                System.out.println("Now you have " + taskCount + " tasks in the list.");
//                            }
//                        } else {
//                            System.out.println("Invalid event format.");
//                        }
//                        break;
//                    default:
//                        System.out.println("Invalid command format.");
//                        break;
//                }
//
//            } else {
//                if (parts[0].equalsIgnoreCase("todo")) {
//                    System.out.println("What you want to do?");
//                } else if (parts[0].equalsIgnoreCase("deadline")) {
//                    System.out.println("What deadline do you have??");
//                } else if (parts[0].equalsIgnoreCase("event")) {
//                    System.out.println("What event are you going to attend??");
//                } else {
//                    System.out.println("Sorry, I don't know what that means :(");
//                }
//            }
//        } else {
//            System.out.println("Sorry, the task list is full.");
//        }
//    }
//    private static void deleteTask (String userCommand) {
//        try {
//            int index = Integer.parseInt(userCommand.split(" ")[1]) - 1;
//            if (index >= 1 && index <= taskCount) {
//                duke.Task removedTask = tasks.remove(index);
//                taskCount--;
//                System.out.println("OK, I've removed this task.");
//            } else {
//                System.out.println("Invalid task index.");
//            }
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            System.out.println("Invalid command format.");
//        }
//
//    }
//    private static void listTasks() {
//        if (taskCount == 0) {
//            System.out.println("The task list is empty!");
//        } else {
//            for(int i = 0; i < taskCount; i++) {
//                System.out.println((i + 1) + ". " + tasks.get(i));
//            }
//        }
//    }
//    private static boolean isValidTaskIndex(int taskIndex) {
//        return taskIndex >= 0 && taskIndex < taskCount;
//    }
//    private static void markTask(String userCommand) {
//        try {
//            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
//            if (isValidTaskIndex(taskIndex)) {
//                duke.Task taskToMark = tasks.get(taskIndex);
//                taskToMark.markAsDone();
//                System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
//            } else {
//                System.out.println("Invalid task number. Please enter a valid task number.");
//            }
//        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//            System.out.println("Invalid command format. Please use 'mark [task number]'.");
//        }
//    }
//    private static void unmarkTask(String userCommand) {
//        try {
//            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
//            if (isValidTaskIndex(taskIndex)) {
//                duke.Task taskToUnmark = tasks.get(taskIndex);
//                taskToUnmark.markAsNotDone();
//                System.out.println("OK, I've marked this task as not done yet:\n  " + taskToUnmark);
//            } else {
//                System.out.println("Invalid task number. Please enter a valid task number.");
//            }
//        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//            System.out.println("Invalid command format. Please use 'unmark [task number]'.");
//        }
//    }
//
//    private static void loadTasks() {
//        File file = new File(DATA_FILE_PATH);
//
//        if (file.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] taskData = line.split(" \\| ");
//                    if (taskData.length >= 2) {
//                        TaskType taskType = TaskType.valueOf(taskData[0]);
//                        String taskDescription = taskData[1];
//                        String taskTime1 = (taskData.length > 2) ? taskData[2] : "";
//                        String taskTime2 = (taskData.length > 3) ? taskData[3] : "";
//
//                        switch (taskType) {
//                            case TODO:
//                                tasks.add(new duke.Todo(taskDescription));
//                                break;
//                            case DEADLINE:
//                                tasks.add(new duke.Deadline(taskDescription, taskTime1));
//                                break;
//                            case EVENT:
//                                tasks.add(new duke.Event(taskDescription, taskTime1, taskTime2));
//                                break;
//                            default:
//                                System.out.println("Invalid task type: " + taskType);
//                                break;
//                        }
//                        taskCount++;
//                    } else {
//                        System.out.println("Skipping corrupted task data: " + line);
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error loading tasks.");
//            }
//        } else {
//            System.out.println("Data file does not exist.");
//        }
//    }
//
//    private static void saveTask() {
//        File file = new File(DATA_FILE_PATH);
//        file.getParentFile().mkdirs();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            for (duke.Task task : tasks) {
//                writer.write(task.toDataString());
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving tasks.");
//        }
//    }
//
//    public static void main(String[] args) {
//        loadTasks(); // Load tasks from duke.txt
//
//        String greeting = "Hi, I'm BiuBiu.\nWhat can I do for you?";
//        System.out.println(greeting);
//        String exit = "Bye. Have a great day!";
//
//        Scanner scanner = new Scanner(System.in);
//        while(true) {
//            String userCommand = scanner.nextLine();
//            if(userCommand.equalsIgnoreCase("bye")) {
//                System.out.println(exit);
//                break;
//            } else if (userCommand.equalsIgnoreCase("list")) {
//                System.out.println("Here are the tasks in your list:");
//                listTasks();
//            } else if (userCommand.startsWith("mark ")) {
//                markTask(userCommand);
//            } else if (userCommand.startsWith("unmark ")) {
//                unmarkTask(userCommand);
//            } else if (userCommand.startsWith("delete ")) {
//                deleteTask(userCommand);
//            }else {
//                addTask(userCommand);
//            }
//            saveTask();
//        }
//    }
//}


/*
TODO | read book
DEADLINE | project | 2022-10-10
EVENT | meeting | 2023-06-12 1400 | 2023-06-12 1800
 */