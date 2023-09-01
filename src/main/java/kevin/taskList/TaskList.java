package kevin.taskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class where Kevin stores the list of Task and the actions done to the tasks based on the command.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor to initialize TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds ToDo task to the taskList.
     * @param isDone This is a boolean to mark whether the ToDo is done or not.
     * @param name This is the name description of the ToDo.
     * @return Returns the new ToDo Object.
     */
    public ToDo addToDo(Boolean isDone, String name) {
        ToDo newToDo = new ToDo(isDone, name);
        this.taskList.add(newToDo);
        return newToDo;
    }

    /**
     * Adds Event task to the taskList.
     * @param isDone This is a boolean to mark whether the Event is done or not.
     * @param name This is the name description of the event.
     * @param startTime This is the start time of the event.
     * @param endTime This is the end time of the event.
     * @return Returns the new Event Object.
     */
    public Event addEvent(Boolean isDone, String name, String startTime, String endTime) {
        Event newEvent = new Event(isDone, name, startTime, endTime);
        this.taskList.add(newEvent);
        return newEvent;
    }

    /**
     * Adds Deadline task to the taskList.
     * @param isDone This is a boolean to mark whether the Deadline is done or not.
     * @param name This is the name description of the deadline.
     * @param deadline This is the deadline date of the Deadline.
     * @return Returns the new Deadline Object.
     */
    public Deadline addDeadline(Boolean isDone, String name, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(isDone, name, deadline);
        this.taskList.add(newDeadline);
        return newDeadline;
    }

    /**
     * Returns the string representation of all the task list.
     */
    public String list() {
        StringBuilder listOutput = new StringBuilder();
        listOutput.append("Here are the tasks in your list: ");

        for (int i = 0; i < this.taskList.size(); i++) {
            listOutput.append("\n\t").
                    append((i + 1)).
                    append(".").
                    append(this.taskList.get(i));
        }

        return listOutput.toString();
    }

    /**
     * Marks the task according the index given to be done.
     * @param toDoIndex This is the index of the task in the TaskList to be updated.
     * @return Returns the updated Task.
     */
    public Task mark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).mark();
        return this.taskList.get(toDoIndex - 1);
    }

    /**
     * Marks the task according the index given to be not done.
     * @param toDoIndex This is the index of the task in the TaskList to be updated.
     * @return Returns the updated Task.
     */
    public Task unmark(int toDoIndex) {
        this.taskList.get(toDoIndex - 1).unmark();
        return this.taskList.get(toDoIndex - 1);
    }

    /**
     * Delete the task according the index given to be done.
     * @param deleteIndex This is the index of the task in the TaskList to be deleted.
     * @return Returns the deleted Task.
     */
    public Task delete(int deleteIndex) {
        Task deletedTask = this.taskList.get(deleteIndex - 1);
        this.taskList.remove(deleteIndex - 1);
        return deletedTask;
    }

    /**
     * Returns the current size of the taskList.
     */
    public int size() {
        return this.taskList.size();
    }


    /**
     * {@inheritDoc}
     * @return Returns a string representation of the TaskList object.
     */
    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
