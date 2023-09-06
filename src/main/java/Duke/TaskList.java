package Duke;


import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks for the Duke program.
 * It provides methods to manage and manipulate the list of tasks.
 */
public class TaskList {
    ArrayList<SingleTask> taskList;

    /**
     * Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with the given stored list of tasks.
     * @param taskList an ArrayList of SingleTask objects representing the list of tasks
     */
    public TaskList(ArrayList<SingleTask> taskList) {
        this.taskList = taskList;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param TaskNumber the index of the task to delete (1-based indexing)
     * @throws DukeException if the given index is out of bounds
     */
    public String deleteTask(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot delete a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        String s = task.remove();
        this.taskList.remove(TaskNumber - 1);
        return s;
    }

    /**
     * Displays the list of tasks to the user.
     */
    public String list() {
        String s = "";
        if (this.taskList.size() == 0) {
            return "No tasks here ah boy";
        } else {
            s += "Here are your tasks ah boy:\n";
            for (int i = 0; i < this.taskList.size(); i++) {
                SingleTask task = this.taskList.get(i);
                s += (i + 1) + task.listString();
            }
        }
        return s;
    }

    /**
     * Marks a task as done.
     * @param TaskNumber the index of the task to mark as done (1-based indexing)
     * @throws DukeException if the given index is out of bounds
     */
    public String mark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot mark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        return task.mark();
    }

    /**
     * Unmarks a task as done.
     * @param TaskNumber the index of the task to unmark as done (1-based indexing)
     * @throws DukeException if the given index is out of bounds
     */
    public String unmark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot unmark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        return task.unmark();
    }

    /**
     * Creates a new ToDo task and adds it to the list of tasks.
     * @param content the description of the ToDo task
     */
    public String createToDo(String content) {
        SingleTask taskT = new ToDo(content);
        this.taskList.add(taskT);
        String msg = "";
        msg += taskT.toString();
        msg += String.format("\nGot %d task in list boy", taskList.size());
        return msg;
    }

    /**
     * Creates a new Deadline task and adds it to the list of tasks.
     * @param description the description of the Deadline task
     * @param deadline the deadline of the Deadline task
     */
    public String createDeadline(String description, String deadline) {
        SingleTask taskD = new Deadline(description, deadline);
        taskList.add(taskD);
        String msg = "";
        msg += taskD.toString();
        msg += String.format("\nGot %d task in list boy", taskList.size());
        return msg;
    }

    /**
     * Creates a new Event task and adds it to the list of tasks.
     * @param description the description of the Event
     * @param from the start time of the Event
     * @param to the end time of the Event
     */
    public String createEvent(String description, String from, String to) {
        SingleTask taskE = new Event(description, from, to);
        taskList.add(taskE);
        String msg = "";
        msg += taskE.toString();
        msg += String.format("\nGot %d task in list boy", taskList.size());
        return msg;
    }

    /**
     * Finds and displays tasks that contain a given keyword in their description.
     * @param keyWord the keyword to search for in the descriptions of tasks
     */
    public String find(String keyWord) {
        String msg = "";
        if (this.taskList.size() == 0) {
            return "Boy ah got no tasks how to find anything??";
        }
        int i = 1;
        for (SingleTask task : this.taskList) {
            if (task.description.contains(keyWord)) {
                msg += i + task.listString() + "\n";
                i++;
            }
        }
        return msg;
    }
}
