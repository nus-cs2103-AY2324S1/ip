import java.util.ArrayList;

/**
 * Represents a list of tasks with various operations to manage tasks.
 */
public class TaskList {
    private ArrayList<Task> store;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        store = new ArrayList<>();
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param description The description of the todo task.
     * @throws EmptyTaskException If the task description is empty.
     */
    public void addTodo(String description) throws EmptyTaskException {
        if(description.isEmpty()) {
            throw new EmptyTaskException(TaskType.TODO, "description");
        }
        Task task= new Todo(description);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param input The input containing the description and deadline.
     * @throws EmptyTaskException If the task description or deadline is empty.
     */
    public void addDeadline(String input) throws EmptyTaskException {
        String[] splitInput = input.split(" /by ");
        String description = splitInput[0];
        if(description.isEmpty()) {
            throw new EmptyTaskException(TaskType.DEADLINE, "description");
        }
        if(splitInput.length < 2) {
            throw new EmptyTaskException(TaskType.DEADLINE, "by");
        }
        String by = splitInput[1];
        if(by.isEmpty()) {
            throw new EmptyTaskException(TaskType.DEADLINE, "by");
        }
        Task task= new Deadline(description, by);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param input The input containing the description, start time, and end time.
     * @throws EmptyTaskException If the task description, start time, or end time is empty.
     */
    public void addEvent(String input) throws EmptyTaskException {
        String[] splitInput = input.split("/");
        String description = splitInput[0].trim();
        if(description.isEmpty()) {
            throw new EmptyTaskException(TaskType.EVENT, "description");
        }
        if(splitInput.length < 2) {
            throw new EmptyTaskException(TaskType.EVENT, "from");
        }
        String from = splitInput[1].replace("from", "").trim();
        if(from.isEmpty()) {
            throw new EmptyTaskException(TaskType.EVENT, "from");
        }
        if(splitInput.length < 3) {
            throw new EmptyTaskException(TaskType.EVENT, "to");
        }
        String to  = splitInput[2].replace("to", "").trim();
        if(to.isEmpty()) {
            throw new EmptyTaskException(TaskType.EVENT, "to");
        }
        Task task= new Event(description, from, to);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    public void deleteTask(int index) throws InvalidIndexException{
        if(index > store.size()) {
            throw new InvalidIndexException();
        }
        Task task = store.get(index - 1);
        store.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for(Task task: store) {
            System.out.println(counter + "." + task);
            counter++;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    public void markTask(int index) throws InvalidIndexException{
        if(index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws InvalidIndexException If the provided index is invalid.
     */
    public void unmarkTask(int index) throws InvalidIndexException{
        if(index > store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + curr);
    }
}
