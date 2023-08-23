import java.util.ArrayList;
public class AllyList {
    ArrayList<Task> arr;
    public AllyList() {
        arr = new ArrayList<>(100);
    }

    public void addElements(String str) {
        Task task = new Task(str);
        arr.add(task);
    }

    public void markAsDone(int index) {
        Task task = arr.get(index);
        task.setMarked();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }
    
    public void unMarkDone(int index) {
        Task task = arr.get(index);
        task.notDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }
    public void printElements() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0, len = arr.size(); i < len; i++) {
            System.out.println((i + 1) +". " + arr.get(i).toString());
        }
    }
    public void printNewList(Task task) {
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");

    }
    public void addTodo(String input) {
        Todo todo = new Todo(input);
        arr.add(todo);
        printNewList(todo);
    }

    public void addDeadline(String input, String time) {
        Deadline ddline = new Deadline(input, time);
        arr.add(ddline);
        printNewList(ddline);
    }

    public void addEvent(String input, String from, String to) {
        Event event = new Event(input, from, to);
        arr.add(event);
        printNewList(event);
    }

}
