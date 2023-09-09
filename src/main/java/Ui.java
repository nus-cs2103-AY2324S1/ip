import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public TaskList loadTasks(String filePath) {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] taskData = line.split(" \\| ");
                    if (taskData.length >= 2) {
                        Storage.TaskType taskType = Storage.TaskType.valueOf(taskData[0]);
                        String taskDescription = taskData[1];
                        String taskTime1 = (taskData.length > 2) ? taskData[2] : "";
                        String taskTime2 = (taskData.length > 3) ? taskData[3] : "";

                        switch (taskType) {
                            case TODO:
                                tasks.addTask(new Todo(taskDescription));
                                break;
                            case DEADLINE:
                                tasks.addTask(new Deadline(taskDescription, taskTime1));
                                break;
                            case EVENT:
                                tasks.addTask(new Event(taskDescription, taskTime1, taskTime2));
                                break;
                            default:
                                System.out.println("Invalid task type: " + taskType);
                                break;
                        }
                    } else {
                        System.out.println("Skipping corrupted task data: " + line);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks.");
            }
        } else {
            System.out.println("Data file does not exist.");
        }

        return tasks;
    }
    public boolean handleCommand(String userCommand, TaskList tasks, Storage storage) {
        try {
            String[] parts = userCommand.split(" ", 2);
            String commandType = parts[0].toLowerCase();

            switch (commandType) {
                case "bye":
                    showExit();
                    storage.saveTasks(tasks);
                    return true;
                case "list":
                    showTaskList(tasks);
                    break;
                case "mark":
                    int doneTaskIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.markTaskAsDone(doneTaskIndex);
                    showTaskMarkedAsDone(tasks.getTask(doneTaskIndex));
                    storage.saveTasks(tasks);
                    break;
                case "unmark":
                    int notDoneTaskIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.markTaskAsNotDone(notDoneTaskIndex);
                    showTaskMarkedAsNotDone(tasks.getTask(notDoneTaskIndex));
                    storage.saveTasks(tasks);
                    break;
                case "todo":
                    if (parts.length == 1) {
                        System.out.println("What you want to do?");
                        break;
                    }
                case "deadline":
                    if (parts.length == 1) {
                        System.out.println("What deadline do you have?");
                        break;
                    }
                case "event":
                    if (parts.length == 1) {
                        System.out.println("What event do you have?");
                        break;
                    }
                    Task newTask = Parser.parse(userCommand);
                    if (newTask == null) {
                        break;
                    }
                    tasks.addTask(newTask);
                    showTaskAdded(newTask, tasks.size());
                    storage.saveTasks(tasks);
                    break;
                case "delete":
                    int deletedTaskIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.deleteTask(deletedTaskIndex);
                    showTaskDeleted(tasks.size());
                    storage.saveTasks(tasks);
                    break;
                default:
                    showError("Invalid command format.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            showError("Invalid command format.");
        }
        return false;
    }
    public void showWelcome() {
        System.out.println("Hi, I'm BiuBiu.\nWhat can I do for you?");
    }
    public void showError(String errorCommand) {
        System.out.println(errorCommand);
    }
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public void showTaskDeleted(int taskCount) {
        System.out.println("OK, I've removed this task.");
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }
    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println("Noted, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
    public void showExit() {
        System.out.println("Bye. Have a great day!");
    }
}
