package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import duke.TaskType;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.util.DateAndTimeHandler;

/**
 * Class to handle storage of tasks
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    /**
     * Constructor
     */
    public Storage() {
        this.filePath = "src/data/duke.txt";
        tasks = new ArrayList<>();
    }

    /**
     * Reads tasks from a file and processes them based on their types.
     * The tasks are parsed and passed to respective handler methods.
     * Handles Todo, duke.tasks.Deadline, and duke.tasks.Event tasks stored in the file.
     */
    public ArrayList<Task> handleReadAllTasksFromFile() throws FileNotFoundException {

        //Check if file exists
        try {
            new File(filePath);
        } catch (Exception e) {
            System.out.println("SUI, Please create a folder at the specified folder : src/data/duke.txt");
        }
        try {
            File obj = new File(filePath);
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {
                String taskInFile = reader.nextLine();
                convertTaskToList(taskInFile);
            }
            reader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("SUI, Please create a folder at the specified folder : src/data/duke.txt");
        }
        return tasks;
    }

    /**
     * Writes changes in the task list to the file.
     * Each task is written as a formatted string in the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void handleChangesInFile(ArrayList<Task> list) throws IOException {
        try {
            Writer writer = new FileWriter(filePath, false);
            for (int i = 0; i < list.size(); i++) {
                writer.append("" + (i + 1)).append(".").append(list.get(i).toString()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Converts tasks read from a file to a list of tasks and processes each task.
     * Reads task strings from the provided input, parses them, and handles them based on their types.
     *
     * @param taskFromFile The string containing tasks read from a file.
     * @throws DukeException If there is an error while processing a task.
     * @throws IOException   If there is an I/O error during processing.
     */
    public void convertTaskToList(String taskFromFile) {
        String taskType = taskFromFile.substring(3, 4);
        String taskDescription = taskFromFile.substring(9);
        switch (taskType) {
        case "T":
            handleTodoTaskInFile(taskDescription);
            break;
        case "D":
            handleDeadlineTaskInFile(taskDescription);
            break;
        case "E":
            handleEventTaskInFile(taskDescription);
            break;
        default:
            break;
        }
    }
    /**
     * Adds a todo task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description.
     */
    public void handleTodoTaskInFile(String input) {
        String task = "";
        String[] parts = input.split(" ");

        for (int i = 1; i < parts.length; i++) {
            task += parts[i] + " ";
        }

        tasks.add(new ToDo(task, TaskType.TODO));
    }
    /**
     * Method to handle deadline task from file
     *
     * @param input user input
     * @throws DukeException
     */
    public void handleDeadlineTaskInFile(String input) {
        StringBuilder task = new StringBuilder();
        StringBuilder byDate = new StringBuilder();
        String endTime = "";
        String [] parts = input.split("\\(by: ");
        String[] taskArray = parts[0].split(" ");
        String[] deadlineInfo = parts[1].split(" ");

        for (int i = 1; i < taskArray.length; i++) {
            task.append(taskArray[i]).append(" ");
        }

        for (int i = 0; i < 3; i++) {
            byDate.append(deadlineInfo[i]).append(" ");
        }
        endTime = deadlineInfo[3];
        endTime = endTime.substring(0, endTime.length() - 1);
        byDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(byDate.substring(0, 11), "MMM dd yyyy", "yyyy-MM-dd"));
        tasks.add(new Deadline(task.toString(), byDate.toString(), endTime + ":00", TaskType.DEADLINE));
    }

    /**
     * Method to handle event task from file
     * @param input user input
     * @throws DukeException
     */
    public void handleEventTaskInFile(String input) {
        StringBuilder task = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        String startTime = "";
        String endTime = "";
        String[] parts = input.split("\\(from: ");
        String[] taskArray = parts[0].split(" ");
        String[] taskInfo = parts[1].split("to: ");

        String[] fromInfo = taskInfo[0].split(" ");
        String[] toInfo = taskInfo[1].split(" ");

        for (int i = 1; i < taskArray.length; i++) {
            task.append(taskArray[i]).append(" ");
        }

        for (int i = 0; i < 3; i++) {
            startDate.append(fromInfo[i]).append(" ");
        }

        for (int i = 0; i < 3; i++) {
            endDate.append(toInfo[i]).append(" ");
        }

        startDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(startDate.substring(0, 11), "MMM dd yyyy", "yyyy-MM-dd"));
        endDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(endDate.substring(0, 11), "MMM dd yyyy", "yyyy-MM-dd"));
        startTime = fromInfo[3];
        endTime = toInfo[3];
        endTime = endTime.substring(0, endTime.length() - 1);
        tasks.add(new Event(task.toString(), startDate.toString(), endDate.toString(), startTime + ":00", endTime + ":00", TaskType.EVENT));
    }
}
