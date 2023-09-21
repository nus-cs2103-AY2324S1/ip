package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Manages storage of the task list.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage with specific storage file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list with file.
     *
     * @return The task list.
     * @throws IOException If unable to gain input from file.
     */
    public ArrayList<Task> load() throws IOException {
        String dirPath = this.filePath.split("/")[0];
        File f = getFile(dirPath);
        ArrayList<Task> tasks = loadTasks(f);
        return tasks;
    }

    /**
     * Returns the file for storage. Initializes the directory and the file
     * when necessary.
     *
     * @param path Path of the directory of the storage file.
     * @throws IOException If an input or output exception occurs.
     */
    private File getFile(String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    /**
     * Returns the task list after loading.
     *
     * @param f The file for storage.
     * @throws FileNotFoundException If an attempt to open the file fails.
     */
    private ArrayList<Task> loadTasks(File f) throws FileNotFoundException {
        assert f.exists();
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(storageToTask(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Converts the String input to Task.
     *
     * @param input The String input.
     * @return The corresponding Task.
     */
    private Task storageToTask(String input) throws DukeException {
        String taskType = input.split(" \\| ")[0];
        boolean isComplete = input.split(" \\| ")[1].equals("1");
        String description = input.split(" \\| ")[2];
        if (taskType.equals("T")) {
            return new ToDo(description, isComplete);
        } else if (taskType.equals("D")) {
            return this.createDeadlineFromStorage(input, description, isComplete);
        } else if (taskType.equals("E")) {
            return this.createEventFromStorage(input, description, isComplete);
        } else if (taskType.equals("F")) {
            return this.createFixedDurationTaskFromStorage(input, description, isComplete);
        } else {
            throw new DukeException("Unknown task type.");
        }
    }


    /**
     * Creates the FixedDurationTask from the storage input.
     *
     * @param input The input line.
     * @param description The description of the FixedDurationTask.
     * @param isComplete Whether the FixedDurationTask is completed.
     * @return The Event.
     */
    private FixedDurationTask createFixedDurationTaskFromStorage(String input, String description, boolean isComplete) {
        String duration = input.split(" \\| ")[3];
        return new FixedDurationTask(description, isComplete, duration);
    }

    /**
     * Creates the Event from the storage input.
     *
     * @param input The input line.
     * @param description The description of the Event.
     * @param isComplete Whether the Event is completed.
     * @return The Event.
     */
    private Event createEventFromStorage(String input, String description, boolean isComplete) {
        LocalDate start = LocalDate.parse(input.split(" \\| ")[3],
                DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        LocalDate end = LocalDate.parse(input.split(" \\| ")[4],
                DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        return new Event(description, isComplete, start, end);
    }

    /**
     * Creates the Deadline from the storage input.
     *
     * @param input The input line.
     * @param description The description of the Deadline.
     * @param isComplete Whether the Deadline is completed.
     * @return The Event.
     */
    private Deadline createDeadlineFromStorage(String input, String description, boolean isComplete) {
        LocalDate d = LocalDate.parse(input.split(" \\| ")[3],
                DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        return new Deadline(description, isComplete, d);
    }


    /**
     * Adds the last task in the task list to file.
     *
     * @param list The task list.
     * @throws DukeException If unable to write to file.
     */
    public void addTheLastTaskToFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (list.size() != 1) {
                fw.write("\n");
            }
            fw.write(list.get(list.size() - 1).toTxt());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }

    /**
     * Rewrites the whole file with the task list.
     *
     * @param list The task list.
     * @throws DukeException If unable to write to file.
     */
    public void rewriteFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    fw.write(list.get(i).toTxt());
                    fw.write("\n");
                    continue;
                }
                fw.write(list.get(i).toTxt());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }
}

