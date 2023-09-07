package duke.task;

import duke.TaskType;
import duke.command.DukeException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    private String filePath = "./src/main/java/duke.txt";

    public TaskList() {
        this.tasks = new ArrayList();
    }

    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    public void processCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());
            PrintStream var10000;
            ArrayList var10001;
            int var10002;
            switch (taskType) {
                case TODO:
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }

                    String[] todoParts = parts[1].split(" /from ");
                    if (todoParts.length != 2) {
                        throw new DukeException("OOPS!!! Todo tasks must include '/from' for date.");
                    }

                    String[] dateRange = todoParts[1].split(" /to ");
                    if (dateRange.length != 2) {
                        throw new DukeException("OOPS!!! Todo tasks must include '/to' for the end date.");
                    }

                    LocalDate fromDate = LocalDate.parse(dateRange[0]);
                    LocalDate toDate = LocalDate.parse(dateRange[1]);
                    this.addTask(new TodoTask(todoParts[0], fromDate, toDate, false));
                    System.out.println(" Got it. I've added this task:");
                    var10000 = System.out;
                    var10001 = this.tasks;
                    var10002 = this.tasks.size();
                    var10000.println("   " + var10001.get(var10002 - 1));
                    System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    if (parts.length >= 2 && !parts[1].isEmpty()) {
                        String[] deadlineParts = parts[1].split(" /by ");
                        if (deadlineParts.length != 2) {
                            throw new DukeException("OOPS!!! Deadline tasks must include '/by' for date.");
                        }

                        LocalDate byDate = LocalDate.parse(deadlineParts[1]);
                        this.addTask(new DeadlineTask(deadlineParts[0], byDate, false));
                        System.out.println(" Got it. I've added this task:");
                        var10000 = System.out;
                        var10001 = this.tasks;
                        var10002 = this.tasks.size();
                        var10000.println("   " + var10001.get(var10002 - 1));
                        System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
                        break;
                    }

                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                case EVENT:
                    if (parts.length < 2 || parts[1].isEmpty()) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }

                    String[] eventParts = parts[1].split(" /at ");
                    if (eventParts.length != 2) {
                        throw new DukeException("OOPS!!! Event tasks must include '/at' for date.");
                    }

                    LocalDate atDate = LocalDate.parse(eventParts[1]);
                    this.addTask(new EventTask(eventParts[0], atDate, false));
                    System.out.println(" Got it. I've added this task:");
                    var10000 = System.out;
                    var10001 = this.tasks;
                    var10002 = this.tasks.size();
                    var10000.println("   " + var10001.get(var10002 - 1));
                    System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
            }

        } catch (IllegalArgumentException var12) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(String command) throws DukeException {
        int taskIndex = this.extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task deletedTask = (Task)this.tasks.remove(taskIndex);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + deletedTask);
            System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsDone(String command) throws DukeException {
        int taskIndex = this.extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            ((Task)this.tasks.get(taskIndex)).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            PrintStream var10000 = System.out;
            Object var10001 = this.tasks.get(taskIndex);
            var10000.println("   " + var10001);
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsNotDone(String command) throws DukeException {
        int taskIndex = this.extractTaskIndex(command);
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            ((Task)this.tasks.get(taskIndex)).markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            PrintStream var10000 = System.out;
            Object var10001 = this.tasks.get(taskIndex);
            var10000.println("   " + var10001);
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    private int extractTaskIndex(String command) throws DukeException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        } else {
            return Integer.parseInt(parts[1].trim()) - 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.tasks.isEmpty()) {
            for(int i = 0; i < this.tasks.size(); ++i) {
                sb.append(" ").append(i + 1).append(".").append(this.tasks.get(i));
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            Iterator var2 = this.tasks.iterator();

            while(var2.hasNext()) {
                Task task = (Task)var2.next();
                writer.write(task.toFileString() + "\n");
            }

            writer.close();
        } catch (IOException var4) {
            System.out.println("Error saving tasks to file: " + var4.getMessage());
        }

    }

    public void loadTasksFromFile() {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while((line = reader.readLine()) != null) {
                this.processFileLine(line);
            }

            reader.close();
        } catch (DukeException | IOException var4) {
            System.out.println("Error loading tasks from file: " + var4.getMessage());
        }

    }

    private void processFileLine(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length != 3) {
            throw new DukeException("Invalid task format in the file.");
        } else {
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate byDate;
            switch (taskType) {
                case "T":
                    String[] todoParts = description.split(" \\(from: | to: ", 3);
                    if (todoParts.length != 3) {
                        throw new DukeException("Invalid todo task format in the file.");
                    }

                    LocalDate fromDate = LocalDate.parse(todoParts[1]);
                    byDate = LocalDate.parse(todoParts[2].substring(0, todoParts[2].length() - 1));
                    this.addTask(new TodoTask(todoParts[0], fromDate, byDate, isDone));
                    break;
                case "D":
                    String[] deadlineParts = description.split(" \\(by: ", 2);
                    if (deadlineParts.length != 2) {
                        throw new DukeException("Invalid deadline task format in the file.");
                    }

                    byDate = LocalDate.parse(deadlineParts[1].substring(0, deadlineParts[1].length() - 1));
                    this.addTask(new DeadlineTask(deadlineParts[0], byDate, isDone));
                    break;
                case "E":
                    String[] eventParts = description.split(" \\(at: ", 2);
                    if (eventParts.length != 2) {
                        throw new DukeException("Invalid event task format in the file.");
                    }

                    LocalDate atDate = LocalDate.parse(eventParts[1].substring(0, eventParts[1].length() - 1));
                    this.addTask(new EventTask(eventParts[0], atDate, isDone));
                    break;
                default:
                    throw new DukeException("Unknown task type in file.");
            }

        }
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTotalTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return index >= 0 && index < this.tasks.size() ? (Task)this.tasks.get(index) : null;
    }

    public void markAsDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            ((Task)this.tasks.get(taskIndex)).markAsDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsNotDone(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            ((Task)this.tasks.get(taskIndex)).markAsNotDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task deletedTask = (Task)this.tasks.remove(taskIndex);
            return deletedTask;
        } else {
            throw new DukeException("Invalid task index.");
        }
    }
}