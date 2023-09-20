package brandon.chatbot.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

import brandon.chatbot.common.DukeIndexOutOfBoundsException;
import brandon.chatbot.tag.Tag;



/**
 * Represents a list of tasks to be stored by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes tasks arraylist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to the task list.
     *
     * @param newTask to add to the task list.
     */
    public TaskList addTask(Task newTask) {
        tasks.add(newTask);
        return this;
    }

    /**
     * Deletes task from the task list.
     *
     * @param index of the task to be deleted in the task list.
     * @throws DukeIndexOutOfBoundsException if the index is bigger than the size of the task.
     */
    public void deleteTask(int index) throws DukeIndexOutOfBoundsException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        tasks.remove(index - 1);
    }

    /**
     * Sets the task in the task list as done.
     *
     * @param index of the task in the task list to be set done
     * @throws DukeIndexOutOfBoundsException if the index is bigger than the size of the task.
     */
    public void markAsDone(int index) throws DukeIndexOutOfBoundsException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }

        Task target = tasks.get(index - 1);
        target.setDone(true);
    }

    /**
     * Unmarks the task at a specified index in the task list.
     *
     * @param index of the tast in the task list.
     * @throws DukeIndexOutOfBoundsException if the index is bigger than the size of the array.
     */
    public void unmark(int index) throws DukeIndexOutOfBoundsException {
        if (tasks.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task target = tasks.get(index - 1);
        target.setDone(false);
    }

    /**
     * Finds task in the task list with a given title parameter.
     * @param title is the title of the task that user is searching for.
     * @return new TaskList that contains the tasks with the given title.
     */
    public TaskList findTask(String title) {
        TaskList newList = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(title)) {
                newList.addTask(task);
            }
        }

        return newList;
    }

    /**
     * Checks if the TaskList tasks is empty or not.
     * @return true if the tasks TaskList is empty and false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty() || tasks == null;
    }

    /**
     * Appends a TaskList to another TaskList.
     * @param targetTaskList is the target TaskList object that are being added to the current TaskList object.
     * @return this object with the newly appended tasks values.
     */
    public TaskList appendTaskList(TaskList targetTaskList) {
        this.tasks.addAll(targetTaskList.getList());
        return this;
    }

    /**
     * Filters the tasks TaskList with the given tag value.
     * @param tag is the tag value that the user searches for.
     * @return this TaskList object with the filtered values.
     */
    public TaskList filterTaskWithTag(Tag tag) {
        tasks = (ArrayList<Task>) tasks.stream()
                .filter(task -> {
                    return task.hasTag(tag);
                })
                .collect(Collectors.toList());
        return this;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

}

