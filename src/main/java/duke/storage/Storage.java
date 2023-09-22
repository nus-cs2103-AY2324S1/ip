package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.ronaldo.TaskType;
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
        this.filePath = "./src/data/duke.txt";
        tasks = new ArrayList<>();
    }

    /**
     * Reads tasks from a file and processes them based on their types.
     * The tasks are parsed and passed to respective handler methods.
     * Handles Todo, duke.tasks.Deadline, and duke.tasks.Event tasks stored in the file.
     */
    public ArrayList<Task> handleReadAllTasksFromFile() throws DukeException {
        //Check if file exists
        try {
            new File(filePath);
        } catch (Exception e) {
            System.out.println("SUI, Create a text file named duke.txt at the specified directory : src/data/duke.txt");
        }
        try {
            File obj = new File(filePath);
            obj.getParentFile().mkdirs();
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {
                String taskInFile = reader.nextLine();
                convertTaskToList(taskInFile);
            }
            reader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("SUI, Create a text file named duke.txt at the specified directory : src/data/duke.txt");
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
    public void convertTaskToList(String taskFromFile) throws DukeException {
        String taskType = taskFromFile.substring(3, 4);
        String taskDescription = taskFromFile.substring(9);
        boolean isCompleted = taskFromFile.charAt(6) == 'X';
        switch (taskType) {
        case "T":
            handleTodoTaskInFile(taskDescription, isCompleted);
            break;
        case "D":
            handleDeadlineTaskInFile(taskDescription, isCompleted);
            break;
        case "E":
            handleEventTaskInFile(taskDescription, isCompleted);
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
    public void handleTodoTaskInFile(String input, boolean isCompleted) throws DukeException {
        tasks.add(new ToDo(input, TaskType.TODO));
        if (isCompleted) {
            tasks.get(tasks.size() - 1).setMarked();
        }
    }
    /**
     * Handle a deadline task from file
     *
     * @param input user input
     * @throws DukeException
     */
    public void handleDeadlineTaskInFile(String input, boolean isCompleted) throws DukeException {
        StringBuilder byDate = new StringBuilder();
        String [] parts = input.split("\\(by: ");
        String task = parts[0];
        String[] deadlineInfo = parts[1].split(" ");

        for (int i = 0; i < 3; i++) {
            byDate.append(deadlineInfo[i]).append(" ");
        }
        String endTime = deadlineInfo[3];
        endTime = endTime.substring(0, endTime.length() - 1);
        byDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(byDate.substring(0, 11),
                "MMM dd yyyy", "yyyy-MM-dd"));
        tasks.add(new Deadline(task, byDate.toString(), endTime + ":00", TaskType.DEADLINE));
        if (isCompleted) {
            tasks.get(tasks.size() - 1).setMarked();
        }
    }
    /**
     * Handles a event task from file
     * @param input user input
     * @throws DukeException
     */
    public void handleEventTaskInFile(String input, boolean isCompleted) throws DukeException {
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();
        String[] parts = input.split("\\(from: ");
        String task = parts[0];
        String[] taskInfo = parts[1].split("to: ");

        String[] fromInfo = taskInfo[0].split(" ");
        String[] toInfo = taskInfo[1].split(" ");

        for (int i = 0; i < 3; i++) {
            startDate.append(fromInfo[i]).append(" ");
        }

        for (int i = 0; i < 3; i++) {
            endDate.append(toInfo[i]).append(" ");
        }

        startDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(startDate.substring(0, 11),
                "MMM dd yyyy", "yyyy-MM-dd"));
        endDate = new StringBuilder(DateAndTimeHandler.convertDateToFormat(endDate.substring(0, 11),
                "MMM dd yyyy", "yyyy-MM-dd"));
        String startTime = fromInfo[3];
        String endTime = toInfo[3];
        endTime = endTime.substring(0, endTime.length() - 1);
        tasks.add(new Event(task.toString(), startDate.toString(), endDate.toString(),
                startTime + ":00", endTime + ":00", TaskType.EVENT));
        if (isCompleted) {
            tasks.get(tasks.size() - 1).setMarked();
        }
    }
}
