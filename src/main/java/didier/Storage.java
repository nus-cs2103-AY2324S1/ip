package didier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import didier.exception.FileCorruptedException;
import didier.exception.TaskNumberException;
import didier.task.Task;

/**
 * Deals with reading tasks from a file and writing tasks to a fil. A storage object refers to
 * the memory at and allows the manipulation of a particular file in a particular directory.
 */
public class Storage {

    private String directoryPath;
    private String fileName;

    /**
     * The constructor for the Storage object.
     *
     * @param directoryPath The path to the directory where the file is located.
     * @param fileName The name of the file to read/write to.
     */
    public Storage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        File directory = new File(this.directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Gets the task list from the storage file as a String, parses it into a list of
     * task objects and returns the list.
     *
     * @return The task list stored in the file.
     */
    public TaskList getTasks() {
        File file = new File(this.directoryPath + this.fileName);
        try {
            TaskList taskList = new TaskList();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = Task.parseFileString(scanner.nextLine());
                taskList.addTask(task);
            }
            scanner.close();
            return taskList;
        } catch (FileNotFoundException | FileCorruptedException e) {
            return new TaskList();
        }
    }

    /**
     * Writes the given task list to the file by composing it into a file String.
     *
     * @param taskList The task list to be stored.
     * @throws TaskNumberException If the task list is accessed at an invalid task index.
     */
    public void saveTasks(TaskList taskList) throws TaskNumberException, FileCorruptedException {
        try {
            FileWriter fileWriter = new FileWriter(this.directoryPath + this.fileName);
            for (int i = 1; i <= taskList.getSize(); i++) {
                fileWriter.write(taskList.getTask(i).composeToFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileCorruptedException();
        }
    }
}
