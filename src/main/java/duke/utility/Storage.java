package duke.utility;

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

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
        this.pathString = "C:\\Users\\Admin\\ip\\text-ui-test\\data\\task.txt";
    }

    /**
     * Saves a new task into the task list in the text file.
     *
     * @param newTask New task to save into the text file.
     */
    public void saveTask(Task newTask) {
        try {
            Path path = Paths.get("C:\\Users\\Admin\\ip\\text-ui-test", "data", "task.txt");
            String taskString = newTask.toStoreString() + "\n";

            // write task into text file in string format
            Files.writeString(path, taskString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the status of a task saved in the text file.
     *
     * @param task Task saved in the text file.
     * @param i Status to update the task to. 0 for false, 1 for true.
     */
    public void updateTask(Task task, int i) {
        String oldTask = task.toStoreString();
        String newTask = task.toUpdateString(i);

        File fileToUpdate = new File(this.pathString);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToUpdate));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                oldContent += line + System.lineSeparator();
                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldTask, newTask);

            //Rewriting the input text file with newContent
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

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                // do not copy contents if it is equals to the task to delete
                if (!line.equals(oldTask)) {
                    newContent += line + System.lineSeparator();
                }
                line = reader.readLine();
            }

            //Rewriting the input text file with newContent
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
