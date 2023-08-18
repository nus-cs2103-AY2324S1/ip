import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    LinkedList<String> tasks = new LinkedList<>();

    private void greet() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Hello! I'm Duke\n");
        System.out.println("    What can I do for you?\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void bye() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void addTask(String task) {
        tasks.add(task);
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    " + "added: " + task + "\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void listTasks() {
        int i = 1;
        System.out.println("    ____________________________________________________________\n");
        for (String task : tasks) {
            System.out.println("    " + i + "." + " " + task);
            i += 1;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                this.bye();
                break;
            } else if (command.equals("list")) {
                this.listTasks();
            } else {
                this.addTask(command);
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.echo();
    }
}
