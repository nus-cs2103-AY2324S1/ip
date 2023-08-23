import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke.");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    private void getUserInput() {
        while (true) {
            String userCommand = scanner.nextLine();

            if (userCommand.equals("bye")) {
                this.exit();
                break;
            } else if (userCommand.equals("list")) {
                this.showList();
                continue;
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                this.markTaskAsDone(taskIndex);
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                this.markTaskAsNotDone(taskIndex);
            } else {
                this.addTask(userCommand);
            }

        }
    }

    private void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private void showList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i).getStatusIcon() + " " + list.get(i).description);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex <= list.size()) {
            Task task = list.get(taskIndex);
            task.markAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:\n" +
                    "\t\t" + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("\t____________________________________________________________");
        }
    }

    private void markTaskAsNotDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex <= list.size()) {
            Task task = list.get(taskIndex);
            task.markAsNotDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:\n" +
                    "\t\t" + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("\t____________________________________________________________");
        }
    }

    private void addTask(String userCommand) {
        Task newTask = new Task(userCommand);
        list.add(newTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + userCommand);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.getUserInput();
    }
}
