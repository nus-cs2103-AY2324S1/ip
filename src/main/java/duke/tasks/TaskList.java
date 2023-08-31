package duke.tasks;

import duke.storage.Storage;
import duke.exception.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private static final String INDENTATION = "    ";
    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public String formatList() {
        String str = "";
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            str = str + (i + 1) + ". " + taskList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    public String handleMarking(String commandNum, String status) throws DukeException {

        try {
            int index = Integer.parseInt(commandNum) - 1;
            String buffer = "";

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid duke.tasks.Task Index provided!");
            }
            Task selectedTask = taskList.get(index);
            if (status.equals("mark")) {
                selectedTask.markTask();
                buffer = "Nice! I've marked the task as done:\n   " +
                        INDENTATION + selectedTask;
            } else if (status.equals("unmark")) {
                selectedTask.unmarkTask();
                buffer = "OK, I've marked this task as not done yet:\n   " +
                        INDENTATION + selectedTask;
            }
            this.saveList();
            return buffer;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.tasks.Task Index provided!");
        }
    }

    public String handleDelete(String commandNum) throws DukeException {
        try {
            int index = Integer.parseInt(commandNum) - 1;

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid duke.tasks.Task Index provided!");
            }
            Task selectedTask = taskList.remove(index);
            this.saveList();
            return "Noted. I've removed this task:\n   " +
                    INDENTATION + selectedTask + "\n " + INDENTATION + "Now you have " +
                    taskList.size() + " tasks in the list.";

        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.tasks.Task Index provided!");
        }
    }

    public String handleToDo(String task) {
        ToDo item = new ToDo(task);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
    }

    public String handleDeadline(String desc, String by) throws DukeException {

        Deadline item = new Deadline(desc, by);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
    }

    public String handleEvent(String desc, String start, String end) throws DukeException {
        Event item = new Event(desc, start, end);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
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

    public String exportList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.exportData()).append("\n");
        }
        return sb.toString();
    }

    public void saveList() {
        String data = this.exportList();
        storage.saveData(data);
    }


}
