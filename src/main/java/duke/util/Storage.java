package duke.util;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String fileLine;
            while ((fileLine = bufferedReader.readLine()) != null) {
                Task task = createTaskFromFile(fileLine);
                tasks.add(task);
            }

            bufferedReader.close();
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task: tasks) {
            String taskFileString = task.toFileString();
            writer.write(taskFileString + "\n");
        }

        writer.close();
    }

    private static Task createTaskFromFile(String fileLine) {
        String[] parts = fileLine.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String type = parts[0];
        String description = parts[2];

        Task task;

        if (type.equals("[T]")) {
            task = new ToDo(description);
        } else if (type.equals("[D]")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else {
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
        }

        task.isDone = parts[1].equals("1");
        return task;
    }
}
