package duke.tasks;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;


/**
 * Represents a Task List to store User's Tasks.
 */
public class TaskList {

    /**
     * The Message to display when Invalid Index is given.
     */
    private static final String INVALID_INDEX_MESSAGE = "Invalid Task Index provided!";

    /**
     * The ArrayList to represent the Task List.
     */
    private ArrayList<Task> taskList;

    /**
     * The Storage for the Chatbot.
     */
    private Storage storage;


    /**
     * The Constructor for the TaskList.
     *
     * @param taskList The ArrayList to initialise as the TaskList.
     * @param storage  The Storage of the Chatbot.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage) {
        assert taskList != null: "TaskList should be initialised!";
        assert storage != null: "Storage should be initialised!";
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Formats the Task List to output.
     *
     * @return The formatted task list as a String.
     */
    public String formatList() {
        String str = "";
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            str = str + (i + 1) + ". " + taskList.get(i);
            if (i != (len - 1)) {
                str += "\n";
            }
        }
        return str;
    }

    /**
     * Handles the Marking of the Tasks in the Task List.
     *
     * @param commandNum The Index of the Task as a String.
     * @param status     The status of the marking as either 'mark' or 'unmark'.
     * @return The Success output as a String.
     * @throws DukeException If Task Index provided is invalid.
     */
    public String handleMarking(String commandNum, String status) throws DukeException {

        try {
            int index = Integer.parseInt(commandNum) - 1;
            String buffer = "";

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException(INVALID_INDEX_MESSAGE);
            }
            Task selectedTask = taskList.get(index);
            if (status.equals("mark")) {
                selectedTask.markTask();
                buffer = "Nice! I've marked the task as done:\n" + selectedTask;
            } else if (status.equals("unmark")) {
                selectedTask.unmarkTask();
                buffer = "OK, I've marked this task as not done yet:\n" + selectedTask;
            }
            this.saveList();
            return buffer;
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Formats the return Message for deleting and adding Tasks.
     *
     * @param status The action type of the Message.
     * @param task The task to be added or removed.
     * @param size The size of the taskList.
     * @return The formatted String Message.
     */
    private String formatMessage(String status, Task task, Integer size) {
        return "Noted. I've " + status + " this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Handles the Deleting of a Task from the Task List.
     *
     * @param commandNum The Index of the Task as a String.
     * @return The Success output as a String.
     * @throws DukeException If Task Index provided is invalid.
     */
    public String handleDelete(String commandNum) throws DukeException {
        try {
            int index = Integer.parseInt(commandNum) - 1;

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException(INVALID_INDEX_MESSAGE);
            }
            Task selectedTask = taskList.remove(index);
            this.saveList();
            return formatMessage("removed", selectedTask, taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Handles the Adding of a ToDo Task to the Task List.
     *
     * @param task The Description of the Task.
     * @return The Success output as a String.
     */
    public String handleToDo(String task) {
        ToDo item = new ToDo(task);
        taskList.add(item);
        this.saveList();
        return formatMessage("added", item, taskList.size());
    }

    /**
     * Handles the Adding of a Deadline Task to the Task List.
     *
     * @param desc The Description of the Task.
     * @param by   The deadline of the Task.
     * @return The Success output as a String.
     * @throws DukeException if there is an Error in creating the Deadline Task.
     */
    public String handleDeadline(String desc, String by) throws DukeException {

        Deadline item = new Deadline(desc, by);
        taskList.add(item);
        this.saveList();
        return formatMessage("added", item, taskList.size());
    }

    /**
     * Handles the Adding of an Event Task to the Task List.
     *
     * @param desc  The Description of the Task.
     * @param start The start time of the Task.
     * @param end   The end time of the Task.
     * @return The Success output as a String.
     * @throws DukeException if there is an Error in creating the Event Task.
     */
    public String handleEvent(String desc, String start, String end) throws DukeException {
        Event item = new Event(desc, start, end);
        taskList.add(item);
        this.saveList();
        return formatMessage("added", item, taskList.size());
    }

    /**
     * Finds the tasks containing the query and returns a new Task List.
     *
     * @param query The query to find the tasks.
     * @return The new Task List containing the tasks that contain the query.
     */
    public TaskList findTasks(String query) {
        ArrayList<Task> buffer = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(query)) {
                buffer.add(task);
            }
        }
        return new TaskList(buffer, storage);
    }

    /**
     * Edits the selected task.
     *
     * @param strIndex The Index of the Task in the Task List.
     * @param editType The type of Edit to be made. ("/desc", "/by", "/from", "/to")
     * @param editData The new data.
     * @return The Success output as a String.
     * @throws DukeException If Task Index provided is invalid or command parameters are invalid.
     */
    public String editTask(String strIndex, String editType, String editData) throws DukeException {
        Task selectedTask;
        try {
            int index = Integer.parseInt(strIndex) - 1;
            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException(INVALID_INDEX_MESSAGE);
            }
            selectedTask = taskList.get(index);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_MESSAGE);
        }

        if (selectedTask == null) {
            throw new DukeException("No Task Selected!");
        }

        if (editType.equals("/desc")) {
            selectedTask.updateDesc(editData);
        } else if (editType.equals("/by") && selectedTask instanceof Deadline) {
            ((Deadline) selectedTask).updateDeadline(editData);
        } else if (editType.equals("/from") && selectedTask instanceof Event) {
            ((Event) selectedTask).updateStart(editData);
        } else if (editType.equals("/to") && selectedTask instanceof Event) {
            ((Event) selectedTask).updateEnd(editData);
        } else {
            throw new DukeException("Invalid Edit Command!\nMake sure that the Task is of the correct type!");
        }

        return formatMessage("updated", selectedTask, taskList.size());
    }

    /**
     * Formats the Task List to be saved to Storage.
     *
     * @return The formatted List as a String.
     */
    public String exportList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.exportData()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Saves the Task List to the Storage.
     */
    public void saveList() {
        String data = this.exportList();
        storage.saveData(data);
    }


}
