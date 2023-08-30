import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private final String LINE_SEPARATOR = "____________________________________________________________";

    public TaskList() {
    }

    public void addTask(String taskTypeAndDetails) throws DukeException {
        Task newTask;
        String taskType = extractTaskType(taskTypeAndDetails);
        String taskDetails = extractTaskDetails(taskTypeAndDetails);
        String taskName;

        if (taskDetails.equals("")) {
            throw new DukeException(messageWithSeparator("☹ OOPS!!! The description of a " + taskType + " cannot be empty."));
        } else if (taskType.equals("todo")) {
            taskName = taskDetails;
            newTask = new ToDoTask(taskDetails);
        } else if (taskType.equals("deadline") && taskDetails.contains("/by")) {
            String[] taskDetailsArray = taskDetails.split("/by");
            taskName = taskDetailsArray[0].trim();
            String taskDeadline = taskDetailsArray[1].trim();
            newTask = new DeadlineTask(taskName, taskDeadline);
        } else if (taskType.equals("event") && taskDetails.contains("/from") && taskDetails.contains("/to")) {
            taskName = taskDetails.split("/from")[0].trim();
            String from = taskDetails.split("/from")[1].split("/to")[0].trim();
            String to = taskDetails.split("/to")[1].trim();
            newTask = new EventTask(taskName, from, to);
        } else {
            printWithSeparator("Please enter a valid task.");
            return;
        }

        tasks.add(newTask);
        printWithSeparator("Got it. I've added this task:\n" + newTask.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            printWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            printWithSeparator("Noted. I've removed this task:\n" + task.getDescription()
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            printWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.markAsDone();
            printWithSeparator("Nice! I've marked this task as done:\n" + task.getDescription());
        }
    }

    public void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            printWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.unmark();
            printWithSeparator("OK, I've marked this task as not done yet:\n" + task.getDescription());
        }
    }

    public void list() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task.getDescription());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public void saveTasksToFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        for (Task task : tasks) {
            fileWriter.write(task.toFileString() + "\n");
        }

        fileWriter.close();
    }

    public void loadTasksFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("X");
            String taskDescription = parts[2];

            Task task;

            if (taskType.equals("T")) {
                task = new ToDoTask(taskDescription);
            } else if (taskType.equals("D")) {
                String deadline = parts[3];
                task = new DeadlineTask(taskDescription, deadline);
            } else if (taskType.equals("E")) {
                String from = parts[3];
                String to = parts[4];
                task = new EventTask(taskDescription, from, to);
            } else {
                throw new IOException("Invalid task type found in file.");
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        reader.close();
    }


    private void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }

    private String messageWithSeparator(String message) {
        return LINE_SEPARATOR+ "\n" + message + "\n" + LINE_SEPARATOR;
    }

    private String extractTaskType(String taskTypeAndDetails) {
        String[] parts = taskTypeAndDetails.split(" ", 2);
        return parts[0];
    }

    private String extractTaskDetails(String taskTypeAndDetails) {
        String[] parts = taskTypeAndDetails.split(" ", 2);
        if (parts.length < 2) {
            return "";
        }
        return parts[1];
    }
}

