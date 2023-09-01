package duke.storage;

import duke.DukeException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        try {
            getFile(filePath);
        }  catch (DukeException e) {
            throw e;
        }
    }

    private void getFile(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    System.out.println("Directory created: " + directory.getAbsolutePath());
                } else {
                    System.out.println("Failed to create directory.");
                }
            }

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error creating file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks;
        try {
            tasks = loadTasksFromFile();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading duke.tasks from file: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("X");
            String taskDescription = parts[2];

            Task task;

            if (taskType.equals("T")) {
                task = new ToDoTask(taskDescription);
            } else if (taskType.equals("D")) {
                LocalDateTime deadline = LocalDateTime.parse(parts[3], Ui.DATE_OUTPUT_FORMAT);
                task = new DeadlineTask(taskDescription, deadline);
            } else if (taskType.equals("E")) {
                LocalDateTime from = LocalDateTime.parse(parts[3], Ui.DATE_OUTPUT_FORMAT);
                LocalDateTime to = LocalDateTime.parse(parts[4], Ui.DATE_OUTPUT_FORMAT);
                task = new EventTask(taskDescription, from, to);
            } else {
                throw new IOException("Invalid task type found in file. Data file may be corrupted.");
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        reader.close();
        return tasks;
    }

    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        ArrayList<Task> tasks = taskList.getTasks();
        for (Task task : tasks) {
            fileWriter.write(task.toFileString() + "\n");
        }

        fileWriter.close();
    }
}
