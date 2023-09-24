package rua.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import rua.exception.InvalidTypeException;
import rua.task.Deadline;
import rua.task.Event;
import rua.task.Task;
import rua.task.TaskList;
import rua.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object which reads and writes to a file located at the filePath.
     *
     * @param filePath The path of the file to be loaded and saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a Task corresponding to a given String.
     *
     * @param str A String that represents a Task object.
     * @return The corresponding Task object.
     * @throws InvalidTypeException if the task type is not supported.
     */
    static Task stringToTask(String str) throws InvalidTypeException {
        String[] features = Arrays.stream(str.split(" \\|",
                Integer.MAX_VALUE)).map(String::trim).toArray(String[]::new);
        Task output;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");
        final boolean isMarked = features[1].equals("1");
        final String taskDescription = features[2];
        final String tagsString = features[3];
        final ArrayList<String> tags = tagsStringToArrayList(tagsString);
        switch (features[0]) {
        case "T":
            output = new Todo(taskDescription, isMarked, tags);
            break;
        case "D":
            final LocalDateTime dueDate = LocalDateTime.parse(features[4], dateFormat);
            output = new Deadline(taskDescription, dueDate, isMarked, tags);
            break;
        case "E":
            final LocalDateTime startingDate = LocalDateTime.parse(features[4], dateFormat);
            final LocalDateTime endingDate = LocalDateTime.parse(features[5], dateFormat);
            output = new Event(taskDescription, startingDate,
                    endingDate, isMarked, tags);
            break;
        default:
            throw new InvalidTypeException(features[0]);
        }
        return output;
    }

    /**
     * Returns a Task corresponding to a given String.
     *
     * @param task A Task object that needs to be translated into a String.
     * @return The corresponding String.
     * @throws InvalidTypeException if the task type is not supported.
     */
    static String taskToString(Task task) throws InvalidTypeException {
        String tagsString = task.getTags().toString().replace("[", "").replace("]", "");
        String output = task.getType() + " | " + (task.isMarked() ? 1 : 0)
                + " | " + task.getDescription()
                + " | " + tagsString;
        switch (task.getType()) {
        case "T":
            break;
        case "D":
            output += " | " + ((Deadline) task).getDue();
            break;
        case "E":
            output += " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
            break;
        default:
            throw new InvalidTypeException(task.getType());
        }
        return output;
    }

    /**
     * Returns the ArrayList of tasks represented by the input String located in the filePath.
     *
     * @return The corresponding ArrayList of Task objects.
     * @throws InvalidTypeException if the task type is not supported.
     * @throws FileNotFoundException if the filePath is invalid.
     */
    public ArrayList<Task> load() throws InvalidTypeException, FileNotFoundException {
        String fileName = filePath + "tasks.txt";
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(stringToTask(sc.nextLine()));
        }
        return tasks;
    }

    /**
     * Saves the string representing a list of tasks at the filePath.
     *
     * @param tasks A TaskList object that needs to be translated into String and saved.
     * @throws InvalidTypeException if the task type is not supported.
     * @throws IOException if the filePath is invalid.
     */
    public void save(TaskList tasks) throws InvalidTypeException, IOException {
        try {
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String fileName = filePath + "tasks.txt";
            FileWriter fw = new FileWriter(fileName);
            String saveTxt = "";
            ArrayList<Task> tasksList = tasks.getTasks();
            for (int i = 0; i < tasksList.size(); i++) {
                saveTxt += taskToString(tasksList.get(i)) + "\n";
            }
            fw.write(saveTxt);
            fw.close();
        } catch (FileNotFoundException exp) {
            StringLogger.append("Target file not found and cannot be created."
                    + " Please create the directory manually\n");
        }
    }

    private static ArrayList<String> tagsStringToArrayList(String tagsString) {
        if (tagsString.isEmpty()) {
            return new ArrayList<>();
        }
        String[] tagsArray = tagsString.split(", ");
        ArrayList<String> tags = new ArrayList<>();
        Collections.addAll(tags, tagsArray);
        return tags;
    }
}
