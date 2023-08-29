package duke;

import duke.exception.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createFile(File file) throws DukeStorageException {
        try {
            boolean isCreated = file.getParentFile().mkdirs() && file.createNewFile();
            if (!isCreated && !file.exists()) {
                throw new DukeStorageException("Something went wrong with creating the store of tasks.");
            }
        } catch (IOException e) {
            throw new DukeStorageException("Something went wrong with creating the store of tasks.");
        }
    }

    public List<Task> loadData() throws DukeStorageException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        try {
            Scanner loader = new Scanner(file);
            while (loader.hasNextLine()) {
                String[] fields = loader.nextLine().split(";");
                String description = fields[2];
                boolean isDone = fields[1].equals("X");
                switch (fields[0]) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(description, isDone, LocalDateTime.parse(fields[3])));
                        break;
                    case "E":
                        tasks.add(new Event(description, isDone, LocalDateTime.parse(fields[3]),
                                LocalDateTime.parse(fields[4])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            createFile(file);
        }
        return tasks;
    }

    public void saveData(TaskList tasks) throws DukeStorageException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.stringifyTasks());
        } catch (IOException e) {
            throw new DukeStorageException("Something went wrong with saving the tasks");
        }
    }
}
