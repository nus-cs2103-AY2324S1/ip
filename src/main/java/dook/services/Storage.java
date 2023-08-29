package dook.services;

import dook.DookException;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Responsible for loading saved task list from/writing updated task list to plaintext file.
 */
public class Storage {
    private File file;

    private Path path;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    public Storage(Path filePath) {
        this.path = filePath;
    }

    /**
     * Loads task list from file, creating a new text file in the same directory if
     * not found.
     *
     * @return A list of saved tasks.
     * @throws DookException Exception thrown by Dook.
     */
    public ArrayList<Task> load() throws DookException {
        ArrayList<Task> result = new ArrayList<>();
        verifyFileExists();
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            while (reader.ready()) {
                String line = reader.readLine();
                result.add(getTaskFromString(line));
            }
        } catch (IOException e) {
            throw new DookException("File can't be read. This session can't be saved.");
        }
        return result;
    }

    private void verifyFileExists() {
        if (Files.exists(path)) return;
         try {
             Files.createFile(path);
         } catch (IOException e) {
             return;
         }
    }

    /**
     * Saves task list to file.
     *
     * @return The confirmation message to be displayed to the user.
     * @throws DookException Exception thrown by Dook.
     */
    public String save(TaskList taskList) throws DookException{
        String toSave = taskList.getSaveableString();
        try {
            writeToFile(toSave);
        } catch (IOException e) {
            throw new DookException("File cannot be saved.");
        }
        return "Task list saved!";

    }

    private void writeToFile(String textToAdd) throws IOException {
        byte[] strToBytes = textToAdd.getBytes();
        Files.write(this.path, strToBytes);
    }

    /**
     * Parses plaintext and converts it into a Task.
     *
     * @param str Raw string representing a task.
     * @return The converted Task.
     */
    private Task getTaskFromString(String str) throws DookException{
        String[] params = str.split("//");
        String taskCode = params[0];
        boolean isDone = params[1].equals("X");
        switch (taskCode) {
            case "T":
                return new Todo(params[2].trim(), isDone);
            case "D":
                return new Deadline(params[2].trim(), params[3].trim(), isDone);
            case "E":
                return new Event(params[2].trim(), params[3].trim(), params[4].trim(), isDone);
            default:
                throw new DookException("Failed to read from file correctly.");
        }
    }

}
