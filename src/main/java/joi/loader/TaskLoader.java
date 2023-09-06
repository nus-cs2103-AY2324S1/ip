package joi.loader;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

import joi.utils.TaskList;
import joi.utils.Task;

import joi.parser.TaskParser;

public class TaskLoader {
    private static final String FILEPATH = "./duke.txt";

    public TaskLoader() {
    }

    public static TaskList loadTasks() throws IOException {
        File file = new File(FILEPATH);
        TaskList taskList = new TaskList();

        if (file.exists()) {
            System.out.println("Loading tasks from file...");
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
            String line;
            while ((line = reader.readLine()) != null) {
                // parse line and insert into taskList
                Task task = TaskParser.readTasksFromFile(line);
                taskList.addTask(task);
            }

            taskList.listTasks();
        } else {
            System.out.println("Start Joi without task file...");
        }

        return taskList;
    }

    public static void storeTasks(TaskList cur) throws IOException {
        try (FileWriter writer = new FileWriter(FILEPATH)) {
            for (int i = 0; i < cur.size(); i++) {
                Task task = cur.get(i);
                writer.write(task.getSaveString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Failed to write to file.");
            throw e;
        }
    }
}
