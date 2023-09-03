package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles storage of tasks.
 * Uses text file as storage method.
 */
public class TaskStorage {
    public static final String DEFAULT_PATH = "./data/duke.txt";

    private final File file;

    /**
     * duke.TaskStorage constructor, uses default path.
     */
    public TaskStorage() {
        this.file = new File(DEFAULT_PATH);
    }

    /**
     * Load existing tasks from text file.
     * @return ArrayList of Tasks loaded
     * @throws DukeException Any errors encountered while loading
     */
    public ArrayList<Task> loadExistingTasks() throws DukeException {
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
                return new ArrayList<>();
            }

            ArrayList<Task> tasks = new ArrayList<>();
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Format: <type>|<isDone>|<taskName>[|...]
            Parser parser;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task;
                parser = Parser.from(line);

                switch(parser.getCommand()) {
                case "todo":
                    task = new Todo(parser.getArg());
                    break;
                case "deadline":
                    task = new Deadline(
                            parser.getArg(),
                            parser.getOptArgAsDateTime("by")
                    );
                    break;
                case "event":
                    task = new Event(
                            parser.getArg(),
                            parser.getOptArgAsDateTime("from"),
                            parser.getOptArgAsDateTime("to")
                    );
                    break;
                default:
                    continue;
                }

                if (parser.getOptArg("done") != null) {
                    task.markDone();
                }

                tasks.add(task);
            }
            fileReader.close();

            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error while loading tasks: " + e.getMessage());
        }
    }


    /**
     * Stores serialized tasks in text file.
     * @param tasks Tasks to store
     * @throws DukeException Any errors encountered while storing
     */
    public void storeTasks(List<Task> tasks) throws DukeException {
        String serialized = tasks.stream()
                .map(Task::serialize)
                .collect(Collectors.joining("\n"));

        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(serialized);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error while storing tasks: " + e.getMessage());
        }
    }
}
