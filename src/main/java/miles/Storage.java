package miles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;

import miles.task.Deadline;
import miles.task.Event;
import miles.task.Task;
import miles.task.ToDo;

/**
 * Represents the storage of the tasks, where we can load our tasks from a file or save our tasks
 * into a file.
 */
public class Storage {
    private String filePath;
    private String directoryPath;
    private Ui ui;

    /**
     * Constructor to create a new storage.
     * 
     * @param filePath       file path of the text file
     * @param directoryPath  directory path of the text file
     */
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        this.ui = new Ui();
    }

    /**
     * Adds task into task list and triggers a save to the text file when the user adds a task.
     * 
     * @param task      the task to be saved
     * @param taskList  the task list containing the task to be saved
     */
    public void saveWhenAddTask(Task task, TaskList taskList) {
        taskList.addTask(task);
        this.saveFile(taskList);
    }

    /**
     * Loads the file from the data folder if it exists, else it creates a new file for users to 
     * store their tasks.
     * 
     * @returns TaskList   a TaskList object containing the tasks from the file
     * @throws IOException if there is an error creating the file  
     */
    public TaskList loadFile() {
        try {
            File directory = new File(this.directoryPath);
            File newFile = new File(this.filePath);

            // check if directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // check if file exists
            if (!newFile.exists()) {
                newFile.createNewFile();
                System.out.println("created a new file");
                return new TaskList(); 
            }

            Scanner scanner = new Scanner(newFile);
            TaskList taskList = new TaskList();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0].trim();

                if (taskType.equals("T")) {
                    String status = parts[1].trim();
                    String task = parts[2];
                    Task newTask = new ToDo("todo " + task);

                    taskList.updateTaskStatusFromFile(newTask, status);
                    taskList.addTask(newTask);
                } else if (taskType.equals("D")) {
                    String status = parts[1].trim();
                    String task = parts[2].trim();
                    String deadline = parts[3].trim();
                    Task newTask = new Deadline(task, deadline);

                    taskList.updateTaskStatusFromFile(newTask, status);
                    taskList.addTask(newTask);                  
                } else if (taskType.equals("E")) {
                    String status = parts[1].trim();
                    String task = parts[2].trim();
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    Task newTask = new Event(task, from, to);

                    taskList.updateTaskStatusFromFile(newTask, status);
                    taskList.addTask(newTask);
                }
            }

            scanner.close();
            return taskList;

        } catch (IOException e) {
            this.ui.printErrorMsg(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Saves the tasks in the task list to the text file.
     * 
     * @param taskList     the task list containing the tasks to be saved
     * @throws IOException if there is an error writing to the file
     */
    public void saveFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int n = taskList.getSize();

            for (int i = 0; i < n; i += 1) {
                Task currentTask = taskList.getTask(i);
                String s = currentTask.saveStringToFile();
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            this.ui.printErrorMsg(e.getMessage());
        }
    }
}
