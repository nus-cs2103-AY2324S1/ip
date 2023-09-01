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
    public void deleteTask(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot delete a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        System.out.println(task.remove());
        this.taskList.remove(TaskNumber - 1);
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void list() {
        if (this.taskList.size() == 0) {
            System.out.println("No tasks here ah boy");
        } else {
            System.out.println("Here are your tasks ah boy:");
            for (int i = 0; i < this.taskList.size(); i++) {
                SingleTask task = this.taskList.get(i);
                System.out.println((i + 1) + task.listString());
            }
        }
    }

    /**
     * Marks a task as done.
     * @param TaskNumber the index of the task to mark as done (1-based indexing)
     * @throws DukeException if the given index is out of bounds
     */
    public void mark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot mark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        task.mark();
    }

    /**
     * Unmarks a task as done.
     * @param TaskNumber the index of the task to unmark as done (1-based indexing)
     * @throws DukeException if the given index is out of bounds
     */
    public void unmark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot unmark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        task.unmark();
    }

    /**
     * Creates a new ToDo task and adds it to the list of tasks.
     * @param content the description of the ToDo task
     */
    public void createToDo(String content) {
        SingleTask taskT = new ToDo(content);
        this.taskList.add(taskT);
        System.out.println(taskT.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }

    /**
     * Creates a new Deadline task and adds it to the list of tasks.
     * @param description the description of the Deadline task
     * @param deadline the deadline of the Deadline task
     */
    public void createDeadline(String description, String deadline) {
        SingleTask taskD = new Deadline(description, deadline);
        taskList.add(taskD);
        System.out.println(taskD.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }

    /**
     * Creates a new Event task and adds it to the list of tasks.
     * @param description the description of the Event
     * @param from the start time of the Event
     * @param to the end time of the Event
     */
    public void createEvent(String description, String from, String to) {
        SingleTask taskE = new Event(description, from, to);
        taskList.add(taskE);
        System.out.println(taskE.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }

    /**
     * Finds and displays tasks that contain a given keyword in their description.
     * @param keyWord the keyword to search for in the descriptions of tasks
     */
    public void find(String keyWord) {
        if (this.taskList.size() == 0) {
            System.out.println("Boy ah got no tasks how to find anything??");
        }
        String msg = "";
        int i = 1;
        for (SingleTask task : this.taskList) {
            if (task.description.contains(keyWord)) {
                msg += i + task.listString() + "\n";
                i++;
            }
        }
        System.out.println(msg);
    }
}
