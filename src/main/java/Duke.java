import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String i4 = "    ";
    private static final String i5 = Duke.i4 + " ";
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

    public void startService() {
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
        } else {
            this.list.add(new Task(input));
            System.out.println(Duke.i5 + "added: " + input);
        }
        this.line();
        startService();
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.startService();
    }
}



