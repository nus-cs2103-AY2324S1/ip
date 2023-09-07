package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static String add(Task task, String type) {
        toDo.add(task);
        Storage.save();
        String description = task.getDescription();
        String successMsg = Ui.successfulAdd(type, description, toDo.size());
        return successMsg;
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
    public static String delete(Integer target) throws DukeException {
        if (target <= toDo.size()) {
            Task toDelete = toDo.get(target - 1);
            String description = toDelete.getDescription();
            toDo.remove(target - 1);
            Storage.save();
            String successMsg = Ui.successfulDelete(description, toDo.size());
            return successMsg;
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Marks the specified task.
     * @param taskNo the index of the task in the task list to be marked.
     * @throws DukeException exception thrown when the <code>taskNo</code> given exceeds the size of the task list.
     */
    public static String mark(Integer taskNo) throws DukeException {
        if (taskNo <= toDo.size()) {
            Task target = toDo.get(taskNo - 1);
            target.mark();
            String description = target.getDescription();
            String successMsg = Ui.markMsg(description);
            Storage.save();
            return successMsg;
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Unmarks the specified task.
     * @param taskNo the index of the task in the task list to be unmarked.
     * @throws DukeException exception thrown when the <code>taskNo</code> given exceeds the size of the task list.
     */
    public static String unmark(Integer taskNo) throws DukeException {
        if (taskNo <= toDo.size()) {
            Task target = toDo.get(taskNo - 1);
            target.unmark();
            String description = target.getDescription();
            String successMsg = Ui.unmarkMsg(description);
            Storage.save();
            return successMsg;
        } else {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        }
    }

    /**
     * Displays all the tasks currently in the task list.
     */
    public static String listOut() {
        String tasks = "";
        for (int i = 0; i < toDo.size(); i++) {
            Task currTask = toDo.get(i);
            String description = currTask.getDescription();
            tasks += "        " + (i + 1) + "." + description;
        }
        return tasks;
    }

    /**
     * Searches for a task in the task list.
     * @param searchTerm the term to search for in the task list.
     */
    public static String searchFor(String searchTerm) {
        String searchResult = "        ____________________________________________________________"
                + "\n        Here are the matching tasks in your list: (≧▽≦)";
        List<Task> result = toDo.stream()
                .filter(task -> task.checkTerm(searchTerm))
                .collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            Task currTask = result.get(i);
            String description = currTask.getDescription();
            searchResult += "        " + (i + 1) + "." + description;
        }
        return searchResult + "        ____________________________________________________________";
    }
}
