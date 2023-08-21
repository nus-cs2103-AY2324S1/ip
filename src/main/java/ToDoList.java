public class ToDoList {
    private Task[] list = new Task[100];
    private int index = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void add (String taskName) {
        Task task = new Task(taskName);
        this.list[index] = task;
        this.index += 1;
        printLine();
        System.out.println("added: " + taskName);
        printLine();
    }

    public void list() {
        printLine();
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        printLine();
    }

    public void mark(int index) {
        index -= 1; // since 0 indexed
        Task task = list[index];
        task.mark();
    }

    public void unmark(int index) {
        index -= 1; // since 0 indexed
        Task task = list[index];
        task.unmark();
    }

}
