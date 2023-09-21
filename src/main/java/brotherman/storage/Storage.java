package brotherman.storage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import brotherman.exceptions.BrothermanException;
import brotherman.parser.Parser;
import brotherman.tasks.Task;
import brotherman.tasks.TaskList;



/**
 * Represents a storage to save and load the task list
 */
public class Storage {

    /**
     * File path of the storage
     */
    private File folder;
    private String filePath;

    /**
     * Constructor for Storage
     * @param filePath File path of the storage
     */
    public Storage(String filePath) {
        this.folder = new File(filePath);
        this.filePath = filePath;

        File parentDirectory = folder.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }
    /**
     * Reads the task list from the storage
     * @return Task list from the storage
     */
    public TaskList readFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                String[] task = line.split("\\|");
                String type = task[0];

                switch (type) {
                case "T":
                    handleTodo(task, taskList);
                    break;
                case "D":
                    handleDeadline(task, taskList);
                    break;
                case "E":
                    handleEvent(task, taskList);
                    break;
                default:
                    throw new BrothermanException("☹ OOPS!!! Brotherman file read error! :-(");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (BrothermanException e) {
            System.out.println(e.getMessage());
        }

        return new TaskList(taskList);
    }

    /**
     * Handles the todo task
     * @param description Description of the todo task
     * @param taskList Task list to be added to
     */
    private void handleTodo(String[] description, ArrayList<Task> taskList) {
        Task task = Parser.parseTodo(description[2].trim());
        task.markedChecker(description[1]);
        taskList.add(task);
    }

    /**
     * Handles the deadline task
     * @param description Description of the deadline task
     * @param taskList Task list to be added to
     */
    private void handleDeadline(String[] description, ArrayList<Task> taskList) {
        String[] parts = description[2].split("/by");
        Task task = Parser.parseDeadline(parts[0].trim(), parts[1].trim());
        task.markedChecker(description[1]);
        taskList.add(task);
    }

    /**
     * Handles the event task
     * @param description Description of the event task
     * @param taskList Task list to be added to
     */
    private void handleEvent(String[] description, ArrayList<Task> taskList) {
        String[] parts = description[2].trim().split("\\s*/from\\s*|\\s*/to\\s*");
        Task task = Parser.parseEvent(parts[0], parts[1], parts[2]);
        task.markedChecker(description[1]);
        taskList.add(task);
    }

    /**
     * Saves the task list to the storage
     * @param list Task list to be saved
     */
    public void saveToFile(ArrayList<Task> list) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task listItems : list) {
                fw.write(listItems.storeText() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
