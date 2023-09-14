package dude;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.ToDo;

/**
 * Represents the Storage system of Dude and deals with loading tasks from
 * the file and saving tasks in the file.
 */
public class Storage {

    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String filepath;

    /**
     * Constructs a new Storage object with the specified filepath.
     *
     * @param filepath Path to the file.
     */
    public Storage(String filepath) {
        assert !this.filepath.trim().isEmpty() : "Filepath should not be empty";
        this.filepath = filepath;
    }

    /**
     * Saves the task list to the file specified by filepath.
     *
     * @param taskList List of tasks to be saved.
     */
    public void saveTasksToDisk(TaskList taskList) throws IOException {
        File file = new File(this.filepath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created at: " + this.filepath);
                } else {
                    System.err.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file: " + e.getMessage());
            }
        }

        String data = convertTaskListToString(taskList);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Converts task list into String for saving to file.
     * @param taskList
     * @return
     */
    public String convertTaskListToString(TaskList taskList) {
        String data = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            data += task.saveTask();
        }

        return data;
    }

    /**
     * Loads the saved tasks from the file specified by filepath.
     *
     * @return List of tasks from the file.
     * @throws FileNotFoundException If no file is not found in the filepath.
     */
    public TaskList loadTasksFromDisk() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        File file = new File(this.filepath);

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String storedTaskDetails = sc.nextLine();
                    Task task = convertStringToTask(storedTaskDetails);
                    taskList.addTask(task);
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
        } else {
            try {
                file.createNewFile();
                System.out.println("File created at: " + this.filepath);
            } catch (IOException e) {
                System.out.println("File creating error");
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("You have %d saved tasks.\n", taskList.getSize());
        return taskList;
    }

    /**
     * Converts a string of task details into Task.
     * @param storedTaskDetails
     * @return
     */
    public Task convertStringToTask(String storedTaskDetails) {
        String[] taskDetails = storedTaskDetails.split("\\s+\\|\\s+");
        String taskType = taskDetails[0];
        String taskIsDone = taskDetails[1];
        String taskDesription = taskDetails[2];

        Task task = null;

        if (taskType.equals("T")) {
            task = new ToDo(taskDesription);
        } else if (taskType.equals("D")) {
            String byInput = taskDetails[3];
            LocalDateTime by = LocalDateTime.parse(byInput);
            task = new Deadline(taskDetails[2], by);
        } else if (taskType.equals("E")) {
            String fromInput = taskDetails[3];
            String toInput = taskDetails[4];
            LocalDateTime from = LocalDateTime.parse(fromInput);
            LocalDateTime to = LocalDateTime.parse(toInput);
            task = new Event(taskDetails[2], from, to);
        }
        task.setDone(taskIsDone.equals("1"));
        return task;
    }
}
