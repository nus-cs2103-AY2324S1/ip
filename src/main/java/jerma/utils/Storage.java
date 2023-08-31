package jerma.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jerma.tasks.Deadline;
import jerma.tasks.Event;
import jerma.tasks.Task;
import jerma.tasks.Todo;

public class Storage {
    private static final Path SAVE_FOLDER = Paths.get(".", "data");
    private static final Path SAVE_PATH = Paths.get(SAVE_FOLDER.toString(),
            "save.txt");

    public static TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        List<String> save = Files.readAllLines(SAVE_PATH);

        for (String taskString : save) {
            String[] taskElements = taskString.split("\\|");
            Task task;

            switch (taskElements[0]) {
            case "T":
                task = new Todo(taskElements[2]);
                break;
            case "D":
                task = new Deadline(taskElements[2], taskElements[3]);
                break;
            case "E":
                task = new Event(taskElements[2], taskElements[3],
                        taskElements[4]);
                break;
            default:
                throw new UnsupportedOperationException();
            }

            if (taskElements[1].equals("1")) {
                task.setDone();
            }

            tasks.add(task);
        }
        System.out.println("List loaded");

        return tasks;
    }

    public static void save(TaskList tasks) throws IOException {
        Files.createDirectories(SAVE_FOLDER);
        if (!Files.exists(SAVE_PATH)) {
            Files.createFile(SAVE_PATH);
        }

        String saveString = "";
        for (Task task : tasks) {
            saveString += task.save() + "\n";
        }

        byte[] saveBytes = saveString.getBytes();
        Files.write(SAVE_PATH, saveBytes);
        System.out.println("List saved");
    }
}
