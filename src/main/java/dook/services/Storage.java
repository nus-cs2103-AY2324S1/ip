package dook.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import dook.DookException;
import dook.command.CommandInfo;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;

/**
 * Responsible for loading saved task list from/writing updated task list to plaintext file.
 */
public class Storage {
    private static final String FILEREAD_ERROR_MSG = "Failed to read from file!";
    private static final String FILESAVE_ERROR_MSG = "File cannot be saved.";
    private static final String FILESAVE_SUCCESS_MSG = "Task list saved!";

    private final Path taskListPath;
    private final Path aliasesListPath;
    private final HashMap<String, CommandInfo> aliases = new HashMap<>();
    public Storage(Path taskListPath, Path aliasesListPath) {
        this.taskListPath = taskListPath;
        this.aliasesListPath = aliasesListPath;
    }

    /**
     * Loads task list from file, creating a new text file in the same directory if
     * not found.
     *
     * @return A list of saved tasks.
     * @throws DookException Exception thrown by Dook.
     */
    public ArrayList<Task> loadTasksFromFile() throws DookException {
        ArrayList<Task> result = new ArrayList<>();
        verifyFileExists(taskListPath);
        try {
            BufferedReader reader = Files.newBufferedReader(taskListPath);
            while (reader.ready()) {
                String line = reader.readLine();
                result.add(getTaskFromString(line));
            }
        } catch (IOException e) {
            throw new DookException(FILEREAD_ERROR_MSG);
        }
        return result;
    }

    public void loadAliasesFromFile(Parser parser) throws DookException {
        verifyFileExists(aliasesListPath);
        try {
            BufferedReader reader = Files.newBufferedReader(aliasesListPath);
            while (reader.ready()) {
                String line = reader.readLine();
                parser.addMapping(line);
            }
        } catch (IOException e) {
            throw new DookException(FILEREAD_ERROR_MSG);
        }
    }

    public String saveAliases(Parser parser) throws DookException {
        String toSave = parser.getAliasesAsSaveableString();
        try {
            writeAliasesToFile(toSave);
        } catch (IOException e) {
            throw new DookException(FILESAVE_ERROR_MSG);
        }
        return "New aliases saved!";
    }

    private void writeAliasesToFile(String textToAdd) throws IOException {
        byte[] strToBytes = textToAdd.getBytes();
        Files.write(this.aliasesListPath, strToBytes);
    }



    private void verifyFileExists(Path path) {
        if (Files.exists(path)) {
            return;
        }
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
    public String saveTaskList(TaskList taskList) throws DookException {
        String toSave = taskList.accumulateTasks((task, str) ->
                str + (task.getSaveableString() + "\n"), "");
        try {
            writeTaskListToFile(toSave);
        } catch (IOException e) {
            throw new DookException(FILESAVE_ERROR_MSG);
        }
        return FILESAVE_SUCCESS_MSG;
    }

    private void writeTaskListToFile(String textToAdd) throws IOException {
        byte[] strToBytes = textToAdd.getBytes();
        Files.write(this.taskListPath, strToBytes);
    }

    /**
     * Parses plaintext and converts it into a Task.
     *
     * @param str Raw string representing a task.
     * @return The converted Task.
     */
    private Task getTaskFromString(String str) throws DookException {
        String[] params = str.split("//");
        String taskCode = params[0];
        assert !Objects.equals(taskCode, "") : "taskCode should not be empty";
        boolean isDone = params[1].equals("X");
        switch (taskCode) {
        case "T":
            return new Todo(params[2].trim(), isDone);
        case "D":
            return new Deadline(params[2].trim(), params[3].trim(), isDone);
        case "E":
            return new Event(params[2].trim(), params[3].trim(), params[4].trim(), isDone);
        default:
            throw new DookException(FILEREAD_ERROR_MSG);
        }
    }

}
