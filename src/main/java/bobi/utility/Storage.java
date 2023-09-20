package bobi.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

import bobi.task.Deadline;
import bobi.task.Event;
import bobi.task.Task;
import bobi.task.ToDo;
import javafx.application.Platform;

/**
 * Storage class encapsulates all actions that can be done to the tasks saved in the backend.
 *
 * @author ruo-x
 */
public class Storage {
    /** Path to the text file the data is saved in */
    private final String pathString;

    /**
     * Constructor for a Storage object.
     */
    public Storage() {
        this.pathString = "C:\\Users\\Admin\\Bobi\\data\\task.txt";
    }

    /**
     * Saves a new task into the task list stored in the text file.
     *
     * @param newTask New task to save into the text file.
     */
    public void saveTask(Task newTask) {
        try {
            Path path = Paths.get("C:\\Users\\Admin\\Bobi", "data", "task.txt");
            String taskString = newTask.toStoreString() + "\n";

            // the text file should exist at the correct path if execution reaches here
            assert Files.exists(path);

            // write new task into text file in the specified string format
            Files.writeString(path, taskString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a task saved in the text file.
     *
     * @param task Task saved in the text file.
     * @param newStatus Status to update the task to.
     *     0 for false, 1 for true.
     */
    public void updateTask(Task task, int newStatus) {
        String oldTask = task.toStoreString();
        String newTask = task.toUpdateString(newStatus);

        File fileToUpdate = new File(this.pathString);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToUpdate));

            // reading all the lines of the original text file into oldContent
            String line = reader.readLine();
            while (line != null) {
                oldContent += line + System.lineSeparator();
                line = reader.readLine();
            }

            // replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldTask, newTask);

            // rewriting the input text file with newContent
            writer = new FileWriter(fileToUpdate);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // closing the resources
                assert reader != null;
                reader.close();
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes a task from the text file.
     *
     * @param task Task to delete.
     */
    public void deleteTask(Task task) {
        String oldTask = task.toStoreString();

        File fileToUpdate = new File(this.pathString);
        String newContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToUpdate));

            String line = reader.readLine();
            while (line != null) {
                // do not copy over line if it is equals to the task to delete
                if (line.equals(oldTask)) {
                    line = reader.readLine();
                    continue;
                }
                newContent += line + System.lineSeparator();
                line = reader.readLine();
            }

            // rewriting the input text file with newContent
            writer = new FileWriter(fileToUpdate);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                assert reader != null;
                reader.close();
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads all contents from the text file into the active task list.
     *
     * @param list Active task list to load into.
     */
    public void handleLoad(TaskList list) {
        Scanner sc = null;
        try {
            File file = new File(this.pathString);

            file.getParentFile().mkdirs();

            // creates new file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // the text file should exist at the correct path if execution reaches here
            assert Files.exists(Path.of(this.pathString));

            sc = new Scanner(file);

            while (sc.hasNext()) {
                // reads text file line by line
                String[] keyword = sc.nextLine().split("/@/");
                switch (keyword[0]) {
                case "T":
                    // line is a To-Do Task
                    boolean tStatus = keyword[1].equals("1");
                    list.addTask(new ToDo(tStatus, keyword[2]));
                    break;
                case "D":
                    // line is a Deadline Task
                    boolean dStatus = keyword[1].equals("1");
                    list.addTask(new Deadline(
                            dStatus,
                            keyword[2],
                            LocalDateTime.parse(keyword[3])
                    ));
                    break;
                case "E":
                    // line is an Event Task
                    boolean eStatus = keyword[1].equals("1");
                    list.addTask(new Event(
                            eStatus,
                            keyword[2],
                            LocalDateTime.parse(keyword[3]),
                            LocalDateTime.parse(keyword[4])
                    ));
                    break;
                default:
                    System.out.println("error");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert sc != null;
            sc.close();
        }
    }
}
