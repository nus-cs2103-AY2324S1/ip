import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String i4 = "    ";
    private static final String i5 = Duke.i4 + " ";
    private static final String i7 = Duke.i5 + "  ";
    private static final String line = Duke.i4 + "————————————————————————————————————————————————————";
    private String name;
    private List<Task> list;

    public Duke(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void line() {
        System.out.println(Duke.line);
    }

    public void exit() {
        System.out.println(Duke.i5 + "Bye. Hope to see you again soon!");
    }

    public void greet() {
        this.line();
        System.out.println(Duke.i5 + "Hello! I'm " + this.name);
        System.out.println(Duke.i5 + "What can I do for you?");
        this.line();
    }

    public void startService() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        this.line();
        if (input.equals("bye")) {
            scanner.close();
            exit();
            this.line();
            return;
        } else if (input.equals("list")) {
            System.out.println(Duke.i5 + "Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(Duke.i5 + (i + 1) + "." + this.list.get(i));
            }
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            this.list.get(index).mark();
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            this.list.get(index).unmark();
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = this.list.remove(index);
            System.out.println(Duke.i5 + "Noted. I've removed this task:");
            System.out.println(Duke.i7 + task);
            System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        } else {

            Task task = null;

            if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    throw new DukeException(Duke.i5 + "☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(input.substring(5));
            } else if (input.startsWith("deadline")) {
                int slashIndex = input.indexOf('/');
                String by = input.substring(slashIndex + 4);
                task = new Deadline(input.substring(9, slashIndex - 1), by);
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String from = input.substring(fromIndex + 6, toIndex - 1);
                String to = input.substring(toIndex + 4);
                task = new Event(input.substring(6, fromIndex - 1), from, to);
            } else {
                throw new DukeException(Duke.i5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            this.list.add(task);

            System.out.println(Duke.i5 + "Got it. I've added this task:");
            System.out.println(Duke.i7 + task);
            System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        }
        this.line();
        startService();
    }

    public static void main(String[] args) throws DukeException {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.startService();
    }
}



