package duke.tasklist;

import duke.data.exception.CCException;
import duke.data.task.Task;
import duke.data.task.ToDo;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The TaskList class represents a collection of tasks managed by the ChatterChicken task manager.
 * It allows for adding, marking, unmarking, and deleting tasks, as well as printing the list of tasks
 * along with their respective indexes. Tasks can be of different types: ToDo, Deadline, and Event.
 */
public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> taskList;
    private final Ui ui;
    private int markedCount;
    private int todoCount;
    private int deadlineCount;
    private int eventCount;

    /**
     * Constructs a new TaskList with the provided list of tasks and a UI component for user interactions.
     *
     * @param taskList The initial list of tasks.
     * @param ui The user interface component for displaying messages and user interactions.
     */
    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
        this.markedCount = 0;
        this.todoCount = 0;
        this.deadlineCount = 0;
        this.eventCount = 0;
    }

    /**
     * Adds a new task to the task list based on the provided Task object.
     * Displays a confirmation message with the added task's details.
     *
     * @param task The task to be added to the task list.
     * @throws CCException If there is an error in adding the task.
     */
    public String addTask(Task task) {
        assert task != null : "Task should not be null"; // Check that the task is not null
        taskList.add(task);
        if (task instanceof ToDo) {
            todoCount++;
        } else if (task instanceof Deadline) {
            deadlineCount++;
        } else {
            eventCount++;
        }
        return ui.displayAddTask(task, taskList.size());
    }

    /**
     * Marks a task as done based on the provided input.
     * Displays a confirmation message with the marked task's details.
     *
     * @param input The input containing task information to mark as done.
     * @throws CCException If there is an error in marking the task or if the input is invalid.
     */
    public String markTask(String input) throws CCException {
        assert input != null : "Input should not be null"; // Check that the input is not null
        assert !input.isEmpty() : "Input should not be empty"; // Check that the input is not empty

        try {
            Task task = taskList.get(getIndex(input));
            assert task != null : "Task should not be null"; // Check that the task is not null
            task.setDone(true);
            markedCount++;
            return ui.displayMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for marking list of length " + taskList.size());
        }
    }

    /**
     * Unmarks a previously marked task as not done based on the provided input.
     * Displays a confirmation message with the unmarked task's details.
     *
     * @param input The input containing task information to unmark.
     * @throws CCException If there is an error in unmarking the task or if the input is invalid.
     */
    public String unmarkTask(String input) throws CCException {
        assert input != null : "Input should not be null"; // Check that the input is not null
        assert !input.isEmpty() : "Input should not be empty"; // Check that the input is not empty

        try {
            Task task = taskList.get(getIndex(input));
            assert task != null : "Task should not be null"; // Check that the task is not null
            task.setDone(false);
            markedCount--;
            return ui.displayUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Deletes a task from the task list based on the provided input.
     * Displays a confirmation message after deleting the task.
     *
     * @param input The input containing task information to be deleted.
     * @throws CCException If there is an error in deleting the task or if the input is invalid.
     */
    public String deleteTask(String input) throws CCException {
        assert input != null : "Input should not be null"; // Check that the input is not null
        try {
            int index = getIndex(input);
            Task task = taskList.get(index);
            assert task != null : "Task should not be null"; // Check that the task is not null
            taskList.remove(index);
            return ui.displayDeleteTask(task, taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param input The keyword or search query used to find matching tasks.
     * @return A string containing a list of tasks that match the search query.
     */
    public String find(String input) {
        assert input != null : "Input should not be null"; // Check that the input is not null
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if(task.getTaskDescription().contains(input)) {
                matchingTasks.add(task);
            }
        }
        return ui.displayMatchingTasks(matchingTasks);
    }

    public String countTasks(String input) {
        String response = "";
        switch (input) {
        case "completed":
            response = countCompletedTasks();
            break;
        case "uncompleted":
            response = countUncompletedTasks();
            break;
        case "type":
            response = countTaskType();
        }
        return response;
    }

    private String countCompletedTasks() {
        return ui.displayCompletedTaskCount(markedCount, taskList.size());
    }

    private String countUncompletedTasks() {
        return ui.displayUncompletedTaskCount(taskList.size() - markedCount, taskList.size());
    }

    private String countTaskType() {
        return ui.displayTaskTypeCount(todoCount, deadlineCount, eventCount);
    }

    /**
     * Retrieves the index of a task based on the provided input.
     *
     * @param input The input containing task information and index as the last character.
     * @return The index of the task parsed from the input.
     */
    private int getIndex(String input) {
        return input.charAt(input.length() - 1) - '0' - 1;
    }

    /**
     * Prints the list of tasks with their respective indexes.
     */
    public String printList() {
        return ui.displayList(taskList);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}