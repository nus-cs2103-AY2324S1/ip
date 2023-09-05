package duke.utility;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class responsible for loading and saving tasks from and to a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static boolean isValidTaskType(String taskType) {
        return taskType.equals("T") || taskType.equals("D") || taskType.equals("E");
    }

    private static boolean isValidDoneFlag(String doneFlag) {
        return doneFlag.equals("0") || doneFlag.equals("1");
    }

    private static boolean isValidTaskFormat(String taskType, int taskFormatLength) {
        return (taskType.equals("T") && taskFormatLength == 3) || (taskType.equals("D") && taskFormatLength == 4)
                || (taskType.equals("E") && taskFormatLength == 5);
    }

    /**
     * Loads tasks from the file and adds them to the provided TaskList.
     *
     * @param taskList The TaskList to which tasks should be added.
     */
    public void loadTasks(TaskList taskList) {
        try {
            File file = new File(filePath);
            System.out.println("Loading tasks...");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                validateString(line);
                Task task = convertStringIntoTask(line);
                taskList.addTask(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please create duke.txt in the resource folder");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("An error has occured during file loading. Plese check duke.txt in the data folder");
            System.exit(1);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Tasks loaded successfully!");
    }

    /**
     * Converts a data string into a Task object.
     *
     * @param dataString The string containing task data.
     * @return The parsed Task object.
     * @throws DukeException If parsing encounters an error.
     */
    public Task convertStringIntoTask(String dataString) throws DukeException {
        String[] dataArr = dataString.split(" \\| ");
        String taskType = dataArr[0];
        boolean isDone = dataArr[1].equals("1");
        String taskName = dataArr[2];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(taskName);
            break;
        case "D":
            task = new Deadline(taskName, dataArr[3]);
            break;
        case "E":
            task = new Event(taskName, dataArr[3], dataArr[4]);
            break;
        default:
            throw new DukeException("Unexpected error");
        }

        if (isDone) {
            task.markAsDone(false);
        }
        return task;
    }

    /**
     * Validates a data string to ensure its format is correct.
     *
     * @param dataString The string to be validated.
     * @throws DukeException If the format is invalid.
     */
    public void validateString(String dataString) throws DukeException {

        String[] dataArr = dataString.split(" \\| ");
        int dataLength = dataArr.length;

        String taskType = dataArr[0];
        String doneFlag = dataArr[1];

        if (!isValidTaskType(taskType)) {
            throw new DukeException("Invalid task type");
        }

        if (!isValidDoneFlag(doneFlag)) {
            throw new DukeException("Invalid done flag");
        }

        if (!isValidTaskFormat(taskType, dataLength)) {
            throw new DukeException("Invalid task format");
        }


    }

    /**
     * Saves a task to the file.
     *
     * @param task The task to be saved.
     */
    public void saveTask(Task task) {
        try {
            File file = new File(filePath);
            java.io.FileWriter fileWriter = new java.io.FileWriter(file, true);
            fileWriter.write(task.convertTaskToString() + "\n");
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Modifies an existing task in the file.
     *
     * @param taskNumber The index of the task to be modified.
     * @param newTask    The new task to replace the existing task.
     */
    public void modifyTask(int taskNumber, Task newTask) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            StringBuilder newContents = new StringBuilder();
            int count = 0;
            while (scanner.hasNext()) {
                String dataString = scanner.nextLine();
                if (count == taskNumber - 1) {
                    newContents.append(newTask.convertTaskToString() + "\n");
                } else {
                    newContents.append(dataString + "\n");
                }
                count++;
            }
            scanner.close();
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(newContents.toString());
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Deletes a task from the file.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder newContents = new StringBuilder();
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (count != taskNumber - 1) {
                    newContents.append(line + "\n");
                }
                count++;
            }
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(newContents.toString());
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            System.exit(1);
        }
    }
}


