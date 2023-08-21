import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String CHAT_BOT_NAME = "Genos";
    private ArrayList<Task> list;
    private enum Command {
        TASK, MARK, UNMARK, LIST, EXIT
    }

    public Duke() {
        this.list = new ArrayList<>();
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
        String text = sc.nextLine();
        while (true) {
            Command cmd = textToCommand(text);
            switch (cmd) {
                case EXIT:
                    System.out.println("    Goodbye, Hope to see you again soon.");
                    return;
                case LIST:
                    this.listCommands();
                    break;
                case MARK:
                    int num = Integer.parseInt(text.substring(5));
                    if (num > this.list.size() || num <= 0) {
                        System.out.println("The task number you are trying to mark does not exist yet.");
                    } else {
                        Task task = this.list.get(num-1);
                        task.doTask();
                        System.out.println("    I've marked this task as done:");
                        System.out.println(task.toString());
                    }
                    break;
                case UNMARK:
                    int num2 = Integer.parseInt(text.substring(7));
                    if (num2 > this.list.size() || num2 <= 0) {
                        System.out.println("The task number you are trying to unmark does not exist yet.");
                    } else {
                        Task task = this.list.get(num2-1);
                        task.undoTask();
                        System.out.println("    I've marked this task as not done yet:");
                        System.out.println(task.toString());
                    }
                    break;
                case TASK:
                    System.out.println("    Added: " + text);
                    this.list.add(new Task(text));
                    break;
                default:
                    System.out.println("There seems to be some error here");
            }
            text = sc.nextLine();
        }

    }

    private void listCommands() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.list.get(i));
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
        if (text.substring(0, Math.min(text.length(), 6)).equals("unmark")) {
            return Command.UNMARK;
        }
        return Command.TASK;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Duke.greet();
        bot.converse();
    }
}
