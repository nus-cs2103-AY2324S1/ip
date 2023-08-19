import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private LinkedList<Task> tasks = new LinkedList<>();

    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private void displayInvalidIndex() {
        System.out.println("    Enter a valid index");
    }

    private void greet() {
        printLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    private void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    private Task createTask(String[] words) {
        StringBuilder taskName = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();

        if (words[0].equals("todo")) {
            for (int i = 1; i < words.length; i += 1) {
                taskName.append(" ").append(words[i]);
            }
            return new ToDoTask(taskName.toString());
        } else if (words[0].equals("event")) {
            int i = 1;
            while (i < words.length && !words[i].equals("/from")) {
                taskName.append(" ").append(words[i]);
                i += 1;
            }
            i += 1;
            while (i < words.length && !words[i].equals("/to")) {
                startDate.append(" ").append(words[i]);
                i += 1;
            }
            i += 1;
            while (i < words.length) {
                endDate.append(" ").append(words[i]);
                i += 1;
            }
            return new EventTask(taskName.toString(), startDate.toString(), endDate.toString());
        } else {
            int i = 1;
            while (i < words.length && !words[i].equals("/by")) {
                taskName.append(" ").append(words[i]);
                i += 1;
            }
            i += 1;
            while (i < words.length) {
                endDate.append(" ").append(words[i]);
                i += 1;
            }
            return new DeadlineTask(taskName.toString(), endDate.toString());
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("     " + "Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void listTasks() {
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " + i + "." + task);
            i += 1;
        }
    }

    private void markTask(String index) {
        String regex = "\\d+";

        if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
            this.displayInvalidIndex();
        } else {
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markCompleted();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + task.toString());
        }
    }

    private void unmarkedTask(String index) {
        String regex = "\\d+";

        if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
            this.displayInvalidIndex();
        } else {
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markNotCompleted();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + task.toString());
        }
    }


    private boolean parseCommand(String command) {
        String[] words = command.trim().split("\\s");
        if (words[0].equals("bye") && words.length == 1) {
            return false;
        } else if (words[0].equals("list") && words.length == 1) {
            this.listTasks();
        } else if (words[0].equals("mark") && words.length == 2) {
            this.markTask(words[1]);
        } else if (words[0].equals("unmark") && words.length == 2) {
            this.unmarkedTask(words[1]);
        } else if ((words[0].equals("deadline") || words[0].equals("todo") || words[0].equals("event"))) {
            this.addTask(createTask(words));
        } else {
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private void handleUI() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (true) {
            command = scanner.nextLine();
            printLine();
            boolean continueLoop = parseCommand(command);
            if (!continueLoop) {
                break;
            }
            printLine();
        }
        bye();
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.handleUI();
    }
}
