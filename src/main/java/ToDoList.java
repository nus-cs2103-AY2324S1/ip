public class ToDoList {
    private String[] list = new String[100];
    private boolean[] done = new boolean[100];
    private int index = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void add (String task) {
        this.list[index] = task;
        this.index += 1;
        printLine();
        System.out.println("added: " + task);
        printLine();
    }

    public void list() {
        printLine();
        for (int i = 0; i < index; i++) {
            String status = done[i] ? "[X]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + list[i]);
        }
        printLine();
    }

    public void mark(int index) {
        index -= 1;
        printLine();
        done[index] = true;
        System.out.println("SIUUU! I've marked this task as done: \n [X] " + list[index]);
        printLine();
    }

    public void unmark(int index) {
        index -= 1;
        printLine();
        done[index] = false;
        System.out.println("OK, I've marked this task as not done yet: \n [ ] " + list[index]);
        printLine();
    }


}
