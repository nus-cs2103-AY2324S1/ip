package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Storage class handles file operations for saving and retrieving tasks to/from a file.
 *
 * @author Selwyn
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private File file;

    private String filePath;

    /**
     * Constructs a Storage object with the specified directory path and file name.
     *
     * @param directoryPath The directory path where the file should be stored.
     * @param fileName The name of the file.
     */
    public Storage(String directoryPath, String fileName) {
        this.filePath = directoryPath + fileName;

        try {
            if (new File(directoryPath).mkdirs()) {
                System.out.println("Directories are created.");
            } else {
                System.out.println("Directories already exist.");
            }

            this.file = new File(filePath);

            if (this.file.createNewFile()) {
                System.out.println("File is created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects retrieved from the file.
     * @throws DukeException If there is an issue with file retrieval or if the file is corrupted.
     */
    public ArrayList<Task> retrieveTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.file);

            while (scanner.hasNext()) {
                String taskDesc = scanner.nextLine();
                String[] taskDescArr = taskDesc.split(" ", 3);
                String taskTitle = taskDescArr[0];
                String taskDoneStatus = taskDescArr[1];
                String taskDetails = taskDescArr[2];

                Task taskToAdd = retrieveSpecificTask(taskTitle, taskDetails, taskDoneStatus);
                taskList.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return taskList;
    }

    /**
     * Retrieves a specific type of task based on its title, details, and done status.
     *
     * @param taskTitle The title of the task.
     * @param taskDetails The details of the task.
     * @param taskDoneStatus The done status of the task.
     * @return A Task object corresponding to the provided information.
     * @throws DukeException If the file is corrupted or the task cannot be created.
     */
    public Task retrieveSpecificTask(String taskTitle, String taskDetails, String taskDoneStatus) throws DukeException {
        Task taskToReturn;
        switch(taskTitle) {
        case("[T]"):
            taskToReturn = new Todo(taskDetails);
            break;
        case("[D]"):
            String[] deadlineNameAndDueDate = taskDetails.split("\\(by:", 2);
            String deadlineName = deadlineNameAndDueDate[0];
            String deadlineDueDate = deadlineNameAndDueDate[1];
            taskToReturn = new Deadline(deadlineName.trim(),
            LocalDateTime.parse(deadlineDueDate.split("\\)")[0].trim(), DATE_TIME_FORMATTER));
            break;
        case("[E]"):
            String[] eventNameAndDuration = taskDetails.split("\\(from:", 2);
            String eventName = eventNameAndDuration[0];
            String[] eventDuration = eventNameAndDuration[1].split("to:", 2);
            taskToReturn = new Event(eventName.trim(),
            LocalDateTime.parse(eventDuration[0].trim(), DATE_TIME_FORMATTER),
            LocalDateTime.parse(eventDuration[1].split("\\)")[0].trim(), DATE_TIME_FORMATTER));
            break;
        default:
            throw new DukeException("File is corrupted!");
        }

        if (taskDoneStatus.equals("[X]")) {
            taskToReturn.markDone();
        }

        return taskToReturn;
    }
}
