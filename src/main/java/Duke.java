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
        //System.out.println(logo);
        System.out.println("\t Hello! I'm Duke.");
        System.out.println("\t What can I do for you?");
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
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                this.markTaskAsDone(taskIndex);
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                this.markTaskAsNotDone(taskIndex);
            } else if (userCommand.startsWith("todo")) {
                String description = userCommand.substring(5).trim();
                this.addTodo(description);
            } else if (userCommand.startsWith("deadline")) {
                String description = userCommand.substring(9).split("/by")[0].trim();
                String by = userCommand.substring(9).split("/by")[1].trim();
                this.addDeadline(description, by);
            } else if (userCommand.startsWith("event")) {
                String description = userCommand.substring(6).split("/from")[0].trim();
                String from = userCommand.substring(6).split("/from")[1].split("/to")[0].trim();
                String to = userCommand.substring(6).split("/to")[1].trim();
                this.addEvent(description, from, to);
            } else {
                this.addTask(userCommand);
            }

        }
    }

    private void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private void showList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + this.list.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    private void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex <= list.size()) {
            Task task = list.get(taskIndex);
            task.markAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Nice! I've marked this task as done:\n" +
                    "\t\t" + this.list.get(taskIndex));
            System.out.println("\t____________________________________________________________");
        }
    }

    private void markTaskAsNotDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex <= list.size()) {
            Task task = list.get(taskIndex);
            task.markAsNotDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t OK, I've marked this task as not done yet:\n" +
                    "\t\t" + this.list.get(taskIndex));
            System.out.println("\t____________________________________________________________");
        }
    }

    private void addTask(String userCommand) {
        Task newTask = new Task(userCommand);
        list.add(newTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t added: " + userCommand);
        System.out.println("\t____________________________________________________________");
    }

    private void addTodo(String userCommand) {
        Todo newTask = new Todo(userCommand);
        list.add(newTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    private void addDeadline(String description, String by) {
        Deadline newTask = new Deadline(description, by);
        list.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    private void addEvent(String description, String from, String to) {
        Event newTask = new Event(description, from, to);
        list.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.getUserInput();
    }
}
