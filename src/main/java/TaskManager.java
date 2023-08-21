public class TaskManager {
    private Task[] list = new Task[100];
    private int index = 0;
    private int numOfTasks = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void add (Task task) {
        this.list[index] = task;
        this.index += 1;
        this.numOfTasks += 1;
        printLine();
        System.out.println("Got it. I've added the task: \n " + task);
        System.out.println("Now you have " + numOfTasks + " in your list, just like how I have 5 Ballon d'Ors.");
        printLine();
    }

    public void list() {
        printLine();
        System.out.println("Here are the tasks in your list:");
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
