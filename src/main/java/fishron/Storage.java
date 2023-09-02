package fishron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles loading and saving tasks to/from a file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the file and returns a TaskList containing them.
     *
     * @return A TaskList containing tasks loaded from the file.
     */
    public TaskList loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File("./data/fishron.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length >= 2) {
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    if (taskType.equals("T")) {
                        Task task = Parser.parseTodo(description.trim());
                        if (isDone) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                    } else if (taskType.equals("D")) {
                        String by = parts[3];
                        Task task = Parser.parseDeadline(description.trim(), by.trim());
                        if (isDone) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                    } else if (taskType.equals("E")) {
                        String from = parts[3];
                        String to = parts[4];
                        Task task = Parser.parseEvent(description.trim(), from.trim(), to.trim());
                        if (isDone) {
                            task.markAsDone();
                        }
                        taskList.add(task);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        } catch (FishronException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
        }
        return new TaskList(taskList);
    }

    /**
     * Saves the provided task list to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            PrintWriter printWriter = new PrintWriter("./data/fishron.txt");
            for (Task task : taskList) {
                printWriter.println(task.toFileString());
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}
