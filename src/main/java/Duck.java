import java.util.Scanner;
public class Duck {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        
        Duck.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                line();
                list.listTasks();
                line();
            } else {
                line();
                list.add(input);
                line();
            }
            input = in.nextLine();
        }
        Duck.bye();
        in.close();
    }

    private static void line() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm Duck\n" + "What can I do for you?";

        line();
        System.out.println(greeting);
        line();
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";

        line();
        System.out.println(bye);
        line();
    }

    private static void echo(String input) {
        line();
        System.out.println(input);
        line();
    }

}

class TaskList {
    private String[] list;
    private int currentIndex;

    public TaskList() {
        this.list = new String[100];
        currentIndex = 0;
    }

    public void add(String input) {
        list[currentIndex] = input;
        currentIndex++;
        System.out.println("added: " + input);
    }

    public void listTasks() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(i + 1 + ". " + list[i]);
        }
    }
}