package chatbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import chatbot.exceptions.FileCorruptedException;
import chatbot.exceptions.FilePermissionException;
import chatbot.exceptions.InvalidTaskStringException;
import chatbot.exceptions.LocalFileException;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import chatbot.tasks.ToDoTask;

/**
 * Class that interact with user's local storage.
 */
public class Storage {
    private static final int MIN_TASK_STRING_LENGTH = 11;
    private static final int LOCAL_STORAGE_TASK_TYPE_INDEX = 1;
    private static final int LOCAL_STORAGE_TASK_IS_DONE_INDEX = 4;
    private static final int LOCAL_STORAGE_TASK_PRIORITY_INDEX = 8;
    private static final int LOCAL_STORAGE_TASK_NAME_INDEX = 11;
    private String localDirectoryPath;
    private String localFilePath;

    /**
     * Instantiates a Storage object with the paths.
     *
     * @param directoryPath String path to the directory containing the data file.
     * @param filePath String path to the data file.
     */
    public Storage(String directoryPath, String filePath) {
        this.localDirectoryPath = directoryPath;
        this.localFilePath = filePath;
    }

    private static Task parseTaskString(String taskString) throws InvalidTaskStringException {
        if (taskString.length() < MIN_TASK_STRING_LENGTH) {
            throw new InvalidTaskStringException();
        }

        switch (taskString.charAt(LOCAL_STORAGE_TASK_TYPE_INDEX)) {
        case 'T':
            return parseTodoTaskString(taskString);
        case 'D':
            return parseDeadlineTaskString(taskString);
        case 'E':
            return parseEventTaskString(taskString);
        default:
            throw new InvalidTaskStringException();
        }
    }

    private static boolean parseTaskIsDone(String taskString) throws InvalidTaskStringException {
        try {
            switch (taskString.charAt(LOCAL_STORAGE_TASK_IS_DONE_INDEX)) {
            case 'X':
                return true;
            case ' ':
                return false;
            default:
                throw new InvalidTaskStringException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task.Priority parseTaskPriority(String taskString) throws InvalidTaskStringException {
        switch (taskString.charAt(LOCAL_STORAGE_TASK_PRIORITY_INDEX)) {
        case 'H':
            return Task.Priority.HIGH;
        case 'M':
            return Task.Priority.MEDIUM;
        case 'L':
            return Task.Priority.LOW;
        default:
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseTodoTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            Task.Priority priority = parseTaskPriority(taskString);
            String taskName = taskString.substring(LOCAL_STORAGE_TASK_NAME_INDEX);
            return new ToDoTask(taskName, isDone, priority);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseDeadlineTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            Task.Priority priority = parseTaskPriority(taskString);

            int idOfBy = taskString.indexOf("(by:");
            if (idOfBy == -1) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(LOCAL_STORAGE_TASK_NAME_INDEX, idOfBy);
            String deadlineWholeString = taskString.substring(idOfBy);
            String deadline = deadlineWholeString.substring(5, deadlineWholeString.length() - 1);

            return new DeadlineTask(taskName, isDone, priority, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseEventTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            Task.Priority priority = parseTaskPriority(taskString);

            int idOfFrom = taskString.indexOf("(from:");
            int idOfTo = taskString.indexOf("to:");
            if (idOfFrom == -1 || idOfTo == -1 || idOfFrom > idOfTo) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(LOCAL_STORAGE_TASK_NAME_INDEX, idOfFrom);
            String fromWholeString = taskString.substring(idOfFrom, idOfTo);
            String toWholeString = taskString.substring(idOfTo);
            String from = fromWholeString.substring(7, fromWholeString.length() - 1);
            String to = toWholeString.substring(4, toWholeString.length() - 1);
            return new EventTask(taskName, isDone, priority, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * Loads data from the data file.
     *
     * @return ArrayList of Tasks represented by the Strings in the data file.
     * @throws LocalFileException when the file contains invalid data, or cannot be accessed due to permission issue.
     */
    public ArrayList<Task> readData() throws LocalFileException {
        try {
            File d = new File(localDirectoryPath);
            if (!d.exists() || !d.isDirectory()) {
                Files.createDirectory(Paths.get(localDirectoryPath));
            }
            File f = new File(localFilePath);
            if (!f.exists() || !f.isFile()) {
                Files.createFile(Paths.get(localFilePath));
            }
            // Local File is present
            Scanner initialDataScanner = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();
            while (initialDataScanner.hasNext()) {
                taskList.add(parseTaskString(initialDataScanner.nextLine()));
            }
            return taskList;
        } catch (IOException e) {
            throw new FilePermissionException(localFilePath);
        } catch (InvalidTaskStringException e) {
            throw new FileCorruptedException(localFilePath);
        }
    }

    /**
     * Rewrites the data file with the data provided.
     *
     * @param data the String data to store in the data file.
     * @throws LocalFileException when the file cannot be written due to permission issue.
     */
    public void writeToDataFile(String data) throws LocalFileException {
        assert (data.length() >= MIN_TASK_STRING_LENGTH);
        try {
            FileWriter fw = new FileWriter(localFilePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new FilePermissionException(localFilePath);
        }
    }
}
