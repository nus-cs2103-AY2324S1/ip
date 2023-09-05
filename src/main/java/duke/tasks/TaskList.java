package duke.tasks;

import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    /**
     * The list of tasks
     */
    private final ArrayList<Task> list;

    /**
     * Constructor for TaskList
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructor for TaskList
     */
    public void add(Task newTask) {
        this.list.add(newTask);
    }

    /**
     * Marks the task as done
     *
     * @param index the index of the task
     */
    public void mark(int index) {
        this.list.get(index).mark();
    }

    /**
     * Unmarks the task as done
     *
     * @param index the index of the task
     */
    public void unmark(int index) {
        this.list.get(index).unmark();
    }

    /**
     * Prints the list of tasks
     */
    public String showList() {
        String output = "";
        for (int i = 0; i < this.list.size(); i++) {
            output += (i + 1) + "." + this.list.get(i).toString() + "\n";
        }
        return output;
    }


    /**
     * Marks the task with the given index.
     *
     * @param index  Index of the task to be marked.
     * @param isMark Mark if true, Unmark if false.
     * @throws DukeException Exception thrown upon invalid index.
     */
    public void changeStatus(int index, boolean isMark) throws DukeException {
        if (index >= this.length()) {
            throw new DukeException("I'm unable to perform the mark/unmark operation due to an invalid index!");
        }
        if (this.list.get(index).isCompleted() == isMark) {
            throw new DukeException("I'm unable to perform the mark/unmark operation because the task"
                    + " is already marked/unmarked!");
        }
        this.list.get(index).completeTask(isMark);
    }

    /**
     * Deletes the task
     *
     * @param index the index of the task
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    /**
     * Returns the size of the list
     *
     * @return the size of the list
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Returns the task
     *
     * @param index the index of the task
     * @return the task
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns all the tasks
     *
     * @return all the tasks
     */
    public ArrayList<Task> getAllTasks() {
        return this.list;
    }

    /**
     * Returns the filtered tasks
     *
     * @param keyword the keyword to filter
     * @return the filtered tasks
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }
}