package dot.storage;

import dot.errors.DotException;
import dot.errors.TaskError;
import dot.tasks.Deadline;
import dot.tasks.Event;
import dot.tasks.Task;
import dot.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // TODO: Handle the situation of the data file being corrupted
    private final String storageLocation;

    /**
     * Current Storage is only designed to handle path names in the
     * following format: <code>{@literal ./<directory>/<filename>}</code>
     *
     * @param storageLocation Pathname in format <code>{@literal 。/<directory>/<filename>}</code>
     */
    public Storage(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public File getFile() throws DotException {
        try {
            String[] splitBySlash = this.storageLocation.split("/");
            String directoryName = splitBySlash[1];
            String fileName = splitBySlash[2];
            // Create directory if it does not exist
            File directory = new File(directoryName);
            directory.mkdirs();
            // Create file if it does not exist
            File file = new File(directoryName, fileName);
            file.createNewFile();
            return file;
        } catch (IOException | SecurityException e) {
            throw new DotException("Error getting file", TaskError.ERR_GETTING_FILE);
        }
    }

    public ArrayList<Task> getTasks() throws DotException {
        try {
            File file = this.getFile();
            Scanner sc = new Scanner(file);

            ArrayList<Task> taskList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (currLine.isBlank()) {
                    break;
                }
                // Pipe is a special character is regex
                String[] items = currLine.split(" \\| ");
                String taskType = items[0];
                boolean isCompleted = items[1].equals("1");
                String description = items[2];
                switch (taskType) {
                case "T":
                    Task todoTask = new Todo(description, isCompleted);
                    taskList.add(todoTask);
                    break;
                case "D":
                    Task deadlineTask = new Deadline(description, items[3], isCompleted);
                    taskList.add(deadlineTask);
                    break;
                case "E":
                    Task eventTask = new Event(description, items[3], items[4], isCompleted);
                    taskList.add(eventTask);
                    break;
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DotException("Error reading file", TaskError.ERR_READING_FILE);
        }
    }

    public void saveTasks(ArrayList<Task> taskList) throws DotException {
        try {
            File file = this.getFile();
            FileWriter fw = new FileWriter(file);
            for (Task currTask : taskList) {
                fw.write(currTask.getFileFormat() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DotException("Error saving tasks", TaskError.ERR_WRITING_FILE);
        }
    }
}
