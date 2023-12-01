package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Storage deals with loading tasks from the data file and saving tasks into it.
 */
public class Storage {

    private String filePath;

    /**
     * The constructor of Storage.
     *
     * @param filePath The specific file path to load and save tasks into.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes and save tasks from the task list into the file.
     *
     * @param taskList The task list to get the tasks from.
     */
    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.getTaskList()) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occured while writing the tasks into file: " + e);
            e.printStackTrace();
        }
    }

    /**
     * The method to read tasks from the file and add the subsequent tasks read into the
     * task list in order.
     *
     * @param file The file which the tasks are read from.
     * @return The ArrayList of Tasks which the tasks read are added into.
     */
    public ArrayList<Task> readTasks(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                char taskType = input.charAt(1);
                boolean isDone = input.charAt(4) == 'X';
                String description = input.split("] ")[1];
                switch (taskType) {
                case 'T':
                    Todo t = new Todo(description, isDone);
                    tasks.add(t);
                    break;
                case 'D':
                    int byIndex = description.indexOf("(by: ");
                    int lastByIndex = description.indexOf(")");
                    String deadlineDescription = description.substring(0, byIndex - 1);
                    String by = description.substring(byIndex + 5, lastByIndex);
                    Deadline d = new Deadline(deadlineDescription, by, isDone);
                    tasks.add(d);
                    break;
                case 'E':
                    int fromIndex = description.indexOf("(from: ");
                    int toIndex = description.indexOf("to: ");
                    int lastToIndex = description.indexOf(")");
                    String eventDescription = description.substring(0, fromIndex - 1);
                    String start = description.substring(fromIndex + 7, toIndex - 1);
                    String end = description.substring(toIndex + 4, lastToIndex);
                    Event e = new Event(eventDescription, start, end, isDone);
                    tasks.add(e);
                    break;
                default:
                    assert false : taskType;
                    System.out.println("An error has occurred: Unknown task type");
                }
            }
            sc.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println(exception);
        } finally {
            return tasks;
        }
    }

    /**
     * The method to load the initial task list from the data file.
     * If the data file does not exist, a new one will be created and an empty ArrayList
     * will be returned.
     *
     * @return The ArrayList of Tasks with the initial tasks added in where applicable.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String[] filePathArray = filePath.split("/");
            String fileDirectory = filePathArray[0] + "/" + filePathArray[1];
            File directory = new File(fileDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
            assert directory.exists() : "The file directory should already exist";
            File taskFile = new File(filePath);
            if (taskFile.createNewFile()) {
                // creates new file
            } else {
                assert taskFile.exists() : "The file should already exist";
                tasks = readTasks(taskFile);
            }
        } catch (IOException e) {
            System.out.println("An error occured when initialising tasks: " + e);
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }
}
