package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.DukeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    public void saveTask(String description) throws DukeException {
        try {
            String taskToSave = String.format("T | 0 | %s", description);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void saveTask(String description, String deadline) throws DukeException {
        try {
            String taskToSave = String.format("D | 0 | %s | %s", description, deadline);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void saveTask(String description, String from, String to) throws DukeException {
        try {
            String taskToSave = String.format("E | 0 | %s | %s | %s", description, from, to);
            this.saveTaskToFile(taskToSave);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

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
