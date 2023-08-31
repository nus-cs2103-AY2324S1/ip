package chatbot;

import chatbot.exceptions.FileCorruptedException;
import chatbot.exceptions.FilePermissionException;
import chatbot.exceptions.InvalidTaskStringException;
import chatbot.exceptions.LocalFileException;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import chatbot.tasks.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String localDirectoryPath;
    private final String localFilePath;

    /**
     * Constructor that instantiates a Storage object with the paths.
     * @param directoryPath String path to the directory containing the data file
     * @param filePath String path to the data file
     */
    public Storage(String directoryPath, String filePath) {
        this.localDirectoryPath = directoryPath;
        this.localFilePath = filePath;
    }

    private static Task parseTaskString(String taskString) throws InvalidTaskStringException {
        if (taskString.isEmpty()) {
            throw new InvalidTaskStringException();
        }
        String firstWord = taskString.split(" ")[0];
        try {
            switch (firstWord.charAt(1)) {
            case 'T':
                return parseTodoTaskString(taskString);
            case 'D':
                return parseDeadlineTaskString(taskString);
            case 'E':
                return parseEventTaskString(taskString);
            default:
                throw new InvalidTaskStringException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static boolean parseTaskIsDone(String taskString) throws InvalidTaskStringException {
        try {
            switch (taskString.charAt(4)) {
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

    private static Task parseTodoTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            String taskName = taskString.substring(7);
            return new ToDoTask(taskName, isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseDeadlineTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            int idOfBy = taskString.indexOf("(by:");
            if (idOfBy == -1) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(7, idOfBy - 1);
            String deadlineWholeString = taskString.substring(idOfBy);
            String deadline = deadlineWholeString.substring(5, deadlineWholeString.length() - 1);
            return new DeadlineTask(taskName, isDone, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseEventTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            int idOfFrom = taskString.indexOf("(from:");
            int idOfTo = taskString.indexOf("to:");
            if (idOfFrom == -1 || idOfTo == -1) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(7, idOfFrom - 1);
            String fromWholeString = taskString.substring(idOfFrom, idOfTo);
            String toWholeString = taskString.substring(idOfTo);
            String from = fromWholeString.substring(7, fromWholeString.length() - 1);
            String to  = toWholeString.substring(4, toWholeString.length() - 1);
            return new EventTask(taskName, isDone, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * Load data from the data file.
     * @return ArrayList of Tasks represented by the Strings in the data file
     * @throws LocalFileException when the file contains invalid data, or cannot be accessed due to permission issue
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
     * Rewrite the data file with the data provided.
     * @param data the String data to store in the data file
     * @throws LocalFileException when the file cannot be written due to permission issue
     */
    public void writeToDataFile(String data) throws LocalFileException {
        try {
            FileWriter fw = new FileWriter(localFilePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new FilePermissionException(localFilePath);
        }
    }
}
