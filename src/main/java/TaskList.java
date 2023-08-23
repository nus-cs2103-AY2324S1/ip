import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> store;

    public TaskList() {
        store = new ArrayList<>();
    }
    public void addTodo(String description) throws EmptyTaskException {
        if(description.isEmpty()) {
            throw new EmptyTaskException("todo", "description");
        }
        Task task= new Todo(description);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addDeadline(String input) throws EmptyTaskException {
        String[] splitInput = input.split(" /by ");
        String description = splitInput[0];
        if(description.isEmpty()) {
            throw new EmptyTaskException("deadline", "description");
        }
        if(splitInput.length < 2) {
            throw new EmptyTaskException("deadline", "by");
        }
        String by = splitInput[1];
        if(by.isEmpty()) {
            throw new EmptyTaskException("deadline", "by");
        }
        Task task= new Deadline(description, by);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addEvent(String input) throws EmptyTaskException {
        String[] splitInput = input.split("/");
        String description = splitInput[0].trim();
        if(description.isEmpty()) {
            throw new EmptyTaskException("event", "description");
        }
        if(splitInput.length < 2) {
            throw new EmptyTaskException("event", "from");
        }
        String from = splitInput[1].replace("from", "").trim();
        if(from.isEmpty()) {
            throw new EmptyTaskException("event", "from");
        }
        if(splitInput.length < 3) {
            throw new EmptyTaskException("event", "to");
        }
        String to  = splitInput[2].replace("to", "").trim();
        if(to.isEmpty()) {
            throw new EmptyTaskException("event", "to");
        }
        Task task= new Event(description, from, to);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void deleteTask(int index) throws InvalidIndexException{
        if(index >= store.size()) {
            throw new InvalidIndexException();
        }
        Task task = store.get(index - 1);
        store.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for(Task task: store) {
            System.out.println(counter + "." + task);
            counter++;
        }
    }

    public void markTask(int index) throws InvalidIndexException{
        if(index >= store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
    }

    public void unmarkTask(int index) throws InvalidIndexException{
        if(index >= store.size()) {
            throw new InvalidIndexException();
        }
        Task curr = store.get(index - 1);
        curr.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + curr);
    }
}
