package pogo.storage;

import pogo.parsers.TaskParser;
import pogo.tasks.*;
import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextStorage implements Storage {
    private static final String TASKS_FILE = "./data/pogo.tasks.txt";

    private TextStorage() {
    }

    public static TextStorage of() {
        return new TextStorage();
    }

    private String formatTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFormattedString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void createTaskFileIfNotExist() throws IOException {
        File file = new File(TASKS_FILE);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        file.createNewFile();
    }

    public void save(List<Task> tasks) {
        try {
            createTaskFileIfNotExist();
        } catch (IOException e) {
            System.out.println("Error creating task file");
            return;
        }

        try {
            FileWriter fw = new FileWriter(TASKS_FILE);
            fw.write(formatTasks(tasks));
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving pogo.tasks");
        }
    }

    public List<Task> load() throws IOException {
        createTaskFileIfNotExist();

        File f = new File(TASKS_FILE);
        Scanner s = new Scanner(f);

        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] split = line.split(" \\| ");
            TaskType taskType = TaskParser.toTaskType(split[0]);

            if (taskType == null) {
                System.out.println("Failed to read the following task: " + line);
                continue;
            }

            Task task = null;
            try {
                switch (taskType) {
                case TODO:
                    task = ToDo.fromFormattedString(line);
                    break;
                case DEADLINE:
                    task = Deadline.fromFormattedString(line);
                    break;
                case EVENT:
                    task = Event.fromFormattedString(line);
                    break;
                }

                if (task != null) {
                    tasks.add(task);
                }
            } catch (PogoInvalidTaskException e) {
                System.out.println("Failed to read the following task: " + line);
            }
        }

        return tasks;
    }
}
