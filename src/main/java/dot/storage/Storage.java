package dot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import dot.errors.DotException;
import dot.errors.TaskError;
import dot.tasks.Deadline;
import dot.tasks.Event;
import dot.tasks.Task;
import dot.tasks.Todo;

/**
 * The Storage class is responsible for fileIO.
 */
public class Storage {

    // TODO: Handle the situation of the data file being corrupted
    private final String storageLocation;

    /**
     * Constructor for Storage.
     * Current Storage is only designed to handle path names in the
     * following format: <code>{@literal ./<directory>/<filename>}</code>.
     *
     * @param storageLocation Pathname in format <code>{@literal ./<directory>/<filename>}</code>
     */
    public Storage(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    /**
     * Attempts to create the given directory and file,
     * if they do not exist. Then, it returns a File object.
     *
     * @return File object in the given pathname, subject to format.
     * @throws DotException On detected error.
     */
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

    /**
     * Accesses the file using getFile, reads it
     * and parse it into an ArrayList of Tasks to return.
     *
     * @return An ArrayList of Tasks from the file.
     * @throws DotException On detected error.
     */
    public ArrayList<Task> getTasks() throws DotException {
        try {
            File file = this.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Stream<String> lines = bufferedReader.lines();

            ArrayList<Task> taskList = new ArrayList<>();

            lines.forEach(line -> {
                if (line.isBlank()) {
                    return;
                }
                addTaskToList(line, taskList);
            });
            return taskList;
        } catch (IOException e) {
            throw new DotException("Error reading file", TaskError.ERR_READING_FILE);
        }
    }

    /**
     * Helper method for getTasks. Parses currLine to appropriate variables
     * and creates the appropriate Tasks to add into taskList,
     *
     * @param currLine This is a line representing a task from the text file.
     * @param taskList This is the taskList to populate.
     */
    private void addTaskToList(String currLine, ArrayList<Task> taskList) {
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
        default:
            break;
        }
    }

    /**
     * Accesses the data file using getFile and writes
     * the given taskList to it.
     *
     * @param taskList This is the TaskList to write.
     * @throws DotException On detected error.
     */
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
