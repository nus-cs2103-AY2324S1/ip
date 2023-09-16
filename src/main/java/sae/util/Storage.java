package sae.util;

import sae.task.Event;
import sae.task.Task;
import sae.task.TaskList;
import sae.task.Todo;
import sae.task.Deadline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        File f = new File(filePath);
        Scanner tasks = new Scanner(f);
        TaskList taskList = new TaskList();
        while (tasks.hasNext()) {
            String str = tasks.nextLine();
            String[] taskDetails = str.split("\\|"); // Escape the pipe character
            String taskType = taskDetails[0].trim();
            String completion = taskDetails[1].trim();
            String description = taskDetails[2].trim();

            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(description);
                    if (completion.equals("1")) {
                        newTodo.markTask();
                    }
                    taskList.addTask(newTodo);
                    break;
                case "D":
                    String by = taskDetails[3].trim();

                    // Parse the 'by' string into LocalDateTime using a formatter
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");

                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

                    Deadline newDeadline = new Deadline(description, dateTime);
                    if (completion.equals("1")) {
                        newDeadline.markTask();
                    }
                    taskList.addTask(newDeadline);
                    break;
                case "E":
                    String from = taskDetails[3].trim();
                    String to = taskDetails[4].trim();
                    Event newEvent = new Event(description, from, to);
                    if (completion.equals("1")) {
                        newEvent.markTask();
                    }
                    taskList.addTask(newEvent);
                    break;
            }
        }
        tasks.close();
        return taskList;
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