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
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                line();
                list.mark(index - 1);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                line();
                list.unmark(index - 1);
                line();
                input = in.nextLine();
                continue;
            }

            line();
            list.add(input);
            line();
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
    private boolean[] doneList;

    public TaskList() {
        this.list = new String[100];
        currentIndex = 0;
        this.doneList = new boolean[100];
    }

    public void add(String input) {
        list[currentIndex] = input;
        currentIndex++;
        System.out.println("added: " + input);
    }

    public void listTasks() {
        for (int i = 0; i < currentIndex; i++) {
            String output = i + 1 + ".";
            if (doneList[i]) {
                output += "[X] ";
            } else {
                output += "[ ] ";
            }
            output += list[i];
            System.out.println(output);
        }
    }

    public void mark(int index) {
        doneList[index] = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + list[index]);
    }

    public void unmark(int index) {
        doneList[index] = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + list[index]);
    }
}