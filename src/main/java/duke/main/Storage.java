package duke.main;

import duke.main.TaskManager;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import duke.exceptions.StorageException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.format.DateTimeFormatter;
/**
 * The Storage class is responsible for reading and writing data to and from the storage file.
 * It provides methods to save tasks to the storage file and load tasks from the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves task data from the TaskManager to the storage file.
     *
     * @param taskManager The TaskManager containing the list of tasks.
     * @throws StorageException If there is an error while saving data.
     */
    public void saveData(TaskManager taskManager) throws StorageException {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task: taskManager.getList()) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new StorageException("Couldn't save duke.data");
        }
    }

    /**
     * Loads task data from the storage file and returns a TaskManager.
     *
     * @return A TaskManager containing the loaded tasks.
     * @throws StorageException If there is an error while loading data.
     */
    public TaskManager loadData() throws StorageException {
        File savedFile = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(savedFile);
            while (fileReader.hasNextLine()) {
                tasks.add(readLine(fileReader.nextLine()));
            }
            fileReader.close();
            TaskManager taskmanager = new TaskManager(tasks);
            return taskmanager;
        } catch (FileNotFoundException e) {
            createFile();
            return new TaskManager();
        }
    }

    private Task readLine(String line) throws StorageException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        line = line.trim();
        Pattern todoPattern = Pattern.compile("\\[T\\]\\[(.)\\] (.*)");
        Pattern deadlinePattern = Pattern.compile("\\[D\\]\\[(.)\\] (.*) \\(by: (.*)\\)");
        Pattern eventPattern = Pattern.compile("\\[E\\]\\[(.)\\] (.*) \\(from: (.*) to: (.*)\\)");
        Matcher matcher;

        matcher = todoPattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            ToDo todo = new ToDo(taskName, isDone);
            return todo;
        }

        matcher = deadlinePattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            String dueString = matcher.group(3).trim();
            LocalDateTime dueTime = LocalDateTime.parse(dueString, formatter);
            Deadline deadline = new Deadline(taskName, isDone, dueTime);
            return deadline;
        }

        matcher = eventPattern.matcher(line);
        if (matcher.matches()) {
            boolean isDone = matcher.group(1).equals("X");
            String taskName = matcher.group(2).trim();
            String fromString = matcher.group(3).trim();
            String toString = matcher.group(4).trim();
            LocalDateTime fromTime = LocalDateTime.parse(fromString, formatter);
            LocalDateTime toTime = LocalDateTime.parse(toString, formatter);
            return new Event(taskName, isDone, fromTime, toTime);
        }

        throw new StorageException("There was an issue reading your duke.data");
    }

    /**
     * Creates the storage file and its parent directory.
     *
     * @throws StorageException If there is an error while creating the file.
     */
    public void createFile() throws StorageException {
        File file = new File(this.filePath);
        File rootDirectory = new File(file.getParent());
        try {
            rootDirectory.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Unable to create a database");
        }
    }

}
