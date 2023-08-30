import java.util.ArrayList;

/**
 * Contains the list of tasks. The tasks stored from the file loaded
 * and from inputs given by user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with a empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with data load from the file.
     *
     * @param taskList The task list stored in the file.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + "." + taskList.get(i).toString();

            if (i != taskList.size() - 1) {
                tasks += "\n";
            }
        }

        return tasks;
    }

    /**
     * Returns a string of tasks to be written into the file. Formats the data stored in TaskList
     * to be written into file.
     *
     * @return The string representing TaskList to be written into file.
     */
    public String toFileString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += taskList.get(i).fileString() + "\n";
        }
        return tasks;
    }

    /**
     * Adds a new task into TaskList. The task can either be a ToDo,
     * Deadline or Event.
     *
     * @param taskType The type of task to add.
     * @param args Additional information for task to be instantiated.
     * @return A string description of the task.
     */
    public String addTask(String taskType, String[] args) {
        switch (taskType) {
        case "todo":
            taskList.add(new ToDo(args[0]));
            break;

        case "deadline":
            taskList.add(new Deadline(args[0], args[1]));
            break;

        case "event":
            taskList.add(new Event(args[0], args[1], args[2]));
            break;

        }

        return "Got it. I've added this task:\n" +
                taskList.get(taskList.size() - 1).toString() +
                "\nNow you have " + (taskList.size()) + " tasks in the list.";
    }

    /**
     * Edits a task in the TaskList. Task can be marked, unmarked as done or deleted.
     *
     * @param taskType The type of edits to be made.
     * @param ind The task to edit.
     * @return The string description of the edit being made.
     */
    public String editTask(String taskType, int ind) {

        String editDesc = "";

        switch (taskType) {
        case "mark":
            taskList.get(ind - 1).markTask();
            editDesc += "Nice! I've marked this task as done:\n" + taskList.get(ind - 1).toString();
            break;

        case "unmark":
            taskList.get(ind - 1).unmarkTask();
            editDesc += "OK, I've marked this task as not done yet:\n" + taskList.get(ind - 1).toString();
            break;

        case "delete":
            editDesc += "Noted. I've removed this task:\n" + taskList.get(ind - 1).toString();
            taskList.remove(ind - 1);
            editDesc += "\nNow you have " + taskList.size() + " tasks in the list.";
            break;
        }

        return editDesc;
    }
}
