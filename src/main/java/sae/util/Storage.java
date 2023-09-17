package sae.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import sae.task.Deadline;
import sae.task.Event;
import sae.task.Task;
import sae.task.TaskList;
import sae.task.Todo;


public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public TaskList loadTasks(String filePath) throws IOException {
        File f = new File(filePath);
        // if the files doesn't exist, we make the file
        if (!f.exists()) {
            createFile(f);
            throw new FileNotFoundException("The file has not been created. Try again.");
        } else {
            return readTasks(filePath);
        }
    }

    public TaskList readTasks(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        TaskList taskList = new TaskList();

        while (scanner.hasNext()) {
            String[] taskDetails = scanner.nextLine().split("\\|");
            String taskType = taskDetails[0].trim();
            boolean isCompleted = taskDetails[1].trim().equals("1");
            String description = taskDetails[2].trim();

            Task task = createTask(taskType, description, taskDetails, isCompleted);
            taskList.addTask(task);
        }

        scanner.close();
        return taskList;
    }

    private Task createTask(String taskType, String description, String[] taskDetails, boolean isCompleted) {
        switch (taskType) {
            case "T":
                Todo todo = new Todo(description);
                if (isCompleted) {
                    todo.markTask();
                }
                return todo;
            case "D":
                String by = taskDetails[3].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
                LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                Deadline deadline = new Deadline(description, dateTime);
                if (isCompleted) {
                    deadline.markTask();
                }
                return deadline;
            case "E":
                String from = taskDetails[3].trim();
                String to = taskDetails[4].trim();
                Event event = new Event(description, from, to);
                if (isCompleted) {
                    event.markTask();
                }
                return event;
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }




    public void saveTasks(TaskList taskList) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskString = task.toFileString();
            fw.write(taskString + System.lineSeparator());
        }

        fw.close();
    }


}