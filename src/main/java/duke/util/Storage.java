package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.exception.DukeException;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage file management system of the task list.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs the storage system.
     * @param filePath File path of the storage system.
     */
    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Saves task(to-do) into file.
     * @param description Description of task.
     * @throws DukeException Error saving file.
     */
    public void saveTask(String description) throws DukeException {
        try {
            String taskToSave = String.format("T | 0 | %s", description);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves task(deadline) into file.
     * @param description Description of task.
     * @param deadline Deadline of task.
     * @throws DukeException Error saving file.
     */
    public void saveTask(String description, String deadline) throws DukeException {
        try {
            String taskToSave = String.format("D | 0 | %s | %s", description, deadline);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves task(event) into file.
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     * @throws DukeException Error saving file.
     */
    public void saveTask(String description, String from, String to) throws DukeException {
        try {
            String taskToSave = String.format("E | 0 | %s | %s | %s", description, from, to);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves the task straight to the file (lower-level)
     * @param task String of task to save.
     * @throws DukeException Error saving file.
     */
    public void saveTaskToFile(String task) throws DukeException {
        String fileName = filePath;
        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    writer.newLine();
                    writer.write(task);
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Deletes task from file.
     * @param taskNumber Index of task to delete.
     * @throws DukeException Error modifying file.
     */
    public void deleteTaskFromFile(Integer taskNumber) throws DukeException {
        String fileName = filePath;
        List<String> lines = new ArrayList<>();
        String currentLine;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Integer lineNumber = 1;
            while ((currentLine = reader.readLine()) != null) {
                if (!lineNumber.equals(taskNumber)) {
                    lines.add(currentLine);
                }
                lineNumber++;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Load tasks from file into list.
     * @param list List for file to populate.
     * @return List of tasks from file.
     * @throws DukeException Error reading from file.
     */
    public List<Task> loadTasksFromFile(List<Task> list) throws DukeException {
        String fileName = filePath;
        Path filePath = Paths.get(fileName);

        try {
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String[] formattedLine = line.split(" \\| ");
                    switch (formattedLine[0]) {
                    case "T": {
                        Task task = new Todo(formattedLine[2]);
                        if (formattedLine[1].equals("1")) {
                            task.setDone();
                        }
                        list.add(task);
                        break;
                    }
                    case "D": {
                        Task task = new Deadline(formattedLine[2], formattedLine[3]);
                        if (formattedLine[1].equals("1")) {
                            task.setDone();
                        }
                        list.add(task);
                        break;
                    }
                    case "E": {
                        Task task = new Event(formattedLine[2], formattedLine[3], formattedLine[4]);
                        if (formattedLine[1].equals("1")) {
                            task.setDone();
                        }
                        list.add(task);
                        break;
                    }
                    default: break;
                    }
                }
                fileReader.close();
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return list;
    }
}
