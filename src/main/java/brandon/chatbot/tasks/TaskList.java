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

    public TaskList findTask(String title) {
        TaskList newList = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(title)) {
                newList.addTask(task);
            }
        }

        return newList;
    }

    public boolean isEmpty() {
        return tasks.isEmpty() || tasks == null;
    }

    public TaskList appendTaskList(TaskList targetTaskList) {
        this.tasks.addAll(targetTaskList.getList());
        return this;
    }

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

