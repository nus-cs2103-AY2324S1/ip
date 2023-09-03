import java.io.*;
import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;
    private String filePath = "src/main/java/duke.txt";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void processCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());

            switch (taskType) {
                case TODO:
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(new TodoTask(parts[1], false));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    addTask(new DeadlineTask(parts[1], false));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    break;
                case EVENT:
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    addTask(new EventTask(parts[1], false));
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(String command) throws DukeException {
        int taskIndex = extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + deletedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsDone(String command) throws DukeException {
        int taskIndex = extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex));
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsNotDone(String command) throws DukeException {
        int taskIndex = extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex));
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    private int extractTaskIndex(String command) throws DukeException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Here are the tasks in your list:\n");
        if (!tasks.isEmpty()) {

            for (int i = 0; i < tasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(tasks.get(i));
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return; // If the file doesn't exist yet, no need to load tasks
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                processFileLine(line);
            }
            reader.close();
        } catch (IOException | DukeException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void processFileLine(String line) throws DukeException {
        // Parse the line and create tasks based on the format in the file
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                // Create and add a TodoTask
                addTask(new TodoTask(description, isDone));
                break;
            case "D":
                // Create and add a DeadlineTask
                addTask(new DeadlineTask(description, isDone));
                break;
            case "E":
                // Create and add an EventTask
                addTask(new EventTask(description, isDone));
                break;
            default:

        }
    }
}
