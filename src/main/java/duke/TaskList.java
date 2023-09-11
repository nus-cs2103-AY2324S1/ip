package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to encapsulate the list of tasks
 */
public class TaskList {
    public static final int TODO_WITH_TAGS_LENGTH = 4;
    public static final int DEADLINE_WITH_TAGS_LENGTH = 5;
    public static final int EVENT_WITH_TAGS_LENGTH = 6;
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     * @param filePath  the path of the file
     */
    public TaskList(String filePath) {
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
                // We need to return a task after parsing, if it is null, it means that the task is corrupted
                assert task != null : "Task is null";
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
        // The assumption is that the stored task has a task type, task status and task name minimally
        assert splitString.length >= 3 : "Stored task is invalid / corrupted";
        String taskType = splitString[0];
        boolean taskStatus = Boolean.parseBoolean(splitString[1]);
        String taskName = splitString[2];
        boolean hasTags = TaskList.hasTags(taskType, splitString);

        switch (taskType) {
        case "T":
            Task toDo = new ToDos(taskName, taskStatus);
            if (!hasTags) {
                return toDo;
            } else if (hasEmptyTag(splitString[TODO_WITH_TAGS_LENGTH - 1])) {
                return toDo;
            }
            String tags = splitString[3];
            toDo.loadTags(tags);
            return toDo;
        case "D":
            String deadline = splitString[3];
            DateTime deadlineDateTime = DateTime.createDateTimeFromStorage(deadline);
            if (deadlineDateTime == null) {
                throw new WrongInputException("Stored deadline is invalid / corrupted",
                        "Please clear the folder and restart the program");
            }
            Task deadlineTask = new Deadline(taskName, taskStatus, deadlineDateTime);
            if (!hasTags) {
                return deadlineTask;
            } else if (hasEmptyTag(splitString[DEADLINE_WITH_TAGS_LENGTH - 1])) {
                return deadlineTask;
            }
            String tagsDeadline = splitString[4];
            deadlineTask.loadTags(tagsDeadline);
            return deadlineTask;
        case "E":
            String to = splitString[3];
            String from = splitString[4];
            DateTime fromDateTime = DateTime.createDateTimeFromStorage(from);
            DateTime toDateTime = DateTime.createDateTimeFromStorage(to);
            if (fromDateTime == null || toDateTime == null) {
                throw new WrongInputException("Stored event is invalid / corrupted",
                        "Please clear the folder and restart the program");
            }
            Task event = new Event(taskName, taskStatus, fromDateTime, toDateTime);
            if (!hasTags) {
                return event;
            } else if (hasEmptyTag(splitString[EVENT_WITH_TAGS_LENGTH - 1])) {
                return event;
            }
            String tagsEvent = splitString[5];
            event.loadTags(tagsEvent);
            return event;
        default:
            // If it has reach the default statement, the taskType is not valid, program should be stopped
            assert false : "Invalid task type";
        }
        return null;
    }

    /**
     * Checks whether a task has tags
     * @param taskType  the type of the task
     * @param splitString  the string that has been split by the delimiter
     * @return
     */
    public static boolean hasTags(String taskType, String[] splitString) {
        switch (taskType) {
        case "T":
            return splitString.length == TODO_WITH_TAGS_LENGTH;
        case "D":
            return splitString.length == DEADLINE_WITH_TAGS_LENGTH;
        case "E":
            return splitString.length == EVENT_WITH_TAGS_LENGTH;
        default:
            // If it has reach the default statement, the taskType is not valid, program should be stopped
            assert false : "Invalid task type";
        }
        return false;
    }

    public static boolean hasEmptyTag(String tag) {
        return tag.trim().isEmpty();
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
     * @param storage  the storage object that is used to write to the file
     */
    private void generateNewTaskList(Storage storage) {
        storage.clearFile();
        for (Task task : this.taskList) {
            storage.writeToFile(task.convertToSaveFormat());
        }
    }

    /**
     * Removes a tag from a task in the taskList
     * @param index the index of the task in the taskList
     * @param tag  the tag to be removed
     * @param storage  the storage object that is used to write to the file
     */
    public void removeTag(int index, String tag, Storage storage) {
        Task task = this.taskList.get(index);
        task.removeTag(tag);
        task.printTags(index + 1);
        this.generateNewTaskList(storage);
    }

    /**
     * Returns the taskList
     * @param index the index of the task in the taskList
     * @param tag the tag to be added
     * @param storage  the storage object that is used to write to the file
     */
    public void updateTags(int index, String tag, Storage storage) {
        Task task = this.taskList.get(index);
        task.addTag(tag);
        task.printTags(index + 1);
        this.generateNewTaskList(storage);
    }

    /**
     *  Returns the length of the taskList
     *  @return the length of the taskList
     */
    public int length() {
        return this.taskList.size();
    }

}
