import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> store;

    public TaskList() {
        store = new ArrayList<>();
    }
    public void addTodo(String title) {
        Task task= new Todo(title);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addDeadline(String input) {
        String[] splitInput = input.split(" /by ");
        Task task= new Deadline(splitInput[0], splitInput[1]);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addEvent(String input) {
        String[] splitInput = input.split("/");
        String title = splitInput[0].trim();
        String from = splitInput[1].replace("from", "").trim();
        String to  = splitInput[2].replace("to", "").trim();
        Task task= new Event(title, from, to);
        store.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addTask(String title) {
        Task task= new Task(title);
        store.add(task);
        System.out.println("Got it. I've added this task:");
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

    public void markTask(int index) {
        Task curr = store.get(index - 1);
        curr.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
    }

    public void unmarkTask(int index) {
        Task curr = store.get(index - 1);
        curr.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + curr);
    }
}
