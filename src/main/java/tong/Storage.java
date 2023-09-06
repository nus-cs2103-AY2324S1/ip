package tong;

import tong.exception.DecodingException;
import tong.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    public static final String FILE = "./data";
    public static final String FILEPATH = "./data/tong.txt";

    public Storage() {
        initializeStorage();
    }

    private void initializeStorage() {
        File directory = new File(FILE);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Saves the tong.TaskList to the storage file.
     */
    public void save(TaskList tasklist) {
        List<String> encodedTaskList = encode(tasklist);
        try {
            Files.write(Paths.get(FILEPATH), encodedTaskList);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads the Tasks from the storage file, and returns a tong.TaskList with previously saved Tasks.
     */
    public TaskList load() {
        try {
            return decode(Files.readAllLines(Paths.get(FILEPATH)));
        } catch (DecodingException e) {
            System.err.println("Sorry, I am unable to retrieve your saved tasklist. " +
                    "Start with a new tasklist from today!");
            return new TaskList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Encodes each tong.task.Task in the tong.TaskList into a String representation.
     */
    private static List<String> encode(TaskList tasklist) {
        List<String> encodedTaskList = new ArrayList<>();

        for (Task task : tasklist.getTaskList()) {
            String encodedTask = task.getEncodedString();
            encodedTaskList.add(encodedTask);
        }

        return encodedTaskList;
    }

    private static TaskList decode(List<String> encodedTaskList) throws DecodingException {
        ArrayList<Task> decodedTasks = new ArrayList<>();

        for (String encodedString : encodedTaskList) {
            if (encodedString.isBlank()) {
                continue;
            }

            char type = encodedString.charAt(0);
            switch (type) {
            case 'T':
                decodedTasks.add(Task.getDecodedToDo(encodedString));
                break;
            case 'D':
                decodedTasks.add(Task.getDecodedDeadline(encodedString));
                break;
            case 'E':
                decodedTasks.add(Task.getDecodedEvent(encodedString));
                break;
            default:
                throw new DecodingException("Problem encountered during decoding the data file.");
            }
        }

        return new TaskList(decodedTasks);
    }
}