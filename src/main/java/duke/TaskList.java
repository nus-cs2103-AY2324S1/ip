package duke;

import java.util.ArrayList;

/**
 * Represents a task list.
 * Contains all current tasks and contain methods to add/delete/mark/unmark a specific task.
 */
public class TaskList {
    protected static ArrayList<Task> toDo;

    /**
     * Constructs a new <code>TaskList</code> object.
     */
    public TaskList() {
        toDo = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list from user input.
     * @param task the task to add into task list.
     * @param type type of task added.
     */
    public static void add(Task task, String type) {
        toDo.add(task);
        String description = task.getDescription();
        Ui.successfulAdd(type, description, toDo.size());
        Storage.save();
    }

    /**
     * Adds a new task to the task list from the hard disk when chatbot starts up.
     * @param task the task from the file in the hard disk to be loaded into the task list.
     */
    public static void add(Task task) {
        toDo.add(task);
    }

    /**
     * Deletes a specific task from the task list.
     * @param target the index of the task in the task list that should be deleted.
     * @throws DukeException throws exception when the index of the task given is larger than the size of the task list.
     */
    public static void delete(Integer target) throws DukeException {
        if (target <= toDo.size()) {
            Task toDelete = toDo.get(target - 1);
            String description = toDelete.getDescription();
            toDo.remove(target - 1);
            Ui.successfulDelete(description, toDo.size());
            Storage.save();
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Marks the specified task.
     * @param task_no the index of the task in the task list to be marked.
     * @throws DukeException exception thrown when the <code>task_no</code> given exceeds the size of the task list.
     */
    public static void mark(Integer task_no) throws DukeException {
        if (task_no <= toDo.size()) {
            Task target = toDo.get(task_no - 1);
            target.mark();
            String description = target.getDescription();
            Ui.markMsg(description);
            Storage.save();
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Unmarks the specified task.
     * @param taskNo the index of the task in the task list to be unmarked.
     * @throws DukeException exception thrown when the <code>taskNo</code> given exceeds the size of the task list.
     */
    public static void unmark(Integer taskNo) throws DukeException {
        if (taskNo <= toDo.size()) {
            Task target = toDo.get(taskNo - 1);
            target.unmark();
            String description = target.getDescription();
            Ui.unmarkMsg(description);
            Storage.save();
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Displays all the tasks currently in the task list.
     */
    public static void listOut() {
        for (int i = 0; i < toDo.size(); i++) {
            Task currTask = toDo.get(i);
            String description = currTask.getDescription();
            System.out.println("        " + (i + 1) + "." + description);
        }
    }

}
