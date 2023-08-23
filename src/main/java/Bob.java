public class Bob {
    private String name = "Bob";
    private Task[] list = new Task[100];
    private String horizontal = "____________________________________________________________";
    private int count;

    public Bob() {
        this.count = 0;
    }

    public void greet() {
        String ln1 = String.format("Hello! I'm %s", this.name);
        String ln2 = "What can I do for you?";

        System.out.println(horizontal);
        System.out.println(ln1);
        System.out.println(ln2);
        System.out.println(horizontal);
    }
    public void bye() {
        String ln3 = "Bye. Hope to see you again soon!";

        System.out.println(horizontal);
        System.out.println(ln3);
        System.out.println(horizontal);
    }

    public void addTask(String desc) {
        Task task = new Task(desc);
        list[this.count] = task;
        this.count++;

        System.out.println(horizontal);
        System.out.println("added: " + desc);
        System.out.println(horizontal);
    }
    public void addTodo(String text) {
        Todo task = new Todo(text);
        list[this.count] = task;
        this.count++;

        System.out.println(horizontal);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(horizontal);
    }

    public void addDeadline(String text) {
        String[] split = text.split(" /by ", 2);
        String desc = split[0];
        String by = split[1];
        Deadline task = new Deadline(desc, by);
        list[this.count] = task;
        this.count++;

        System.out.println(horizontal);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + count + " tasks in the list.");
        System.out.println(horizontal);
    }

    public void addEvent(String text) {
        String[] slice = text.split(" /from ", 2);
        String desc = slice[0];
        String[] slicess = slice[1].split(" /to ", 2);
        String from = slicess[0];
        String to = slicess[1];

        Event task = new Event(desc, from, to);
        list[this.count] = task;
        this.count++;

        System.out.println(horizontal);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + count + " tasks in the list. ");
        System.out.println(horizontal);
    }

    public void listOut() {
        System.out.println(horizontal);
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= this.count; i++) {
            System.out.println(i + ". " + list[i - 1]);
        }

        System.out.println(horizontal);
    }

    public void markTask(int index) {
        Task task = list[index];
        task.markAsDone();
    }

    public void unmarkTask(int index) {
        Task task = list[index];
        task.unmark();
    }
}
