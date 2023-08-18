import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private LinkedList<Task> tasks = new LinkedList<>();

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
        tasks.add(new Task(task));
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    " + "added: " + task + "\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void listTasks() {
        int i = 1;
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Here are the tasks in your list:\n");
        for (Task task : tasks) {
            System.out.println("    " + i + "." + " " + task);
            i += 1;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void displayInvalidIndex() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Enter a valid index");
        System.out.println("    ____________________________________________________________\n");
    }

    private void markTask(String markTaskCommand) {
        String regex = "\\d+";
        String index = markTaskCommand.substring(5);

        if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
            this.displayInvalidIndex();
        } else {
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markCompleted();
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    Nice! I've marked this task as done:\n");
            System.out.println("    " + task.toString());
            System.out.println("    ____________________________________________________________\n");
        }
    }

    private void unmarkTask(String unmarkTaskCommand) {
        String regex = "\\d+";
        String index = unmarkTaskCommand.substring(7);

        if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
            this.displayInvalidIndex();
        } else {
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markNotCompleted();
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    OK, I've marked this task as not done yet:\n");
            System.out.println("    " + task.toString());
            System.out.println("    ____________________________________________________________\n");
        }
    }

    private void handleUI() {
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                this.bye();
                break;
            } else if (command.equals("list")) {
                this.listTasks();
            } else if (command.startsWith("mark ")) {
                this.markTask(command);
            } else if (command.startsWith("unmark ")) {
                this.unmarkTask(command);
            } else {
                this.addTask(command);
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.handleUI();
    }
}
