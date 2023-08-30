import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Storage {

    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public void processLine(String line, TaskList tasks) {
        Task newTask;
        String[] lineSeq = line.split(" \\| ");
        try {
            if (line.startsWith("T") && lineSeq.length == 3
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Todo(lineSeq[2]);
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else if (line.startsWith("D") && lineSeq.length == 4
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Deadline(lineSeq[2], LocalDateTime.parse(lineSeq[3]));
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else if (line.startsWith("E") && lineSeq.length == 5
                    && (Objects.equals(lineSeq[1], "1") || Objects.equals(lineSeq[1], "0"))) {
                newTask = new Event(lineSeq[2], LocalDateTime.parse(lineSeq[3]), LocalDateTime.parse(lineSeq[4]));
                if (Objects.equals(lineSeq[1], "1")) newTask.markAsDone();
                tasks.add(newTask);
            } else {
                // else do nothing, specifying that the task is invalid.
                System.out.println("The task " + line + " is invalid and is ignored!");
            }
        } catch (DateTimeParseException e) {
            // do nothing, specifying that the task is invalid.
            System.out.println("The task " + line + " is invalid and is ignored!");
        }
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        boolean fileExists = Files.exists(filePath);
        try {
            if (!fileExists) {
                Files.createFile(filePath);
            } else {
                List<String> contents = Files.readAllLines(filePath);
                for (String line : contents) {
                    processLine(line, tasks);
                }
            }
            return tasks;
        } catch (IOException e) {
            System.err.println("Cannot read initial tasks from file!");
            return new TaskList();
        }
    }

    public void saveTasks(TaskList tasks) {
        boolean fileExists = Files.exists(filePath);
        try {
            if (!fileExists) {
                Files.createFile(filePath);
            } else {
                ArrayList<String> lines = tasks.getSavedStrings();
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            System.err.println("Cannot write tasks to file!");
            e.printStackTrace();
        }
    }
}
