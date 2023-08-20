public class ToDoList {
    private String[] list = new String[100];
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
            System.out.println((i + 1) + ". " + list[i]);
        }
        printLine();
    }
}
