package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Represents a storage mechanism for saving and loading tasks to/from a file.
 */
public class Storage {
    private String filepath;


    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for saving and loading tasks
     */
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the given string to the file specified in the constructor.
     *
     * @param saveString The string to be saved to the file
     * @throws LukeException If there is an error writing to the file
     */
    public void save(String saveString) throws LukeException {
        try {
            File file = new File(filepath);
            File parent_directory = file.getParentFile();

            if (parent_directory != null) {
                parent_directory.mkdirs();
            }

            FileWriter fw = new FileWriter(file);
            fw.write(saveString);
            fw.close();
        } catch (IOException e) {
            throw new LukeException("Error writing saved tasks into '" + filepath
                    + "'\n\n Save aborted");
        }
    }

    /**
     * Loads tasks from the file specified in the constructor and returns them as an ArrayList.
     *
     * @return An ArrayList of loaded tasks
     * @throws LukeException If there is an error reading from the file or if the data format is invalid
     */
    public ArrayList<Task> load() throws LukeException {
        try {
            ArrayList<Task> allTasks = new ArrayList<>();
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String savedData = s.nextLine();
                String[] taskDetails = Arrays.stream(savedData.split("\\|")).map(String::trim).toArray(String[]::new);

                if (taskDetails.length < 2) {
                    throw new LukeException("Unknown data '" + savedData + "'");
                }

                Task createdTask = createTask(taskDetails);
                allTasks.add(createdTask);
            }

            return allTasks;
        } catch (LukeException e) {
            throw new LukeException(
                    "Error reading saved tasks from '" + filepath + "': \n"
                            + e.getMessage() + "\n\n No tasks loaded"
            );
        } catch (FileNotFoundException e) {
            throw new LukeException(
                    "Could not find file '" + filepath + "'\n\n No tasks loaded"
            );
        }
    }

    private boolean getTaskStatus(String[] taskDetails) throws LukeException {
        assert(taskDetails.length >= 2);

        switch (taskDetails[1]) {
        case "Done":
            return true;
        case "Not Done":
            return false;
        default:
            throw new LukeException("Task neither 'Done' nor 'Not Done'");
        }
    }

    private Task createTask(String[] taskDetails) throws LukeException {
        boolean isDone = getTaskStatus(taskDetails);

        switch (taskDetails[0]) {
        case "T":
            return Todo.createTodo(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
        case "D":
            return Deadline.createDeadline(
                    Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
        case "E":
            return Event.createEvent(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
        default:
            throw new LukeException("Unknown Task Type '" + taskDetails[0] + "'");
        }
    }
}
