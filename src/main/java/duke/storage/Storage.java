package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * The Storage to read and write from the data file.
 */
public class Storage {

    /**
     * A Temporary storage for reading the Tasks from the file.
     */
    private ArrayList<Task> store = new ArrayList<Task>();

    /**
     * The file path to read and write data from.
     */
    private String filePath;

    /**
     * The formatter to parse the time of the tasks.
     */
    private DateTimeFormatter storeTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * The formatter to format the time of the tasks to store.
     */
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for the Storage.
     *
     * @param filePath The file path of the data file to write and read from.
     */
    public Storage(String filePath) {
        assert filePath.length() != 0 : "File Path should be specified!";
        this.filePath = filePath;
    }

    /**
     * Saves the data to the data file.
     *
     * @param data The data to be saved.
     */
    public void saveData(String data) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save data to storage!");
        }
    }

    /**
     * Processes the task components to add the new Todo Task.
     *
     * @param taskComponents The String Array that contains the task components.
     * @throws DukeException If the array is not of the correct length.
     */
    private void processTodoData(String[] taskComponents) throws DukeException {
        String isCompleted = taskComponents[1];
        String desc = taskComponents[2];
        Task item = new ToDo(desc);
        if (isCompleted.equals("X")) {
            item.markTask();
        }
        store.add(item);
    }

    /**
     * Processes the task components to add the new Deadline Task.
     *
     * @param taskComponents The String Array that contains the task components.
     * @throws DukeException If the array is not of the correct length.
     */
    private void processDeadlineData(String[] taskComponents) throws DukeException {
        String isCompleted = taskComponents[1];
        String desc = taskComponents[2];
        if (taskComponents.length < 3) {
            throw new DukeException("Invalid Deadline Format!");
        }
        LocalDateTime buffer = LocalDateTime.parse(taskComponents[3], storeTimeFormatter);
        Task item = new Deadline(desc, buffer.format(inputFormatter));
        if (isCompleted.equals("X")) {
            item.markTask();
        }
        store.add(item);
    }

    /**
     * Processes the task components to add the new Event Task.
     *
     * @param taskComponents The String Array that contains the task components.
     * @throws DukeException If the array is not of the correct length.
     */
    private void processEventData(String[] taskComponents) throws DukeException {
        String isCompleted = taskComponents[1];
        String desc = taskComponents[2];
        if (taskComponents.length < 3) {
            throw new DukeException("Invalid Event Format!");
        }
        String[] timeComponents = taskComponents[3].split("-", 2);
        if (timeComponents.length != 2) {
            throw new DukeException("Invalid Event Format!");
        }
        LocalDateTime bufferStart = LocalDateTime.parse(timeComponents[0], storeTimeFormatter);
        LocalDateTime bufferEnd = LocalDateTime.parse(timeComponents[1], storeTimeFormatter);
        Task item = new Event(desc, bufferStart.format(inputFormatter), bufferEnd.format(inputFormatter));
        if (isCompleted.equals("X")) {
            item.markTask();
        }
        store.add(item);
    }

    /**
     * Loads the task data from the data file.
     *
     * @return The Task List from the data file.
     * @throws DukeException if data file is corrupted or not found.
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                // Process file line input
                String newLine = s.nextLine();
                String[] taskComponents = newLine.split(" \\| ");
                String type = taskComponents[0];
                if (type.equals("T")) {
                    processTodoData(taskComponents);
                } else if (type.equals("D")) {
                    processDeadlineData(taskComponents);
                } else if (type.equals("E")) {
                    processEventData(taskComponents);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not Found!");
        }

        return store;
    }
}
