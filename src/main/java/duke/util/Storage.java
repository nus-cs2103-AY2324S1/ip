package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
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
        try {
            checkDataFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.newLine();
                writer.write(task);
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
        List<String> lines = new ArrayList<>();
        String currentLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Integer lineNumber = 1;
            // Copies tasks that are not to be deleted out to temporary list.
            while ((currentLine = reader.readLine()) != null) {
                if (!lineNumber.equals(taskNumber)) {
                    lines.add(currentLine);
                }
                lineNumber++;
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            // Writes tasks from temporary list into txt file.
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
     *
     * @param list List for file to populate.
     * @throws DukeException Error reading from file.
     */
    public void loadTasksFromFile(List<Task> list) throws DukeException {
        try {
            checkDataFile();
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                processTaskIntoList(line, list);
            }
            fileReader.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Processes each task in data txt file format into application's TaskList.
     * @param taskLine Task in data txt file.
     * @param list Application's TaskList.
     * @throws DukeException Error instantiating Task object.
     */
    public void processTaskIntoList(String taskLine, List<Task> list) throws DukeException {
        String[] formattedLine = taskLine.split(" \\| ");
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

    public void checkDataFile() throws Exception {
        String fileName = filePath;
        Path filePath = Paths.get(fileName);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
