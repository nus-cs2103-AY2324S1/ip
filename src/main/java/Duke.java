import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String CHAT_BOT_NAME = "Genos";
    private ArrayList<Task> tasks;
    private enum Command {
        MARK, UNMARK, LIST, EXIT, TODO, DEADLINE, EVENT, INVALID
    }

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    private static void greet() {
        System.out.println("Hello I'm " + Duke.CHAT_BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("Please type your command below, I will store what you said");
        System.out.println("Usage: \"list\" to see the list of text stored, \"bye\" to exit \n" +
                "\"mark (number)\" to mark task no. (number) to be done, \"unmark (number)\" to mark it as undone");
    }

    private void converse() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String text = sc.nextLine();
            Command cmd = textToCommand(text);

            // non tasks command.
            if (cmd == Command.EXIT) {
                System.out.println("    Goodbye, Hope to see you again soon.");
                return;
            } else if (cmd == Command.LIST) {
                this.listTasks();
                continue;
            }

            // adding tasks
            Task task;
            switch (cmd) {
                case MARK:
                    int num = Integer.parseInt(text.substring(5));
                    if (num > this.tasks.size() || num <= 0) {
                        System.out.println("The task number you are trying to mark does not exist yet.");
                        continue;
                    } else {
                        task = this.tasks.get(num-1);
                        task.doTask();
                        System.out.println("    I've marked this task as done:");
                    }
                    break;
                case UNMARK:
                    int num2 = Integer.parseInt(text.substring(7));
                    if (num2 > this.tasks.size() || num2 <= 0) {
                        System.out.println("The task number you are trying to unmark does not exist yet.");
                        continue;
                    } else {
                        task = this.tasks.get(num2-1);
                        task.undoTask();
                        System.out.println("    I've marked this task as not done yet:");
                    }
                    break;
                case TODO:
                    System.out.println("    Added Todo to the list of tasks:");
                    task = new Todo(text.substring(5));
                    this.tasks.add(task);
                    break;
                case EVENT:
                    System.out.println("    Added Event to the list of tasks:" );
                    String[] parts = (text.substring(6)).split("/");
                    String fromTime = parts[1].substring(5); // remove the "from "
                    String toTime = parts[2].substring(3);  // remove the "to "
                    task = new Event(parts[0], fromTime, toTime);
                    this.tasks.add(task);
                    break;
                case DEADLINE:
                    System.out.println("    Added Deadline to the list of tasks:");
                    String[] parts2 = (text.substring(9)).split("/");
                    String by = parts2[1].substring(3); // remove the "by "
                    task = new Deadline(parts2[0], by);
                    this.tasks.add(task);
                    break;
                default:
                    System.out.println("    There seems to be some error here");
                    continue;
            }
            System.out.println("    " + task.toString());
            System.out.println("    You currently have " + this.tasks.size() + " tasks in the list.");

        }

    }

    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.tasks.get(i));
        }
    }

    private Command textToCommand(String text) {
        if (text.equals("bye")) {
            return Command.EXIT;
        }
        if (text.equals("list")) {
            return Command.LIST;
        }
        if (text.substring(0, Math.min(text.length(), 4)).equals("mark")) {
            return Command.MARK;
        }
        if (text.substring(0, Math.min(text.length(), 4)).equals("todo")) {
            return Command.TODO;
        }
        if (text.substring(0, Math.min(text.length(), 5)).equals("event")) {
            return Command.EVENT;
        }
        if (text.substring(0, Math.min(text.length(), 6)).equals("unmark")) {
            return Command.UNMARK;
        }
        if (text.substring(0, Math.min(text.length(), 8)).equals("deadline")) {
            return Command.DEADLINE;
        }
        return Command.INVALID;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Duke.greet();
        bot.converse();
    }
}
