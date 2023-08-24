import java.util.ArrayList;
import java.util.List;

/**
 * DukeList is a class that represents a list of tasks.
 */
public class DukeList {
    private List<Task> dukeList;

    /**
     * Constructs a new DukeList object with an empty list of tasks.
     */
    public DukeList() {
        dukeList = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    private void add(Task task) {
        dukeList.add(task);
        System.out.println("Got it. I've added this task:\n\t" + task.toString() + "\nNow you have " + dukeList.size() + " tasks in the list.");
    }

    /**
     * Adds a ToDo task to the list of tasks.
     *
     * @param input The description of the ToDo task to be added.
     */
    public void addToDo(String input) {
        ToDo todo = new ToDo(input);
        this.add(todo);
    }

    /**
     * Adds a Deadline task to the list of tasks.
     *
     * @param input The description of the Deadline task to be added.
     * @param by The deadline of the task.
     */
    public void addDeadline(String input, String by) {
        Deadline deadline = new Deadline(input, by);
        this.add(deadline);
    }

    /**
     * Adds an Event task to the list of tasks.
     *
     * @param input The description of the Event task to be added.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public void addEvent(String input, String from, String to) {
        Event event = new Event(input, from, to);
        this.add(event);
    }

    /**
     * Displays the list of tasks along with their corresponding indices.
     */
    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= dukeList.size(); i++) {
            Task task = dukeList.get(i - 1);
            System.out.println(i + ". " + task.toString());
        }
    }

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param key The index of the task to be marked as done.
     */
    public void markDone(int key) {
        Task task = dukeList.get(key - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + "\t" + task.toString());
    }

    /**
     * Marks a task as not done based on its index in the list.
     *
     * @param key The index of the task to be marked as not done.
     */
    public void unmark(int key) {
        Task task = dukeList.get(key - 1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + "\t" + task.toString());
    }
    
    public void takeInput(String input) throws DukeException {
        if (input.equals("list")) {
            this.display();
            return;
        }
        if (input.startsWith("mark")) {
            String[] inputs = input.split(" ");
            int key = Integer.parseInt(inputs[1]);
            if (key > dukeList.size() - 1) {
                throw new DukeException("key exceeds size of list");
            }
            this.markDone(key);
            return;
        }
        if (input.startsWith("unmark")) {
            String[] inputs = input.split(" ");
            int key = Integer.parseInt(inputs[1]);
            if (key > dukeList.size() - 1) {
                throw new DukeException("key exceeds size of list");
            }
            this.unmark(key);
            return;
        }
        if (input.startsWith("todo")) {
            String[] inputs = input.split(" ", 2);
            try {
                this.addToDo(inputs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("todo cant be empty");
            }
            return;
        }
        if (input.startsWith("deadline")) {
            String[] inputs = input.split(" ", 2);
            try {
                String[] deadLine = inputs[1].split("/by", 2);
                this.addDeadline(deadLine[0], deadLine[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("deadline invalid format");
            }
            return;
        }
        if (input.startsWith("event")) {
            String[] inputs = input.split(" ", 2);
            try {
                String[] from = inputs[1].split("/from", 2);
                String[] to = from[1].split("/to", 2);
                this.addEvent(from[0], to[0], to[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("event invalid format");
            }
            return;
        }
        throw new DukeException("Unrecognised input :(");
    }
}
