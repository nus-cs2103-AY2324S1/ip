package duke;

import duke.tasks.Task;
import duke.trivia.Trivia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Storage {
    private String taskFilePath;
    private String triviaFilePath;

    public Storage(String taskFilePath, String triviaFilePath) {
        this.taskFilePath = taskFilePath;
        this.triviaFilePath = triviaFilePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        saveToFile(tasks, taskFilePath, Task::toFileFormat);
    }

    public void saveTrivia(ArrayList<Trivia> trivia) throws IOException {
        saveToFile(trivia, triviaFilePath, Trivia::toFileFormat);
    }

    public ArrayList<Task> loadTasks() throws IOException {
        return loadFromFile(taskFilePath, Task::fromFileFormat);
    }

    public ArrayList<Trivia> loadTrivia() throws IOException {
        return loadFromFile(triviaFilePath, Trivia::fromFileFormat);
    }

    private <T> void saveToFile(ArrayList<T> items, String filePath, Function<T, String> toFileFormatFunc) throws IOException {
        List<String> lines = new ArrayList<>();
        for (T item : items) {
            lines.add(toFileFormatFunc.apply(item));
        }
        Files.write(Paths.get(filePath), lines);
    }

    private <T> ArrayList<T> loadFromFile(String filePath, Function<String, T> fromFileFormatFunc) throws IOException {
        ArrayList<T> items = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                items.add(fromFileFormatFunc.apply(line));
            }
        }
        return items;
    }
}