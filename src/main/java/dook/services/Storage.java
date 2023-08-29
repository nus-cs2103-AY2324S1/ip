package dook.services;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import dook.DookException;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;




/**
 * Responsible for loading saved task list from/writing updated task list to plaintext file.
 * FORMAT: [Type]/[isDone]/[description]/[time]
 *
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
     * Loads task list from file.
     *
     * @return A list of saved tasks.
     * @throws FileNotFoundException, DookException
     */
    public ArrayList<Task> load() throws FileNotFoundException, DookException {
        ArrayList<Task> result = new ArrayList<>();
        verifyFileExists();
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            while (reader.ready()) {
                String line = reader.readLine();
                result.add(getTaskFromString(line));
            }
        } catch (IOException e) {
            throw new DookException("File can't be read.");
        }
        return result;
    }

    private void verifyFileExists() {
        if (Files.exists(path)) {
            return;
        }
        try {
            Files.createFile(path);
        } catch (IOException e) {
            return;
        }
    }


    public String save(TaskList taskList) throws DookException {
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
     * Parses plaintext and converts it into a dook.task.Task.
     *
     * @param str
     * @return The converted Task.
     */
    private Task getTaskFromString(String str) throws DookException {
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
