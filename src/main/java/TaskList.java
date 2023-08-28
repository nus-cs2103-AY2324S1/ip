import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
public class TaskList {
    private ArrayList<Task> tasks;
    private final String FILE_PATH = "./data/duke.txt";

    public TaskList() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        taskCountSummary();
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index - 1));

            saveTasksToFile();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index - 1));

            saveTasksToFile();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            taskCountSummary();

            saveTasksToFile();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    private void taskCountSummary() {
        int size = tasks.size();
        if (size == 1) {
            System.out.println("Now you have " + size + " task in the list.");
        } else {
            System.out.println("Now you have " + size + " tasks in the list.");
        }
    }

    private void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                directory.mkdirs(); // Create necessary directories
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return; // No tasks to load if the file doesn't exist
            }

            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                Task task = parseTaskFromString(line);
                if (task == null) {
                    throw new DukeException("☹ OOPS!!! Saved data not found due to corruption. \n Corrupted task data: " + line);
                }
                tasks.add(task);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error loading tasks from file: " + e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task parseTaskFromString(String line) throws DukeException {
        String[] parts = line.split("\\s*\\|\\s*"); // Split using the delimiter "|"
        String taskType = parts[0].trim();
        String status = parts[1].trim();

        if (!status.equals("0") && !status.equals("1")) {
            throw new DukeException("☹ OOPS!!! Saved data not found due to corruption. \n Corrupted task status: " + line);
        }
        boolean isDone = status.equals("1");

        String taskDescription = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;

        switch (taskType) {
            case "T":
                ToDo todo = new ToDo(taskDescription);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (additionalInfo == null) {
                    throw new DukeException("☹ OOPS!!! Saved data not found due to corruption. \n Missing date. Corrupted deadline: " + line);
                }
                Deadline deadline = new Deadline(taskDescription, additionalInfo);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (additionalInfo == null) {
                    throw new DukeException("☹ OOPS!!!  Saved data not found due to corruption. \n Missing details. Corrupted event: " + line);
                }
                String[] eventParts = additionalInfo.split(" to ");
                if (eventParts.length != 2) {
                    throw new DukeException("☹ OOPS!!!  Saved data not found due to corruption. \n Missing details. Corrupted event: " + line);
                }
                String from = eventParts[0];
                String to = eventParts[1];
                Event event = new Event(taskDescription, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }
}
