package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to encapsulate the list of tasks
 */
public class TaskList {
    private File textFile;
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     * @param filePath
     */
    public TaskList(String filePath) {
        this.textFile = textFile;
        this.createTaskList(filePath);
    }

    /**
     * Creates a list of tasks from the file
     * @param filePath
     */
    private void createTaskList(String filePath) {
        try {
            this.taskList = new ArrayList<>();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = TaskList.parseTask(data);
                this.taskList.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, unable to create list of tasks");
        } catch (WrongInputException e) {
            System.out.println("Unable to create task from storage" + e.toString());
        }
    }

    /**
     * Parses a task from a string stored in a certain format
     * @param storedTextLine the string representing a line of text in the file to be parsed
     * @return a Task object of the right task type
     */
    public static Task parseTask(String storedTextLine) throws WrongInputException {
        String[] splitString = storedTextLine.split(" \\| ");
        String taskType = splitString[0];
        boolean taskStatus = Boolean.parseBoolean(splitString[1]);
        String taskName = splitString[2];

        switch (taskType) {
        case "T":
            return new ToDos(taskName, taskStatus);
        case "D":
            String deadline = splitString[3];
            DateTime deadlineDateTime = DateTime.createDateTimeFromStorage(deadline);
            if (deadlineDateTime == null) {
                throw new WrongInputException("Stored deadline is invalid / corrupted",
                        "Please clear the folder and restart the program");
            }
            return new Deadline(taskName, taskStatus, deadlineDateTime);
        case "E":
            String to = splitString[3];
            String from = splitString[4];
            DateTime fromDateTime = DateTime.createDateTimeFromStorage(from);
            DateTime toDateTime = DateTime.createDateTimeFromStorage(to);
            if (fromDateTime == null || toDateTime == null) {
                throw new WrongInputException("Stored event is invalid / corrupted",
                        "Please clear the folder and restart the program");
            }
            return new Event(taskName, taskStatus, fromDateTime, toDateTime);
        default:
            return null;
        }
    }

    /**
     * Adds a task to the taskList without saving it
     * @param task  the task to be added
     */
    public void addTaskQuietly(Task task) {
        this.taskList.add(task);
    };

    /**
     * Adds a task to the taskList
     * @param task  the task to be added
     * @param totalItemCount the total number of the tasks
     */
    public void addTask(Task task, int totalItemCount, Storage storage) {
        this.taskList.add(task);
        task.taskAdded(totalItemCount);
        storage.writeToFile(task.convertToSaveFormat());
    }

    /**
     * Deletes a task in a taskList given the index
     * @param index the index of the task to be deleted
     * @param totalItemCount the total number of the tasks
     */
    public void deleteTask(int index, int totalItemCount, Storage storage) {
        Task task = this.taskList.get(index);
        this.taskList.remove(index);
        task.taskDeleted(totalItemCount);
        this.generateNewTaskList(storage);
    }

    /**
     * Returns the task at a particular index
     * @param index index of the task in the taskList
     * @return  the toString of the task
     */
    public String getTask(int index) {
        return this.taskList.get(index).toString();
    }

    /**
     *  Returns the task object at a particular index
     *  @param index index of the task in the taskList
     *  @return  the task object
     */
    public Task getTaskObject(int index) {
        return this.taskList.get(index);
    }

    /**
     * Marks a task as done given the index in the task list
     * @param index the index of the task in the taskList
     */
    public void markTask(int index, Storage storage) {
        this.taskList.get(index).completeTask();
        this.generateNewTaskList(storage);
    }

    /**
     * Unmarks a task as done given the index in the task list
     * @param index the index of the task in the taskList
     */
    public void unmarkTask(int index, Storage storage) {
        this.taskList.get(index).undoTask();
        this.generateNewTaskList(storage);
    }

    /**
     * Generates a new task list after a task has been added or deleted
     * @param storage   the storage object that is used to write to the file
     */
    private void generateNewTaskList(Storage storage) {
        storage.clearFile();
        for (Task task : this.taskList) {
            storage.writeToFile(task.convertToSaveFormat());
        }
    }

    /**
     *  Returns the length of the taskList
     *  @return the length of the taskList
     */
    public int length() {
        return this.taskList.size();
    }




}
